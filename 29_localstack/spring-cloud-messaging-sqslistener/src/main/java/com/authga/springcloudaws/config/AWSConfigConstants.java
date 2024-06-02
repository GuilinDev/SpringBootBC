package com.authga.springcloudaws.config;

import com.amazonaws.regions.Regions;
import org.springframework.beans.factory.annotation.Value;

public class AWSConfigConstants {
    public static final String ACCESS_KEY = "test";
    public static final String SECRET_KEY = "test";
    public static final String ENDPOINT = "http://localhost:4566";
    public static final String US_EAST_1 = Regions.US_EAST_1.getName();

    @Value("${cloud.aws.sns.order-created.arn}")
    public static final String ORDER_CREATED_TOPIC = "order-created-topic";

    @Value("${cloud.aws.sqs.order-queue.url}")
    public static final String ORDER_QUEUE = "integration-analytics-queue";

    @Value("${cloud.aws.sqs.order-queue-2.url}")
    public static final String ORDER_QUEUE_2 = "integration-analytics-queue-2";
}
