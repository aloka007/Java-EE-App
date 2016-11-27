/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rms.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Tharinda
 */
public class DBpack {

    public static Connection getConnection() {
        Connection conn;
        DataBaseConnection dbconn;
        dbconn = new DataBaseConnection();
        conn = dbconn.getConnection();
        return conn;
    }

    public static int runUpdate(String query) throws SQLException {
        Connection conn2 = DBpack.getConnection();
        PreparedStatement ps = conn2.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        int generatedKey = 0;
        if (rs.next()) {
            generatedKey = rs.getInt(1);
        }
        return generatedKey;
    }

    public static ResultSet getResult(String query) throws SQLException {
        DataBaseConnection dbconn = new DataBaseConnection();
        ResultSet res;
        res = dbconn.getResult(query);
        return res;
    }
}
