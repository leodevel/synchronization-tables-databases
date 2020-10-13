package br.com.marino.monitorar.models;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataSource {
    
    private String host;
    private String database;
    private String user;
    private String password;
    private String table;
    
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }    

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }    

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }    
    
    public Connection getConnection() throws Exception {        
        return DriverManager.getConnection("jdbc:sqlserver://" + host + ";databaseName=" + database,
                user, password);
    }
    
}
