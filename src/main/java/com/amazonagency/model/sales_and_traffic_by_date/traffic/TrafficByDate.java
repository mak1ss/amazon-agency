package com.amazonagency.model.sales_and_traffic_by_date.traffic;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@AllArgsConstructor
@EqualsAndHashCode
@Document
public class TrafficByDate {

    private int browserPageViews;

    private int browserPageViewsB2B;

    private int mobileAppPageViews;

    private int mobileAppPageViewsB2B;

    private int pageViews;

    private int pageViewsB2B;

    private int browserSessions;

    private int browserSessionsB2B;

    private int mobileAppSessions;

    private int mobileAppSessionsB2B;

    private int sessions;

    private int sessionsB2B;

    private double buyBoxPercentage;

    private double buyBoxPercentageB2B;

    private double orderItemSessionPercentage;

    private double orderItemSessionPercentageB2B;

    private double unitSessionPercentage;

    private int unitSessionPercentageB2B;

    private int averageOfferCount;

    private int averageParentItems;

    private int feedbackReceived;

    private int negativeFeedbackReceived;

    private int receivedNegativeFeedbackRate;

}
