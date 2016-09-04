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
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import rms.entity.MenuItem;
import rms.session.MenuItemFacade;

public class ComTainer implements Serializable {

    public static boolean render = false;

    static HashMap keys = new HashMap();

    private static List<MenuItem> menu;
    
    public static HashMap<Integer,Integer> lisin_itmid = new HashMap();
    public static HashMap<Integer, MenuItem> itmid_menit = new HashMap();
    public static HashMap<Integer,Integer> itmid_lisin =new HashMap();



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
