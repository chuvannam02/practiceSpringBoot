package com.test.practiceProject.dto.in;

import com.test.practiceProject.dto.base.SortDTO;
import com.test.practiceProject.utils.Validations.MaxLengthWithField;
import com.test.practiceProject.utils.Validations.groups.Create;
import com.test.practiceProject.utils.Validations.payloads.Severity;
import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.DTO.in  *
 * @Author: ChuVanNam
 * @Date: 3/15/2025
 * @Time: 11:59 AM
 */

@Data
@SuperBuilder
public class MenuDTO extends SortDTO {
    private Integer id;

    private String code;

    @MaxLengthWithField(
            max = 50,
            fieldLabel = "Tên người dùng",
            groups = {Create.class},
            payload = {Severity.Critical.class}
    )
    private String name;

    private String url;

    private Integer parentId;

    private Integer appId;

    // Với status
    //    1. Hiển thị
    //    0. Không hiển thị
    private Integer status;
}
