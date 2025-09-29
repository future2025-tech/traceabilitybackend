package com.example.Traceability.Entity;

import java.time.LocalDateTime;
import com.example.Traceability.Constant.Category;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TC_PRODUCT")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_name")
    private String productName;

    @Enumerated(EnumType.STRING)
    @Column(name = "product_category")
    private Category productCategory;

    @Column(name = "product_event")
    private String productEvent;

    @Column(name = "co2emission")
    private Float co2Emission;

    @Column(name = "water_usage")
    private Float waterUsage;

    @Column(name = "energy_consumption")
    private Float energyConsumption;

    @Column(name = "waste_generated")
    private Float wasteGenerated;

    @Column(name = "product_event_type")
    private String productEventType;

    @Column(name = "product_time_stamp", nullable = false)
    private LocalDateTime timeStamp;

    @Column(name = "product_location", nullable = false)
    private String productLocation;

    @Column(name = "product_description")
    private String productDescription;
}
