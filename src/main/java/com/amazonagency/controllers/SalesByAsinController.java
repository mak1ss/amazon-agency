package com.amazonagency.controllers;

import com.amazonagency.model.sales_and_traffic_by_asin.SalesAndTrafficByAsin;
import com.amazonagency.services.interfaces.StatisticsByAsinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/by-asin")
public class SalesByAsinController {

    private StatisticsByAsinService service;

    @GetMapping("/all")
    public List<SalesAndTrafficByAsin> getAll(){
        return service.getAll();
    }

    @GetMapping("/{asin}")
    public SalesAndTrafficByAsin getByAsin(@PathVariable String asin){
        return service.getByAsin(asin);
    }

    @GetMapping("/in-range")
    public List<SalesAndTrafficByAsin> getByAsinList(@RequestParam String[] asins){
        return service.getByAsinList(asins);
    }

    @Autowired
    public void setService(StatisticsByAsinService service) {
        this.service = service;
    }
}
