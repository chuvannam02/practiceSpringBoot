package com.test.practiceProject.Controller;

import com.test.practiceProject.DTO.UserData;
import com.test.practiceProject.Service.impl.EasyExcelExportService;
import com.test.practiceProject.Service.impl.ExcelExportService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.Controller  *
 * @Author: ChuVanNam
 * @Date: 10/1/2025
 * @Time: 12:24 AM
 */

@RestController
public class ExcelController {

    private final ExcelExportService poiService;
    private final EasyExcelExportService easyService;

    public ExcelController(ExcelExportService poiService, EasyExcelExportService easyService) {
        this.poiService = poiService;
        this.easyService = easyService;
    }

    // Fake generate 1 triệu dòng
    private List<UserData> generateData(int count) {
        List<UserData> list = new ArrayList<>(count);
        for (int i = 1; i <= count; i++) {
            UserData u = new UserData();
            u.setId((long) i);
            u.setName("User_" + i);
            u.setEmail("user" + i + "@example.com");
            list.add(u);
        }
        return list;
    }

    @GetMapping("/export/poi")
    public void exportWithPOI(HttpServletResponse response) throws Exception {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=users-poi.xlsx");

        List<UserData> data = generateData(1_000_000);
        poiService.exportData(data, response.getOutputStream());
    }

    @GetMapping("/export/easyexcel")
    public void exportWithEasyExcel(HttpServletResponse response) throws Exception {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=users-easy.xlsx");

        List<UserData> data = generateData(1_000_000);
        easyService.exportData(data, response.getOutputStream());
    }
}
