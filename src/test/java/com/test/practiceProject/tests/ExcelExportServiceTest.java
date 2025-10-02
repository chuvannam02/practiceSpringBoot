package com.test.practiceProject.tests;

import com.test.practiceProject.dto.UserData;
import com.test.practiceProject.service.ExportService;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.tests  *
 * @Author: ChuVanNam
 * @Date: 10/1/2025
 * @Time: 12:26 AM
 */

@SpringBootTest
class ExcelExportServiceTest {

    @Autowired
    @Qualifier("easyExcelExportService")
    private ExportService excelExportService;

    @Test
    void testExportExcelSmall() throws Exception {
        // fake dữ liệu nhỏ để test logic
        List<UserData> data = List.of(
                new UserData(1L, "Alice", "alice@example.com"),
                new UserData(2L, "Bob", "bob@example.com")
        );

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        excelExportService.exportData(data, os);

        byte[] bytes = os.toByteArray();
        assertTrue(bytes.length > 0, "File Excel phải có dữ liệu");

        // đọc lại bằng Apache POI để verify nội dung
        try (var is = new ByteArrayInputStream(bytes);
             var workbook = WorkbookFactory.create(is)) {
            var sheet = workbook.getSheetAt(0);

            assertEquals("ID", sheet.getRow(0).getCell(0).getStringCellValue());
            assertEquals("Alice", sheet.getRow(1).getCell(1).getStringCellValue());
            assertEquals("bob@example.com", sheet.getRow(2).getCell(2).getStringCellValue());
        }
    }

    @Test
    void testExportExcelMillionRows() throws Exception {
        int total = 1_000_000;
        List<UserData> data = new ArrayList<>(total);
        for (int i = 1; i <= total; i++) {
            data.add(new UserData((long) i, "User" + i, "user" + i + "@example.com"));
        }

        long start = System.currentTimeMillis();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        excelExportService.exportData(data, os);
        long end = System.currentTimeMillis();

        System.out.println("✅ Exported " + total + " rows in " + (end - start) + " ms");
        assertTrue(os.toByteArray().length > 0, "File Excel phải có dữ liệu");
    }
}
