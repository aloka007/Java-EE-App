/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rms.view;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartSeries;
import rms.entity.Bill;
import rms.session.BillFacade;
import rms.common.ComTainer;
import rms.entity.Ingredient;
import rms.session.IngredientFacade;

/**
 *
 * @author Tharinda
 */
@ManagedBean(name = "AdmV")
@SessionScoped
public class AdminView {

    @PostConstruct
    public void init() {
        ingredientList = (List<Ingredient>) ingredientFacade.findAll();
        billList = (List<Bill>) billFacade.findAll();
        createDateModel();
        initBarModel();
    }

// <editor-fold defaultstate="collapsed" desc=" Sales Components ">
    @EJB
    private BillFacade billFacade;

    private LineChartModel salesChart;

    List<Bill> billList = new ArrayList<>();

    public List<Bill> getBillList() {
        return billList;
    }

    public void setBillList(List<Bill> billList) {
        this.billList = billList;
    }

    public LineChartModel getDateModel() {
        return salesChart;
    }

    private void createDateModel() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Random r = new Random();
        salesChart = new LineChartModel();
        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Series 1");
        series1.setSmoothLine(false);

        String mindate = "0";

        try {
            //billList = (List<Bill>)billFacade.findAll();
            //Date initDate = new SimpleDateFormat("yyyy-MM-dd").parse("2016-11-03");
            Date tempDate = new SimpleDateFormat("yyyy-MM-dd").parse("2016-11-02");
            Date lastDate = new SimpleDateFormat("yyyy-MM-dd").parse("2016-12-31");

            while (tempDate.before(lastDate)) {
                BigDecimal tempTotal = BigDecimal.valueOf(0);
                for (Bill bill : billList) {
                    if (bill.getDate().after(tempDate) && bill.getDate().before(ComTainer.addDays(tempDate, 1))) {
                        BigDecimal dec = bill.getSubTotal();
                        tempTotal = tempTotal.add(dec);
                    }
                }
                series1.set(df.format(tempDate), tempTotal.toBigInteger().intValue());
                tempDate = ComTainer.addDays(tempDate, 1);
            }
        } catch (ParseException ex) {
            Logger.getLogger(AdminView.class.getName()).log(Level.SEVERE, null, ex);
        }

        salesChart.addSeries(series1);
        salesChart.setAnimate(true);

        salesChart.setTitle("Zoom for Details");
        salesChart.setZoom(true);
        salesChart.getAxis(AxisType.Y).setLabel("Sales");
        DateAxis axis = new DateAxis("Date");
        axis.setTickAngle(-50);
        axis.setMin("2016-10-31");
        axis.setMax("2016-12-31");
        axis.setTickFormat("%b %#d, %y");

        salesChart.getAxes().put(AxisType.X, axis);
    }

// </editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Stocks Components">
    @EJB
    IngredientFacade ingredientFacade;
    List<Ingredient> ingredientList = new ArrayList<>();
    private BarChartModel stocksBarModel = new BarChartModel();
    
    private LineChartModel consumptionChart; 

    public LineChartModel getConsumptionChart() {
        return consumptionChart;
    }

    public BarChartModel getStocksBarModel() {
        return stocksBarModel;
    }

    private void initBarModel() {
        stocksBarModel.setTitle("Bar Chart");
        stocksBarModel.setAnimate(true);
        Axis xAxis = stocksBarModel.getAxis(AxisType.X);
        xAxis.setLabel("Ingredient");
        Axis yAxis = stocksBarModel.getAxis(AxisType.Y);
        yAxis.setLabel("Amount (grams)");
        
        ChartSeries bar = new ChartSeries();
        
        for (Ingredient ingredient : ingredientList) {
            if (ingredient.getUnit().equals("UNIT")) {
                bar.set(ingredient.getName(), ingredient.getAmount().multiply(BigDecimal.valueOf(1)).toBigInteger().intValue());
            } else {
                bar.set(ingredient.getName(), ingredient.getAmount().multiply(BigDecimal.valueOf(1000)).toBigInteger().intValue());
            }
        }
        stocksBarModel.addSeries(bar);
    }

//</editor-fold>
    public void navigate(String path) {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ec.redirect(ec.getRequestContextPath() + "/users/admin/" + path + ".xhtml");
        } catch (IOException ex) {
            Logger.getLogger(RecepView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
