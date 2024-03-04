package com.amazonagency.services.implementations;

import com.amazonagency.model.sales_and_traffic_by_asin.SalesAndTrafficByAsin;
import com.amazonagency.repositories.StatisticsByAsinRepository;
import com.amazonagency.services.interfaces.StatisticsByAsinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = "by-asin")
public class StatisticsByAsinServiceImpl implements StatisticsByAsinService {

    private StatisticsByAsinRepository repo;

    @Autowired
    public StatisticsByAsinServiceImpl (StatisticsByAsinRepository repo){
        this.repo = repo;
    }

    @Cacheable
    @Override
    public List<SalesAndTrafficByAsin> getAll() {
        return repo.findAll();
    }

    @Cacheable
    @Override
    public SalesAndTrafficByAsin getByAsin(String asin) {
        Optional<SalesAndTrafficByAsin> statistics = repo.findByParentAsin(asin);
        if(statistics.isEmpty()){
            throw new IllegalArgumentException("Unknown asin provided");
        }
        return statistics.get();
    }

    @Cacheable
    @Override
    public List<SalesAndTrafficByAsin> getByAsinList(String[] asins) {
        Optional<List<SalesAndTrafficByAsin>> statistics = repo.findByParentAsinList(asins);
        if(statistics.isEmpty() || statistics.get().isEmpty()){
            throw new IllegalArgumentException("Unknown asin provided");
        }
        return statistics.get();
    }

    @Scheduled(fixedRate = 300_000)
    @CacheEvict(cacheNames = "by-asin", allEntries = true)
    public void clearCache(){

    }


}
