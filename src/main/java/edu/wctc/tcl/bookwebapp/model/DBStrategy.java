package edu.wctc.tcl.bookwebapp.model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author tliebl
 */
public interface DBStrategy {
    
      public abstract void openConnection(String driverClass, String url, 
            String userName, String password) 
            throws ClassNotFoundException, SQLException;
    
    public abstract void closeConnection() throws SQLException;
    
    public abstract List<Map<String, Object>> findAllRecords(String tableName, int maxRecords) throws SQLException;
    
    public abstract void deletedSingleRecordFromTable(String DBName, String tableName, String idName, String id) throws SQLException;
}

