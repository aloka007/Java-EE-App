/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rms.view;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.TabChangeEvent;
import rms.common.ComTainer;

/**
 *
 * @author Tharinda
 */
@ManagedBean(name = "ComV")
@SessionScoped
public class CommonView {

    private int selectedTab = 0;

    public void setSelectedTab(int selectedTab) { // selected tab
        this.selectedTab = selectedTab;
    }

    public int getSelectedTab() {
        return selectedTab;
    }

    public void onTabChange(TabChangeEvent event) {
        selectedTab = Integer.parseInt(event.getTab().getId().substring(3, 4));
    }
    
    public String formatDate(Date date){
        String dateString = new SimpleDateFormat("yyyy-MM-dd").format(date);
        return dateString;
    }

    public String statuSwitch(int m, short i) {
        String s = "";
        if (m == 1) {
            switch (i) {
                case 0:
                    s = "red";
                    break;
                case 1:
                    s = "darkorange";
                    break;
                case 2:
                    s = "lime";
                    break;
                case 3:
                    s = "turquoise";
                    break;
                case 4:
                    s = "yellow";
                    break;
                case 9:
                    s = "slategray";
                    break;
                default:
                    break;
            }

        } else if (m == 2) {
            switch (i) {
                case 0:
                    s = "New";
                    break;
                case 1:
                    s = "Accepted";
                    break;
                case 2:
                    s = "Ready";
                    break;
                case 3:
                    s = "Delivered";
                    break;
                case 4:
                    s = "Paid";
                    break;
                case 9:
                    s = "Cancelled";
                    break;
                default:
                    break;
            }
        }
        return s;
    }

    private boolean hiddenatrib = false;

    public boolean authorize(String utype) {
        hiddenatrib = false;
        try {
            String usertype = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("type");
            String username = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username");
            if (utype.equals("ADMIN")) {
                usertype = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("type");
            }
            int auth_id = (int) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("auth_id");
            int key_id = ComTainer.getKey(username);
            if (usertype.equals(utype) && key_id == auth_id) {
                hiddenatrib = true;
            }
        } catch (Exception e) {
        }
        return hiddenatrib;
        //return true;
    }
}
