package rms.db;

//import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class DataBaseConnection {

    public Statement stmt;
    public ResultSet res;
    public Connection conn;

    public DataBaseConnection() {
        this.setConnection();
    }

    @SuppressWarnings("CallToThreadDumpStack")
    public void setConnection() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            //conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant","root","admin");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smart_rms", "root", "admin");
        } catch (ClassNotFoundException | SQLException e) {
        }
    }

    public Connection getConnection() {
        return this.conn;
    }

    public ResultSet getResult(String query) throws SQLException {
        try {
            stmt = conn.createStatement();
            res = stmt.executeQuery(query);

        } catch (Exception e) {
        }
        return res;
    }
}
