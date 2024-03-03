package com.amazonagency.model.sales_and_traffic_by_date.sales;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@AllArgsConstructor
public class AverageSellingPriceB2B {

    private double amount;

    private String currencyCode;
}