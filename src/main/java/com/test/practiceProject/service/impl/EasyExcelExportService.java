package com.test.practiceProject.service.impl;

import com.alibaba.excel.EasyExcel;
import com.test.practiceProject.dto.UserData;
import com.test.practiceProject.service.ExportService;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.util.List;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.Service.impl  *
 * @Author: ChuVanNam
 * @Date: 10/1/2025
 * @Time: 12:20 AM
 */

@Service("easyExcelExportService")
public class EasyExcelExportService  implements ExportService {
    /**
     * @param data
     * @param os
     */
    @Override
    public void exportData(List<UserData> data, OutputStream os) {
        long start = System.currentTimeMillis();

        EasyExcel.write(os, UserData.class)
                .sheet("Users")
                .doWrite(data);

        long end = System.currentTimeMillis();
        System.out.println("EasyExcel Export Time: " + (end - start) + " ms");
    }
}
