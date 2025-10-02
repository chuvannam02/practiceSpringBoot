package com.test.practiceProject.service.impl;

import com.test.practiceProject.dto.UserData;
import com.test.practiceProject.service.ExportService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.Service.impl  *
 * @Author: ChuVanNam
 * @Date: 10/1/2025
 * @Time: 12:18 AM
 */

@Service("excelExportService")
public class ExcelExportService implements ExportService {

    /**
     * @param data
     * @param os
     */
    @Override
    public void exportData(List<UserData> data, OutputStream os) throws IOException {
        long start = System.currentTimeMillis();

        SXSSFWorkbook workbook = new SXSSFWorkbook(100); // giữ tối đa 100 row trong bộ nhớ
        Sheet sheet = workbook.createSheet("Users");

        // Header
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("ID");
        header.createCell(1).setCellValue("Name");
        header.createCell(2).setCellValue("Email");

        // Data
        int rowIdx = 1;
        for (UserData user : data) {
            Row row = sheet.createRow(rowIdx++);
            row.createCell(0).setCellValue(user.getId());
            row.createCell(1).setCellValue(user.getName());
            row.createCell(2).setCellValue(user.getEmail());
        }

        workbook.write(os);
        workbook.dispose(); // cleanup temp files

        long end = System.currentTimeMillis();
        System.out.println("SXSSF Export Time: " + (end - start) + " ms");
    }
}
