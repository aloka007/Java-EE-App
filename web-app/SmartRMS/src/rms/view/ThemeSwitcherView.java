/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rms.view;

/**
 *
 * @author Tharinda
 */
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import rms.theme.Theme;
import rms.theme.ThemeService;

@ManagedBean
public class ThemeSwitcherView {
 
    private List<Theme> themes;
     
    @ManagedProperty("#{themeService}")
    private ThemeService service;
 
    @PostConstruct
    public void init() {
        themes = service.getThemes();
    }
     
    public List<Theme> getThemes() {
        return themes;
    } 
 
    public void setService(ThemeService service) {
        this.service = service;
    }
}
