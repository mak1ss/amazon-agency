package com.amazonagency.services.implementations;

import com.amazonagency.model.sales_and_traffic_by_date.SalesAndTrafficByDate;
import com.amazonagency.repositories.StatisticsByDateRepository;
import com.amazonagency.services.interfaces.StatisticsByDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = "by-date")
public class StatisticsByDateServiceImpl implements StatisticsByDateService {

    private StatisticsByDateRepository repo;

    @Autowired
    public StatisticsByDateServiceImpl(StatisticsByDateRepository repo) {
        this.repo = repo;
    }

    @Cacheable
    @Override
    public List<SalesAndTrafficByDate> getAll() {
        return repo.findAll();
    }

    @Cacheable
    @Override
    public SalesAndTrafficByDate getByDate(String date) {
        if(!date.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
            throw new RuntimeException("Incorrect date format. Should be using \"YYYY-MM-DD\" format");
        }

        Optional<SalesAndTrafficByDate> statistics = repo.findByDate(date);
        if(statistics.isEmpty()){
            throw new IllegalArgumentException("Unknown date provided");
        }

        return statistics.get();
    }

    @Cacheable
    @Override
    public List<SalesAndTrafficByDate> getByInterval(String dateFrom, String dateTo) {
        String regex = "^\\d{4}-\\d{2}-\\d{2}$";
        if(!(dateFrom.matches(regex) || dateTo.matches(regex))) {
            throw new RuntimeException("Incorrect date format. Should be using \"YYYY-MM-DD\" format");
        }

        Optional<List<SalesAndTrafficByDate>> statistics = repo.findByDateRange(dateFrom, dateTo);
        if(statistics.isEmpty() || statistics.get().isEmpty()){
            throw new IllegalArgumentException("Unknown date provided");
        }

        return statistics.get();
    }

    @Scheduled(fixedRate = 300_000)
    @CacheEvict(cacheNames = "by-date", allEntries = true)
    public void clearCache(){
    }
}
