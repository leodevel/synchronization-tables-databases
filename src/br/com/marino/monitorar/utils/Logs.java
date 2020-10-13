package br.com.marino.monitorar.utils;

import br.com.marino.monitorar.models.DataSynchronize;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logs {

    public static void addLog(DataSynchronize ds, String msg, 
            LocalDateTime datetime, Exception ex) {

        File dir = Paths.get("logs").toFile();

        if (!dir.exists()) {
            dir.mkdirs();
        }

        File log = Paths.get("logs/data_synchronize_" + ds.getId() + ".log").toFile();

        if (!log.exists()) {
            try {
                log.createNewFile();
            } catch (IOException ex1) {
            }
        }

        if (ex == null) {

            String text = datetime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) + " - " + msg;

            try {
                Files.write(log.toPath(), ("\r\n" + text + "\r\n").getBytes(), StandardOpenOption.APPEND);
            } catch (IOException ex1) {
            }

        }

        if (ex != null) {
            try {

                try (FileWriter fw = new FileWriter(log, true)) {
                    ex.printStackTrace(new PrintWriter(fw));
                } catch (Exception ex2) {
                }

            } catch (Exception ex2) {
            }

        }
    }

}
