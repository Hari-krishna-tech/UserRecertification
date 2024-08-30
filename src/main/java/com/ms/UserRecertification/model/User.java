package com.ms.UserRecertification.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class User {
    @Id
    private String username;
    private String countryName;
    private String system;
    private String reportProfitCenter;
    private String firstName;
    private String lastName;
    private String emailId;
    private String werks;
    private String og;
    private String roles;
}
