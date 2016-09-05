/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rms.test;

/**
 *
 * @author Tharinda
 */
import rms.test.Order;
import rms.db.DataBaseConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import rms.db.DBpack;

@ManagedBean(name = "orderService")
@ApplicationScoped
public class OrderService {

    ResultSet res;
    String query;
    DataBaseConnection dbconn;
    
            

    public List<Order> createOrders(int size) {
        List<Order> list = new ArrayList<>();
        try {
            query = "SELECT * FROM `smart_rms`.`order`";
            res = DBpack.getResult(query);
            while (res.next()) {
                //int n = (int) (Math.random() * 10);
                list.add(new Order(res.getInt("order_no"), new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(res.getTimestamp("order_time")), res.getInt("table_no"), res.getString("cust_name")));
            }
        } catch (SQLException ex) {

            for (int i = 0; i < 8; i++) {
                //int n = (int) (Math.random() * 10);
                list.add(new Order(0, "today", 777, "tharinda"));
            }
        }

        return list;
    }

}
