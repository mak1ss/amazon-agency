package com.amazonagency.model.sales_and_traffic_by_date;


import com.amazonagency.model.sales_and_traffic_by_date.sales.SalesByDate;
import com.amazonagency.model.sales_and_traffic_by_date.traffic.TrafficByDate;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(exclude = "_id")
@Document
public class SalesAndTrafficByDate {

    private ObjectId _id;

    private String date;

    private SalesByDate salesByDate;

    private TrafficByDate trafficByDate;
}
