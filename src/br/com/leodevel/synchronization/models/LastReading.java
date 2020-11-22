package br.com.leodevel.synchronization.models;

import br.com.leodevel.synchronization.utils.Utils;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class LastReading {
    
    public static final String PATH = "config";

    private Object lastReading;

    public Object getLastReading() {
        return lastReading;
    }

    public void setLastReading(Object lastReading) {
        this.lastReading = lastReading;
    }

    public static String getFile(Integer configId){
        return PATH + "/reading_" + configId + ".xml"; 
    }
    
    public static XStream getStream() {
        XStream xstream = new XStream(new DomDriver());
        xstream.alias(getAlias(), LastReading.class);
        return xstream;
    }

    public static String getAlias() {
        return "last_reading";
    }

    public String toXML() {
        XStream xstream = getStream();
        String xml = xstream.toXML(this);
        return xml;
    }

    public static LastReading getLastReading(Integer configId) {

        String file = getFile(configId);
        
        Utils.createFileCofig(file, "<" + getAlias() + "/>");
        
        XStream stream = getStream();
        LastReading obj = (LastReading) stream.fromXML(Paths.get(file).toFile());

        return obj;

    }

    public static void update(LastReading newObj, Integer configId) throws IOException {

        String file = getFile(configId);
        
        Utils.createFileCofig(file, "<" + getAlias() + "/>");
        
        String xml = newObj.toXML();

        Files.write(Paths.get(file), new ArrayList<>(Arrays.asList(xml)),
                StandardCharsets.UTF_8);

    }

}
