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
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartSeries;
import rms.entity.Bill;
import rms.session.BillFacade;
import rms.common.ComTainer;

/**
 *
 * @author Tharinda
 */
@ManagedBean(name = "AdmV")
@SessionScoped
public class AdminView {

    @PostConstruct
    public void init() {
        createDateModel();
        billList = (List<Bill>)billFacade.findAll();
    }

// <editor-fold defaultstate="collapsed" desc=" Sales Components ">
    @EJB
    private BillFacade billFacade;

    private LineChartModel salesChart;

    List<Bill> billList = new ArrayList<>();

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

//        series1.set("2014-01-01", 51);
//        series1.set("2014-01-06", 22);
//        series1.set("2014-01-12", 65);
//        series1.set("2014-01-18", 74);
//        series1.set("2014-01-24", 24);
//        series1.set("2014-01-30", 51);


//        for (int i = 1; i < 9; i++) {
//            series1.set("2016-12-0" + Integer.toString(i), r.nextInt(200000 - 50000) + 50000);
//        }
//        for (int i = 10; i < 31; i++) {
//            series1.set("2016-12-" + Integer.toString(i), r.nextInt(200000 - 50000) + 50000);
//        }

        String mindate = "0";

        try {
            billList = (List<Bill>)billFacade.findAll();
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
    public void navigate(String path) {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ec.redirect(ec.getRequestContextPath() + "/users/admin/" + path + ".xhtml");
        } catch (IOException ex) {
            Logger.getLogger(RecepView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
