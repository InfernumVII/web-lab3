package com.infernumvii.service;

import com.infernumvii.model.ResultEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Named
@ApplicationScoped
public class ResultService {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("LabUnit");

    public boolean checkArea(BigDecimal xInput, BigDecimal yInput, BigDecimal rInput) {
        if (xInput == null || yInput == null || rInput == null) {
            return false;
        }
    
        double x = xInput.doubleValue();
        double y = yInput.doubleValue();
        double r = rInput.doubleValue();

        boolean isRect = (x >= 0) && (y >= 0) && (x <= r) && (y <= r / 2.0);
    
        boolean isTriangle = (x <= 0) && (y >= 0) && (y <= x + r / 2.0);
    
        boolean isCircle = (x <= 0) && (y <= 0) && (x * x + y * y <= r * r);
    
        return isRect || isTriangle || isCircle;
    }

    public void save(ResultEntity result) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(result);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<ResultEntity> findLastResults() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT r FROM ResultEntity r ORDER BY r.id DESC", ResultEntity.class)
                        .setMaxResults(18) 
                        .getResultList();
        } finally {
            em.close();
        }
    }
}