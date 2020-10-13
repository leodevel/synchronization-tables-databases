package br.com.marino.monitorar.models;

import com.thoughtworks.xstream.XStream;

public interface Entity {
    
    public String toXML();
    
    public String getFilename();
    
    public XStream getStream();
    
    public String getAlias();
    
}
