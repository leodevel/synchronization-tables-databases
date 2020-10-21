package br.com.leodevel.synchronization.modules;

import br.com.leodevel.synchronization.models.DataSynchronize;
import br.com.leodevel.synchronization.services.DataSynchronizeService;
import br.com.leodevel.synchronization.utils.Logs;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataSynchronizeModule extends Thread {

    private final DataSynchronize dataSynchronize;
    private boolean run;

    public DataSynchronizeModule(DataSynchronize dataSynchronize) {
        this.dataSynchronize = dataSynchronize;
        this.run = false;
    }

    public void close() {
        this.run = false;
    }

    @Override
    public void run() {

        this.run = true;

        while (this.run) {

            try {

                List<Map<String, Object>> list = DataSynchronizeService.getInstance()
                        .getSelectBySource(dataSynchronize);

                for (Map<String, Object> row : list) {

                    try {

                        Object id = DataSynchronizeService.getInstance().insertToDestination(dataSynchronize, row);
                        dataSynchronize.setLastReading(id);
                        DataSynchronizeService.getInstance().update(dataSynchronize, dataSynchronize);
                        
                        Logger.getLogger(DataSynchronizeModule.class.getName())
                                    .log(Level.INFO, "Inserido (id) = {0}", id);

                    } catch (Exception ex) {                        

                        ex.printStackTrace();
                        
                        if (ex.getMessage().startsWith("Violação da restrição PRIMARY KEY")
                                && dataSynchronize.getReplaceIfExist()) {

                            Object id = DataSynchronizeService.getInstance().updateToDestination(dataSynchronize, row);
                            dataSynchronize.setLastReading(id);
                            DataSynchronizeService.getInstance().update(dataSynchronize, dataSynchronize);

                            Logger.getLogger(DataSynchronizeModule.class.getName())
                                    .log(Level.INFO, "Atualizado (id) = {0}", id);
                            
                        } else {                            
                            Logs.addLog(dataSynchronize, "", LocalDateTime.now(), ex);                            
                        }   

                    }
                    
                    Logger.getLogger(DataSynchronizeModule.class.getName())
                                    .log(Level.INFO, "---------------------------------------");

                }                

            } catch (Exception ex) {                               
                Logs.addLog(dataSynchronize, "", LocalDateTime.now(), ex);                
            }

            try {
                Thread.sleep((dataSynchronize.getInterval() * 1000));
            } catch (InterruptedException ex) {
            }

        }

    }

}
