package com.ms.UserRecertification.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import com.ms.UserRecertification.model.User;
import com.ms.UserRecertification.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers(String reportProfitCenter) {
        return userRepository.getUsers(reportProfitCenter);
    }

    public String generateExcelFile(List<User> users) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Users");

        // Create a bold font
        Font boldFont = workbook.createFont();
        boldFont.setBold(true);

        // Create a cell style with the bold font
        CellStyle boldStyle = workbook.createCellStyle();
        boldStyle.setFont(boldFont);

        // setting the width of role column higher

//        private String username;
//        private String countryName;
//        private String system;
//        private String reportProfitCenter;
//        private String firstName;
//        private String lastName;
//        private String emailId;
//        private String werks;
//        private String og;
//        private String roles;
//        // Create header row
        Row headerRow = sheet.createRow(0);
        String[] headers = {"USERNAME", "COUNTRYNAME", "SYSTEM", "REPORTPROFITCENTER", "FIRSTNAME", "LASTNAME", "EMAILID", "WERKS", "OG", "ROLES", "CURRENT STATUS"};

        for(int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(boldStyle);


        }


//        headerRow.createCell(0).setCellValue("username");
//        headerRow.createCell(1).setCellValue("countryName");
//        headerRow.createCell(2).setCellValue("system");
//        headerRow.createCell(3).setCellValue("reportProfitCenter");
//        headerRow.createCell(4).setCellValue("firstName");
//        headerRow.createCell(5).setCellValue("lastName");
//        headerRow.createCell(6).setCellValue("emailId");
//        headerRow.createCell(7).setCellValue("werks");
//        headerRow.createCell(8).setCellValue("og");
//        headerRow.createCell(9).setCellValue("roles");
//        headerRow.createCell(10).setCellValue("Current Status");

        // Fill data
        int rowNum = 1;
        String reportProfitCenter= null;
        for (User user : users) {
            reportProfitCenter = user.getReportProfitCenter();
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(user.getUsername());
            row.createCell(1).setCellValue(user.getCountryName());
            row.createCell(2).setCellValue(user.getSystem());
            row.createCell(3).setCellValue(user.getReportProfitCenter());
            row.createCell(4).setCellValue(user.getFirstName());
            row.createCell(5).setCellValue(user.getLastName());
            row.createCell(6).setCellValue(user.getEmailId());
            row.createCell(7).setCellValue(user.getWerks());
            row.createCell(9).setCellValue(user.getRoles());

        }
        // auto sizing
        for(int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Write to file
        String filePath = "users"+"_"+reportProfitCenter+".xlsx";
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return filePath;
    }
}
