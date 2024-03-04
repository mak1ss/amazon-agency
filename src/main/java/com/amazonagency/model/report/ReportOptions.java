package com.amazonagency.model.report;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@EqualsAndHashCode
@Document
public class ReportOptions {

    private String dateGranularity;

    private String asinGranularity;
}
