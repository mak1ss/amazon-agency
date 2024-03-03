package com.amazonagency.repositories;

import com.amazonagency.model.sales_and_traffic_by_asin.SalesAndTrafficByAsin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatisticsByAsinRepository extends MongoRepository<SalesAndTrafficByAsin, String> {

    SalesAndTrafficByAsin findByParentAsin(String asin);

    @Query("""
           {
               parentAsin : {
                   $in : ?0
               }
           }
            """)
    List<SalesAndTrafficByAsin> findByParentAsinList(String[] asins);
}
