package com.test.practiceProject.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Thông tin phân trang")
public class PageResponse<T> {

    @Schema(description = "Tổng số trang", example = "5")
    private Integer totalPages;

    @Schema(description = "Có trang tiếp theo hay không", example = "true")
    private Boolean hasNext;

    @Schema(description = "Tổng số phần tử", example = "100")
    private Long totalElements;

    @Schema(description = "Có trang trước đó hay không", example = "false")
    private Boolean hasPrevious;

    @Schema(description = "Số trang hiện tại (bắt đầu từ 1)", example = "2")
    private Integer pageNumber;

    @Schema(description = "Danh sách dữ liệu trong trang")
    private List<T> data;

    public PageResponse(Page<T> pageResult) {
        this.totalPages = pageResult.getTotalPages();
        this.hasNext = pageResult.hasNext();
        this.hasPrevious = pageResult.hasPrevious();
        this.pageNumber = pageResult.getNumber() + 1;
        this.totalElements = pageResult.getTotalElements();
        this.data = pageResult.getContent();
    }

    public void copyPageInfor(Page<T> pageResult) {
        this.totalPages = pageResult.getTotalPages();
        this.hasNext = pageResult.hasNext();
        this.hasPrevious = pageResult.hasPrevious();
        this.pageNumber = pageResult.getNumber() + 1;
        this.totalElements = pageResult.getTotalElements();
    }

    public static <T> PageResponse<T> fromList(Page<T> pageResult) {
        // diamond operator
        PageResponse<T> response = new PageResponse<>();
        response.copyPageInfor(pageResult);
        response.setData(pageResult.getContent());
        return response;
    }
}
