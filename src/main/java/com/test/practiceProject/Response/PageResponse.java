package com.test.practiceProject.Response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PageResponse<T> {
    private Integer totalPages;
    private Boolean hasNext;
    private Long totalElements;
    private Boolean hasPrevious;
    private Integer pageNumber;
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
