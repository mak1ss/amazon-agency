package com.amazonagency.services.implementations;

import com.amazonagency.model.sales_and_traffic_by_asin.SalesAndTrafficByAsin;
import com.amazonagency.repositories.StatisticsByAsinRepository;
import com.amazonagency.services.interfaces.StatisticsByAsinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticsByAsinServiceImpl implements StatisticsByAsinService {

    private StatisticsByAsinRepository repo;

    @Autowired
    public StatisticsByAsinServiceImpl (StatisticsByAsinRepository repo){
        this.repo = repo;
    }
    @Override
    public List<SalesAndTrafficByAsin> getAll() {
        return repo.findAll();
    }

    @Override
    public SalesAndTrafficByAsin getByAsin(String asin) {
        return repo.findByParentAsin(asin);
    }

    @Override
    public List<SalesAndTrafficByAsin> getByAsinList(String[] asins) {
        return repo.findByParentAsinList(asins);
    }
}
