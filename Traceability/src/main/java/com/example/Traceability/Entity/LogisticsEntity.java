package com.example.Traceability.Entity;

import java.time.LocalDateTime;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TC_LOGISTICS")
public class LogisticsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long logisticsId;

    private String LogisticName;

    private String logisticType;

    private String trasportMode;

    private String logisticPartner;

    private String sourceLocation;

    private String destinationlocation;

    private Date expectedDeliveryDate;

    private String logisticStatus;

    private String eventType;

    @Column(name = "select_product")
    private String selectProduct;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "logistics_time_stamp", nullable = false)
    private LocalDateTime timeStamp;
}
