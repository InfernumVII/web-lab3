package com.infernumvii.model;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "results")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultEntity implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, precision = 10, scale = 4)
    private BigDecimal x; 

    @Column(nullable = false, precision = 10, scale = 4)
    private BigDecimal y;

    @Column(nullable = false, precision = 10, scale = 4)
    private BigDecimal r;

    @Column(name = "cur_time")
    private String currentTime;

    @Column(name = "exec_time")
    private Long executionTime;

    @Column(nullable = false)
    private boolean success; 
}