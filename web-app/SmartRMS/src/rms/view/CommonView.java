/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rms.view;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.event.TabChangeEvent;
import rms.common.ComTainer;
import rms.entity.UserAccount;
import rms.session.UserAccountFacade;
import rms.transaction.UserManager;

/**
 *
 * @author Tharinda
 */
@ManagedBean(name = "ComV")
@SessionScoped
public class CommonView {

    @EJB
    UserAccountFacade userAccountFacade;

    @EJB
    UserManager userManager;

    @PostConstruct
    public void init() {
        try {

            List<UserAccount> allAccounts = (List<UserAccount>) userAccountFacade.findAll();
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);

            for (UserAccount account : allAccounts) {
                if (((String) session.getAttribute("username")).equals(account.getUsername())) {
                    userAccount = account;
                }
            }
        } catch (Exception e) {
        }
    }

    private String tempPassword = "default";

    public String getTempPassword() {
        return tempPassword;
    }

    public void setTempPassword(String tempPassword) {
        this.tempPassword = tempPassword;
    }

    private UserAccount userAccount = new UserAccount();

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public void saveUserAccount() {
        if (!"default".equals(tempPassword)) {
            userAccount.setPassword(tempPassword);
        }
        userManager.saveUser(userAccount);
    }

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

    public String formatDate(Date date) {
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
            if (usertype.equals("ADMIN")) {
                usertype = utype;//what was i thinking?
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
