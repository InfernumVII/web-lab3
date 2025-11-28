package com.infernumvii.controller;

import com.infernumvii.model.ResultEntity;
import com.infernumvii.service.ResultService;

import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;



@Named
@ViewScoped
@Getter 
@Setter
public class PointBean implements Serializable {

    private BigDecimal x;
    private BigDecimal y;
    private BigDecimal r = new BigDecimal("1");

    @Inject
    private ResultService resultService;

    @Inject
    private ResultsController resultsController;

    public void addPointFromGraph() {
        long startTime = System.nanoTime();
        var params = FacesContext.getCurrentInstance()
                .getExternalContext().getRequestParameterMap();
        
        try {
            String xParam = params.get("x");
            String yParam = params.get("y");
            String rParam = params.get("r");
    
            BigDecimal xVal = new BigDecimal(xParam);
            BigDecimal yVal = new BigDecimal(yParam);
            BigDecimal rVal = new BigDecimal(rParam);
    
            
            boolean isHit = resultService.checkArea(xVal, yVal, rVal); 
            
            ResultEntity result = new ResultEntity();
            result.setX(xVal); 
            result.setY(yVal);
            result.setR(rVal);
            result.setSuccess(isHit);


            long endTime = System.nanoTime();
            long executionTimeMicros = (endTime - startTime) / 1000;
            
            String currTimeStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                                    .format(new Date());
            result.setExecutionTime(executionTimeMicros);
            result.setCurrentTime(currTimeStr);
            
            resultService.save(result);
            resultsController.addResult(result);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void submit() {
        long startTime = System.nanoTime();
        
        boolean isHit = resultService.checkArea(x, y, r);
        
        long executionTime = (System.nanoTime() - startTime) / 1000; 
        String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        ResultEntity result = new ResultEntity();
        result.setX(x);
        result.setY(y);
        result.setR(r);
        result.setCurrentTime(currentTime);
        result.setExecutionTime(executionTime);
        result.setSuccess(isHit);

        resultService.save(result);
        
        resultsController.addResult(result);
    }
}