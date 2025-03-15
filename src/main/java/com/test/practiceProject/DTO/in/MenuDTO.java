package com.test.practiceProject.DTO.in;

import com.test.practiceProject.DTO.base.SortDTO;
import com.test.practiceProject.Utils.Validations.MaxLengthWithField;
import com.test.practiceProject.Utils.Validations.groups.Create;
import com.test.practiceProject.Utils.Validations.payloads.Severity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.DTO.in  *
 * @Author: ChuVanNam
 * @Date: 3/15/2025
 * @Time: 11:59 AM
 */

@Data
@Builder
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
