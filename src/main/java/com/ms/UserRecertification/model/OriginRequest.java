package com.ms.UserRecertification.model;

import lombok.Data;

@Data
public class OriginRequest {
    private String reportProfitCenter;
    private String[] keyUsers;
    private String[] primaryAndSecondaryApprovers;

}
