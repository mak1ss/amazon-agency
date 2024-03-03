package com.amazonagency.repositories;

import com.amazonagency.model.sales_and_traffic_by_date.SalesAndTrafficByDate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatisticsByDateRepository extends MongoRepository<SalesAndTrafficByDate, String> {

    SalesAndTrafficByDate findByDate(String date);

    @Query("""
            {
             $and : [ 
                 { date: { $gte: ?0 } }, 
                 { date: { $lte: ?1 } } 
                  ]
            }
             """)
    List<SalesAndTrafficByDate> findByDateRange(String dateFrom, String dateTo);
}
