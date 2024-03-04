package com.amazonagency.model.report;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "_id")
@Document(collection = "reportSpecification")
public class ReportSpecification {

    private ObjectId _id;

    private String reportType;

    private ReportOptions reportOptions;

    private String dataStartTime;

    private String dataEndTime;

    private String[] marketplaceIds;
}
