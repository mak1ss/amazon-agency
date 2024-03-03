package com.amazonagency.services.interfaces;

import com.amazonagency.model.sales_and_traffic_by_asin.SalesAndTrafficByAsin;

import java.util.List;

public interface StatisticsByAsinService {

    List<SalesAndTrafficByAsin> getAll();

    SalesAndTrafficByAsin getByAsin(String asin);

    List<SalesAndTrafficByAsin> getByAsinList(String... asin);
}
