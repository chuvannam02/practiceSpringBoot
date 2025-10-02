package com.test.practiceProject.service;

import com.test.practiceProject.dto.UserData;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.Service  *
 * @Author: ChuVanNam
 * @Date: 10/1/2025
 * @Time: 12:16 AM
 */

public interface ExportService {
    public void exportData(List<UserData> data, OutputStream os) throws IOException;
}
