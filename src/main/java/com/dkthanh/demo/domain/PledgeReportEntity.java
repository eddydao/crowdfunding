package com.dkthanh.demo.domain;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class PledgeReportEntity {
    public static final String PLEDGE_REPORT_MAP = "pledgeReportMap";
    public static final String OPTION_PERCENT_REPORT_MAP = "optionPercentReportMap";
    public static final String TABLE_REPORT = "tableReport";

    private Integer projectId;
    private Long pledge;
    private Integer year;
    private Integer weekNumber;
    private Integer month;
    private String timestamp;
    private String username;
    private String email;
    private OffsetDateTime timestampOffset;

    private Integer optionId;
    private String optionName;
    private Double optionPercent;

    //get report: pledge by weeks
    public PledgeReportEntity(  Integer year, Integer weekNumber, Long pledge) {
        this.year = year;
        this.weekNumber = weekNumber;
        this.pledge = pledge;
    }

    // get report: option percentage
    public PledgeReportEntity(Integer optionId, String optionName, Double optionPercent) {
        this.optionId = optionId;
        this.optionName = optionName;
        this.optionPercent = optionPercent;
    }

    public PledgeReportEntity( OffsetDateTime timestamp, String username,String email,  String optionName, Long pledge) {
        this.timestampOffset = timestamp;
        this.username = username;
        this.email = email;
        this.optionName = optionName;
        this.pledge = pledge;
    }
}
