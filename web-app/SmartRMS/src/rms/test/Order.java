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
public class Order {
    private int orderNo;
    private String orderTime;
    private int tableNo;
    private String custName;
    private boolean accepted;

    public Order(int orderNo, String orderTime, int tableNo, String custNameString) {
        this.orderNo = orderNo;
        this.orderTime = orderTime;
        this.tableNo = tableNo;
        this.custName = custNameString;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public int getTableNo() {
        return tableNo;
    }

    public void setTableNo(int tableNo) {
        this.tableNo = tableNo;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustNameString(String custNameString) {
        this.custName = custNameString;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
}
