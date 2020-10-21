package br.com.leodevel.synchronization.models;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.util.List;

public class DataSynchronize implements Entity {
    
    public static String FILE = "config/data_synchronize.xml";
    
    private Integer id;
    
    private String name;
    
    private DataSource source;
    private DataSource destination;
    
    private List<DataMapping> dataMapping;
    private List<TableColumn> allColumnsSource;
    private List<TableColumn> allColumnsDestination;
    
    private Boolean replaceIfExist;
    private Integer interval;
    private Boolean startImmediately;
    private Boolean processAll;
    private Integer qtdReprocess;
    
    private Integer limitRecords;
    
    private Object lastReading;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DataSource getSource() {
        return source;
    }

    public void setSource(DataSource source) {
        this.source = source;
    }

    public DataSource getDestination() {
        return destination;
    }

    public void setDestination(DataSource destination) {
        this.destination = destination;
    }

    public List<DataMapping> getDataMapping() {
        return dataMapping;
    }

    public void setDataMapping(List<DataMapping> dataMapping) {
        this.dataMapping = dataMapping;
    }

    public Boolean getReplaceIfExist() {
        return replaceIfExist;
    }

    public void setReplaceIfExist(Boolean replaceIfExist) {
        this.replaceIfExist = replaceIfExist;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    public Boolean getStartImmediately() {
        return startImmediately;
    }

    public void setStartImmediately(Boolean startImmediately) {
        this.startImmediately = startImmediately;
    }

    public Boolean getProcessAll() {
        return processAll;
    }

    public void setProcessAll(Boolean processAll) {
        this.processAll = processAll;
    }

    public Integer getQtdReprocess() {
        return qtdReprocess;
    }

    public Integer getLimitRecords() {
        return limitRecords;
    }

    public void setLimitRecords(Integer limitRecords) {
        this.limitRecords = limitRecords;
    }

    public void setQtdReprocess(Integer qtdReprocess) {
        this.qtdReprocess = qtdReprocess;
    }

    public Object getLastReading() {
        return lastReading;
    }

    public void setLastReading(Object lastReading) {
        this.lastReading = lastReading;
    }    

    public List<TableColumn> getAllColumnsSource() {
        return allColumnsSource;
    }

    public void setAllColumnsSource(List<TableColumn> allColumnsSource) {
        this.allColumnsSource = allColumnsSource;
    }

    public List<TableColumn> getAllColumnsDestination() {
        return allColumnsDestination;
    }

    public void setAllColumnsDestination(List<TableColumn> allColumnsDestination) {
        this.allColumnsDestination = allColumnsDestination;
    }    

    @Override
    public String toXML() {
        XStream xstream = getStream();
        String xml = xstream.toXML(this);
        return xml;
    }

    @Override
    public String getFilename() {
        return FILE;
    }

    @Override
    public XStream getStream() {
        XStream xstream = new XStream(new DomDriver());
        xstream.alias(getAlias(), DataSynchronize.class);
        xstream.useAttributeFor(DataSynchronize.class, "id");
        xstream.alias("data_mapping", DataMapping.class);
        xstream.alias("table_column", TableColumn.class);
        xstream.alias("data_source", DataSource.class);
        return xstream;
    }

    @Override
    public String getAlias() {
        return "data_synchronize";
    }
    
}
