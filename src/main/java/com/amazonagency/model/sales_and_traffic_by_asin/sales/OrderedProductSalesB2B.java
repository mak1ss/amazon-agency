package com.amazonagency.model.sales_and_traffic_by_asin.sales;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@AllArgsConstructor
@Document
@EqualsAndHashCode
public class OrderedProductSalesB2B {

    private int amount;

    private String currencyCode;
}