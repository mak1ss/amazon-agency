package com.amazonagency.services.interfaces;

import com.amazonagency.model.sales_and_traffic_by_date.SalesAndTrafficByDate;
import org.apache.coyote.BadRequestException;

import java.util.List;
import java.util.zip.DataFormatException;

public interface StatisticsByDateService {

    List<SalesAndTrafficByDate> getAll();

    SalesAndTrafficByDate getByDate(String date);

    List<SalesAndTrafficByDate> getByInterval(String dateFrom, String dateTo);
}
