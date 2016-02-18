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
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
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
    public int deleteById(String DBName, String tableName, String idName, Object value) throws SQLException {
        final String FINAL_SINGLE_RECORD_DELETE_SQL = "DELETE FROM " + tableName + " WHERE " + idName + "=?";
        final PreparedStatement psForSingleDelete = conn.prepareStatement(FINAL_SINGLE_RECORD_DELETE_SQL);
        psForSingleDelete.setObject(1, value);
        int result = psForSingleDelete.executeUpdate();
        return result;
    }

    public int updateRecordById(String tableName, List<String> colNames, List<Object> colValues, String pkColName, Object value) throws SQLException {

        PreparedStatement pstmt = null;
        int recsUpdated = 0;

        // do this in an excpetion handler so that we can depend on the
        // finally clause to close the connection
        try {
            pstmt = buildUpdateStatement(conn, tableName, colNames, pkColName);

            final Iterator i = colValues.iterator();
            int index = 1;
            Object obj = null;

            // set params for column values
            while (i.hasNext()) {
                obj = i.next();
                pstmt.setObject(index++, obj);
            }
            // and finally set param for wehere value
            pstmt.setObject(index, value);

            recsUpdated = pstmt.executeUpdate();

        } catch (SQLException sqle) {
            throw sqle;
        } catch (Exception e) {
            throw new SQLException(e.getMessage());
        } finally {
            try {
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                throw e;
            } // end try
        } // end finally

        return recsUpdated;
    }

    /*
     * Builds a java.sql.PreparedStatement for an sql update using only one where clause test
     * @param conn - a JDBC <code>Connection</code> object
     * @param tableName - a <code>String</code> representing the table name
     * @param colDescriptors - a <code>List</code> containing the column descriptors for
     * the fields that can be updated.
     * @param whereField - a <code>String</code> representing the field name for the
     * search criteria.
     * @return java.sql.PreparedStatement
     * @throws SQLException
     */
    private PreparedStatement buildUpdateStatement(Connection conn_loc, String tableName,
            List colDescriptors, String whereField)
            throws SQLException {
        StringBuffer sql = new StringBuffer("UPDATE ");
        (sql.append(tableName)).append(" SET ");
        final Iterator i = colDescriptors.iterator();
        while (i.hasNext()) {
            (sql.append((String) i.next())).append(" = ?, ");
        }
        sql = new StringBuffer((sql.toString()).substring(0, (sql.toString()).lastIndexOf(", ")));
        ((sql.append(" WHERE ")).append(whereField)).append(" = ?");
        final String finalSQL = sql.toString();
        return conn_loc.prepareStatement(finalSQL);
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        DBStrategy db = new MySqlDBStrategy();
        db.openConnection("com.mysql.jdbc.Driver",
                "jdbc:mysql://box1090.bluehost.com:3306/mygamepa_books", "mygamepa_ftrial", "Nalani09");

        //List<Map<String, Object>> rawData = db.findAllRecords("author", 10);
        //db.deleteById("mygamepa_books", "author", "author_id", 4);
        List<String> colNames = Arrays.asList("author_name", "date_added");
        List<Object> calValues = Arrays.asList("Sally Amber", "2007-07-07");
        int result = db.updateRecordById("author", colNames, calValues, "author_id", 3);
        System.out.println(result);
        db.closeConnection();

        //System.out.println(rawData);
    }

}
