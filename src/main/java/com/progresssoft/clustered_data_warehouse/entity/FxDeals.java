package com.progresssoft.clustered_data_warehouse.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "PROGRESSOFT_FX_DEALS", uniqueConstraints = {
        @UniqueConstraint(name = "dealUniqueId", columnNames = {"ORDERING_CURRENCY"})
})
@Getter
@Setter
@RequiredArgsConstructor
public class FxDeals {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "PROGRESSOFT_FX_DEALS_SEQUENCE",
            sequenceName = "PROGRESSOFT_FX_DEALS_SEQUENCE",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROGRESSOFT_FX_DEALS_SEQUENCE")
    private Long id;

    @Column(name = "DEAL_UNIQUE_ID")
    private String dealUniqueId;

    @Column(name = "ORDERING_CURRENCY")
    private String orderingCurrency;

    @Column(name = "TO_CURRENCY")
    private String toCurrency;

    @Column(name = "DEAL_TIME_STAMP")
    private String dealTimeStamp;

    @Column(name = "DEAL_AMOUNT")
    private BigDecimal dealAmount;

}
