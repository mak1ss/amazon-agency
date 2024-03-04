package com.amazonagency.controllers;

import com.amazonagency.model.sales_and_traffic_by_date.SalesAndTrafficByDate;
import com.amazonagency.services.interfaces.StatisticsByDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/by-date")
public class SalesByDateController {

    private StatisticsByDateService service;

    @GetMapping("/all")
    public List<SalesAndTrafficByDate> getAll() {
        return service.getAll();
    }

    @GetMapping
    public SalesAndTrafficByDate getByDate(@RequestParam String date) {
        return service.getByDate(date);
    }

    @GetMapping("/in-range")
    public List<SalesAndTrafficByDate> getByDateRange(@RequestParam String fromDate, String dateTo) {
        return service.getByInterval(fromDate, dateTo);
    }

    @Autowired
    public void setStatisticsByDateService(StatisticsByDateService service) {
        this.service = service;
    }
}
