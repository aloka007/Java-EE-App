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
import javax.faces.bean.ManagedBean;
 
@ManagedBean
public class BasicViewAJ {
     
    private String text;

    public BasicViewAJ() {
        text = "Test!";
    }
    
    
 
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
}
