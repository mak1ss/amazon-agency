package com.amazonagency.model.sales_and_traffic_by_asin;

import com.amazonagency.model.sales_and_traffic_by_asin.sales.SalesByAsin;
import com.amazonagency.model.sales_and_traffic_by_asin.traffic.TrafficByAsin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@Document
public class SalesAndTrafficByAsin {

    private ObjectId _id;

    private String parentAsin;

    private SalesByAsin salesByAsin;

    private TrafficByAsin trafficByAsin;
}
