package com.amazonagency.model.report;

import com.amazonagency.model.sales_and_traffic_by_asin.SalesAndTrafficByAsin;
import com.amazonagency.model.sales_and_traffic_by_date.SalesAndTrafficByDate;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Report {
    private ReportSpecification reportSpecification = new ReportSpecification();

    private List<SalesAndTrafficByDate> salesAndTrafficByDate = new ArrayList<>();

    private List<SalesAndTrafficByAsin> salesAndTrafficByAsin = new ArrayList<>();
}
