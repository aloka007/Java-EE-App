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
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
 
@ManagedBean
@ViewScoped
public class DataBean {
    
    List<DataB> dlist;
    
    @PostConstruct
    public void init() {
        dlist.add(new DataB(1, 2, 3, 4));
        dlist.add(new DataB(5, 6, 7, 8));
    }

    public DataBean() {
        dlist.add(new DataB(1, 2, 3, 4));
        dlist.add(new DataB(5, 6, 7, 8));
    }
    
    public List<DataB> getDlist(){
        return dlist;
    }
    
}
