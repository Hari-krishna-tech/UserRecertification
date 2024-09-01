package com.ms.UserRecertification.controller;

import com.ms.UserRecertification.model.ExampleRequest;
import com.ms.UserRecertification.model.OriginRequest;
import com.ms.UserRecertification.model.User;
import com.ms.UserRecertification.service.EmailService;
import com.ms.UserRecertification.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/origin")
public class UserRecertificationController {

    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;

    @GetMapping("/{reportProfitCenter}")
    public List<User> users(@PathVariable String reportProfitCenter) {
        List<User> users = userService.getUsers(reportProfitCenter);
        userService.generateExcelFile(users);
        return users;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendJustEmail(@RequestBody ExampleRequest exampleRequest) {
       emailService.justEmail(exampleRequest.getTo());
        return ResponseEntity.ok("Email sent successfully");
    }

    @PostMapping("/sendmail")
    public ResponseEntity<String> sendEmail(@RequestBody OriginRequest originRequest) {
       String[] keyUsers = originRequest.getKeyUsers();
        String[] primaryAndSecondaryApprover = originRequest.getPrimaryAndSecondaryApprovers();
        /*
        String reportProfitCenter = originRequest.getReportProfitCenter();
        List<User> users = userService.getUsers(reportProfitCenter);
        String excelPath = userService.generateExcelFile(users);
        */
        String excelPath = "E:\\UserRecertification\\Dockerfile";
        emailService.sendEmail(keyUsers, primaryAndSecondaryApprover,excelPath);
        return ResponseEntity.ok("Email sent successfully");
    }

}
