package com.amazonagency.services.implementations;

import com.amazonagency.model.sales_and_traffic_by_date.SalesAndTrafficByDate;
import com.amazonagency.repositories.StatisticsByDateRepository;
import com.amazonagency.services.interfaces.StatisticsByDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticsByDateServiceImpl implements StatisticsByDateService {

    private StatisticsByDateRepository repo;

    @Autowired
    public StatisticsByDateServiceImpl(StatisticsByDateRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<SalesAndTrafficByDate> getAll() {
        return repo.findAll();
    }

    @Override
    public SalesAndTrafficByDate getByDate(String date) {
        if(!date.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
            throw new IllegalArgumentException("Incorrect date format. Should be using \"YYYY-MM-DD\" format");
        }

        return repo.findByDate(date);
    }

    @Override
    public List<SalesAndTrafficByDate> getByInterval(String dateFrom, String dateTo) {
        String regex = "^\\d{4}-\\d{2}-\\d{2}$";
        if(!(dateFrom.matches(regex) || dateTo.matches(regex))) {
            throw new IllegalArgumentException("Incorrect date format. Should be using \"YYYY-MM-DD\" format");
        }
        return repo.findByDateRange(dateFrom, dateTo);
    }
}
