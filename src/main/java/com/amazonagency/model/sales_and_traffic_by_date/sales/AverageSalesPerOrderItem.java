package com.amazonagency.model.sales_and_traffic_by_date.sales;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@Document
@EqualsAndHashCode
public class AverageSalesPerOrderItem {
    private double amount;
    private String currencyCode;
}
