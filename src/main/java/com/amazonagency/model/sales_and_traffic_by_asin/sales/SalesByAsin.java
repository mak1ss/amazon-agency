package com.amazonagency.model.sales_and_traffic_by_asin.sales;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@Document
@EqualsAndHashCode
public class SalesByAsin {

    private OrderedProductSales orderedProductSales;

    private OrderedProductSalesB2B orderedProductSalesB2B;

    private int totalOrderItems;

    private int totalOrderItemsB2B;

    private int unitsOrdered;

    private int unitsOrderedB2B;
}
