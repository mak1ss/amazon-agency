package com.amazonagency.utils;

import com.amazonagency.model.report.Report;
import com.amazonagency.model.report.ReportSpecification;
import com.amazonagency.model.sales_and_traffic_by_asin.SalesAndTrafficByAsin;
import com.amazonagency.model.sales_and_traffic_by_date.SalesAndTrafficByDate;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class DataUpdater {

    private ObjectMapper mapper;
    private MongoTemplate template;
    private final String DATA_SOURCE_PATH = "C:/test_report.json";
    private long lastTimeModified;
    private Report report = new Report();

    @Autowired
    public DataUpdater(ObjectMapper mapper,
                       MongoTemplate template) {
        this.mapper = mapper;
        this.template = template;

        this.report = new Report();
        retrieveLastDataFromDb();
    }

    private void retrieveLastDataFromDb() {
        ReportSpecification specs = template.findOne(new Query(), ReportSpecification.class);
        if (specs != null) {
            this.report.setReportSpecification(specs);
        }
        List<SalesAndTrafficByAsin> byAsin = template.findAll(SalesAndTrafficByAsin.class,
                "salesAndTrafficByDate");
        if (byAsin != null && !byAsin.isEmpty()) {
            this.report.setSalesAndTrafficByAsin(byAsin);
        }
        List<SalesAndTrafficByDate> byDate = template.findAll(SalesAndTrafficByDate.class,
                "salesAndTrafficByDate");
        if (byDate != null && !byDate.isEmpty()) {
            this.report.setSalesAndTrafficByDate(byDate);
        }

    }

    @Scheduled(cron = "@daily")
    public void updateData() throws IOException {
        System.out.println("-------------------");
        System.out.println("Update date method invoked");
        System.out.println("-------------------");
        if (!checkIfUpdated()) {
            return;
        }

        Report report = mapper.readValue(new File(DATA_SOURCE_PATH), Report.class);

        updateReportSpecificationIfChanged(report);

        updateStatisticsByAsinIfChanged(report);

        updateStatisticsByDateIfChanged(report);
    }


    private void updateReportSpecificationIfChanged(Report report) {
        if (this.report.getReportSpecification().equals(report.getReportSpecification())) {
            return;
        }
        System.out.println("-------------------");
        System.out.println("Report specification has changed");
        System.out.println("-------------------");

        long modified = upsertReportSpecification(report.getReportSpecification());

        System.out.println(modified + " rows modified");
        System.out.println("-------------------");

        this.report.setReportSpecification(report.getReportSpecification());

    }

    private void updateStatisticsByAsinIfChanged(Report report) {
        if (this.report.getSalesAndTrafficByAsin().equals(report.getSalesAndTrafficByAsin())) {
            return;
        }
        System.out.println("-------------------");
        System.out.println("Report by asin has changed");
        System.out.println("-------------------");

        AtomicLong modified = new AtomicLong();
        List<SalesAndTrafficByAsin> deltaToInsert = new ArrayList<>(report.getSalesAndTrafficByAsin());
        deltaToInsert.removeAll(this.report.getSalesAndTrafficByAsin());

        deltaToInsert.forEach(s ->
                modified.addAndGet(insertStatisticsByAsin(s)));


        List<SalesAndTrafficByAsin> deltaToDelete = new ArrayList<>(this.report.getSalesAndTrafficByAsin());
        deltaToDelete.removeAll(report.getSalesAndTrafficByAsin());

        deltaToDelete.forEach(s -> modified.addAndGet(deleteStatisticsByAsin(s)));


        System.out.println(modified.get() + " rows modified");
        System.out.println("-------------------");

        this.report.setSalesAndTrafficByAsin(report.getSalesAndTrafficByAsin());
    }

    private void updateStatisticsByDateIfChanged(Report report) {
        if (this.report.getSalesAndTrafficByDate().equals(report.getSalesAndTrafficByDate())) {
            return;
        }
        System.out.println("-------------------");
        System.out.println("Report by date has changed");
        System.out.println("-------------------");

        AtomicLong modified = new AtomicLong();
        List<SalesAndTrafficByDate> delta = new ArrayList<>(report.getSalesAndTrafficByDate());
        delta.removeAll(this.report.getSalesAndTrafficByDate());

        delta.forEach(s ->
                modified.addAndGet(insertStatisticsByDate(s)));


        List<SalesAndTrafficByDate> deltaToDelete = new ArrayList<>(this.report.getSalesAndTrafficByDate());
        deltaToDelete.removeAll(report.getSalesAndTrafficByDate());

        deltaToDelete.forEach(s -> modified.addAndGet(deleteStatisticsByDate(s)));


        System.out.println(modified.get() + " rows modified");
        System.out.println("-------------------");

        this.report.setSalesAndTrafficByDate(report.getSalesAndTrafficByDate());
    }

    private long upsertReportSpecification(ReportSpecification reportSpecification) {
        Query query = new Query().addCriteria(Criteria.where("reportType").is(reportSpecification.getReportType()));

        Update update = new Update()
                .set("reportOptions", reportSpecification.getReportOptions())
                .set("dataStartTime", reportSpecification.getDataStartTime())
                .set("dataEndTime", reportSpecification.getDataEndTime())
                .set("marketplaceIds", reportSpecification.getMarketplaceIds());

        return template.upsert(query, update, "reportSpecification").getModifiedCount();
    }

    private long insertStatisticsByAsin(SalesAndTrafficByAsin salesAndTrafficByAsin) {
        System.out.println("UpsertStatisticsByAsin() invoked");

        Query query = new Query().addCriteria(Criteria.where("parentAsin").is(salesAndTrafficByAsin.getParentAsin()));
        SalesAndTrafficByAsin res = null;

        if (!template.exists(query, "salesAndTrafficByAsin")) {
            res = template.insert(salesAndTrafficByAsin);
            System.err.println("INSERTED new row to salesAndTrafficByAsin");
        }
        return res != null ? 1 : 0;
    }

    private long deleteStatisticsByAsin(SalesAndTrafficByAsin salesAndTrafficByAsin) {
        System.out.println("DeleteStatisticsByAsin() invoked");

        Query query = new Query().addCriteria(Criteria.where("parentAsin").is(salesAndTrafficByAsin.getParentAsin()));

        if (!template.exists(query, "salesAndTrafficByAsin")) {
            return 0;
        }
        System.err.println("Deleting in salesAndTrafficByAsin");
        return template.remove(query, "salesAndTrafficByAsin").getDeletedCount();

    }

    private long insertStatisticsByDate(SalesAndTrafficByDate salesAndTrafficByDate) {
        System.out.println("UpsertStatisticsByDate() invoked ");

        Query query = new Query().addCriteria(Criteria.where("date").is(salesAndTrafficByDate.getDate()));
        SalesAndTrafficByDate res = null;

        if (!template.exists(query, "salesAndTrafficByDate")) {
            res = template.insert(salesAndTrafficByDate);
            System.err.println("INSERTED new row to salesAndTrafficByDate");
        }
        return res != null ? 1 : 0;
    }

    private long deleteStatisticsByDate(SalesAndTrafficByDate salesAndTrafficByDate) {
        System.out.println("deleteStatisticsByDate() invoked");

        Query query = new Query().addCriteria(Criteria.where("date").is(salesAndTrafficByDate.getDate()));

        if (!template.exists(query, "salesAndTrafficByDate")) {
            return 0;
        }
        System.err.println("Deleting in salesAndTrafficByDate");
        return template.remove(query, "salesAndTrafficByDate").getDeletedCount();
    }

    private boolean checkIfUpdated() {
        File file = new File(DATA_SOURCE_PATH);
        return file.lastModified() > lastTimeModified;
    }


}