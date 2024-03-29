/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rms.common;

/**
 *
 * @author Tharinda
 */
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import rms.entity.MenuItem;

public class ComTainer implements Serializable {

    public static boolean render = false;

    static HashMap keys = new HashMap();

    private static List<MenuItem> menu;

    public static HashMap<Integer, Integer> lisin_itmid = new HashMap();
    public static HashMap<Integer, MenuItem> itmid_menit = new HashMap();
    public static HashMap<Integer, Integer> itmid_lisin = new HashMap();

    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }

    public static Date getOnlyDate(Date fecha) {
        Date res = fecha;
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(fecha);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        res = calendar.getTime();

        return res;
    }

    public static void setKey(String userName, int authID) {
        keys.put(userName, authID);
    }

    public static int getKey(String userName) {
        return (int) keys.get(userName);
    }

    public static void delKey(String userName, String authID) {
        keys.remove(userName);
    }

    public static List<MenuItem> getMenu() {
        return menu;
    }

    public static void setMenu(List<MenuItem> aMenu) {
        menu = aMenu;
    }
}
