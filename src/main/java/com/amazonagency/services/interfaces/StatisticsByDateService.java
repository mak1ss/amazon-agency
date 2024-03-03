package com.amazonagency.services.interfaces;

import com.amazonagency.model.sales_and_traffic_by_date.SalesAndTrafficByDate;

import java.util.List;

public interface StatisticsByDateService {

    List<SalesAndTrafficByDate> getAll();

    SalesAndTrafficByDate getByDate(String date);

    List<SalesAndTrafficByDate> getByInterval(String dateFrom, String dateTo);
}
