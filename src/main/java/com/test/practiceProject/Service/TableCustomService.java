package com.test.practiceProject.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.practiceProject.DTO.in.TableCustomDTO;
import com.test.practiceProject.Entity.Table;
import com.test.practiceProject.Repository.TableCustomRepository;
import com.test.practiceProject.Utils.Enums.DataType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.Service  *
 * @Author: ChuVanNam
 * @Date: 6/21/2025
 * @Time: 11:51 AM
 */

@Service
@Slf4j
@RequiredArgsConstructor
public class TableCustomService {
    private final TableCustomRepository tableCustomRepository;
    private final ObjectMapper objectMapper;

    @Transactional
    public void save(TableCustomDTO tableCustomDTO) {
        log.info("Saving custom table: {}", tableCustomDTO.getName());
        Table table = convertToEntity(tableCustomDTO);
        tableCustomRepository.save(table);
        log.info("Table saved: {}", tableCustomDTO.getName());

    }

    private Table convertToEntity(TableCustomDTO tableCustomDTO) {
        Table table = new Table();
        table.setName(tableCustomDTO.getName());
        table.setDescription(tableCustomDTO.getDescription());
        table.setSchemaName(tableCustomDTO.getSchemaName());
        table.setTableType(tableCustomDTO.getTableType());
        table.setDataType(DataType.fromString(tableCustomDTO.getDataType()));
        table.setPrimaryKey(tableCustomDTO.getPrimaryKey());
        table.setForeignKey(tableCustomDTO.getForeignKey());
        table.setUniqueKey(tableCustomDTO.getUniqueKey());
        return table;
    }

    public Page<Table> getAll(Integer page, Integer size, String searchKey) {
        log.info("Fetching all custom tables with page: {}, size: {}, searchKey: {}, sortBy: {}, sortDirection: {}", page, size, searchKey);

        Pageable pageable = PageRequest.of(page - 1, size);
        return tableCustomRepository.searchByName((searchKey != null && !searchKey.trim().isEmpty()) ? searchKey : null, pageable);
    }
}
