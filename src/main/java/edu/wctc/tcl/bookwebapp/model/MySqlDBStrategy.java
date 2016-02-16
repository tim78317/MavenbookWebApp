package edu.wctc.tcl.bookwebapp.model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author tliebl
 */
public class MySqlDBStrategy implements DBStrategy {

    private Connection conn;
   

    @Override
    public void openConnection(String driverClass, String url,
            String userName, String password) throws ClassNotFoundException, SQLException {

        Class.forName(driverClass);
        conn = DriverManager.getConnection(url, userName, password);

    }

    @Override
    public void closeConnection() throws SQLException {
        conn.close();
    }

    /**
     * Make sure you open and close a connection when using this method. Future
     * optimizations may include change the return type to an array.
     *
     * @param tableName
     * @param maxRecords - limits records found to first maxRecords or if
     * maxRecords is zero then no limit.
     * @return
     * @throws java.sql.SQLException
     */
    @Override
    public List<Map<String, Object>> findAllRecords(String tableName, int maxRecords) throws SQLException {
        String sql;
        if (maxRecords < 1) {
            sql = "select * from " + tableName;
        } else {
            sql = "select * from " + tableName + " limit " + maxRecords;
        }
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();
        List<Map<String, Object>> records = new ArrayList<>();

        while (rs.next()) {
            Map<String, Object> record = new HashMap<>();
            for (int colNo = 1; colNo <= columnCount; colNo++) {
                Object colData = rs.getObject(colNo);
                String colName = rsmd.getColumnName(colNo);
                record.put(colName, colData);
            }
            records.add(record);
        }
        return records;
    }

    @Override
    public void deletedSingleRecordFromTable(String DBName, String tableName, String idName, String id) throws SQLException {
        final String FINAL_SINGLE_RECORD_DELETE_SQL = "DELETE FROM " + tableName + " WHERE " + idName + "=?";
        final PreparedStatement psForSIngleDelete = conn.prepareStatement(FINAL_SINGLE_RECORD_DELETE_SQL);
        psForSIngleDelete.setString(1, id);
        psForSIngleDelete.executeUpdate();
        System.out.println("Record is Deleted");
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        DBStrategy db = new MySqlDBStrategy();
        db.openConnection("com.mysql.jdbc.Driver",
                "jdbc:mysql://box1090.bluehost.com:3306/mygamepa_books", "mygamepa_ftrial", "Nalani09");
        
        List<Map<String, Object>> rawData = db.findAllRecords("author", 10);
        //db.deletedSingleRecordFromTable("mygamepa_books", "author", "author_id", "6");
        db.closeConnection();

        System.out.println(rawData);
    }

}
