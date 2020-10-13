package br.com.leodevel.synchronization.models;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class User implements Entity {

    public static String FILE = "config/users.xml";

    private String name;
    private String login;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        xstream.alias(getAlias(), User.class);
        return xstream;
    }

    @Override
    public String getAlias() {
        return "user";
    }

}