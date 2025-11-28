package com.infernumvii.controller;

import com.infernumvii.model.ResultEntity;
import com.infernumvii.service.ResultService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class ResultsController implements Serializable {

    @Inject
    private ResultService resultService;

    @Getter
    private List<ResultEntity> results;

    private static final int MAX_ROWS = 18;

    @PostConstruct
        public void init() {
            results = resultService.findLastResults();
            if (results == null) {
                results = new ArrayList<>();
            }
        }
    
        public void addResult(ResultEntity result) {
            results.add(0, result);
        
            if (results.size() > MAX_ROWS) {
                results.remove(results.size() - 1);
            }
        }
}