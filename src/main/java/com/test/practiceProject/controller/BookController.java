package com.test.practiceProject.controller;

import com.test.practiceProject.dto.BookDTO;
import com.test.practiceProject.dto.response.BaseResponse;
import com.test.practiceProject.entity.BookEntity;
import com.test.practiceProject.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

@RestController
@RequestMapping("/v1/book")
@Tag(name = "Book API", description = "Quản lý sách trong hệ thống")
public class BookController {

    @Autowired
    private BookService bookService;

    @Operation(summary = "Lưu thông tin sách", description = "Tạo mới một cuốn sách")
    @ApiResponse(responseCode = "200", description = "Lưu sách thành công",
        content = @Content(schema = @Schema(implementation = BaseResponse.class)))
    @PostMapping("/save")
    public ResponseEntity<BaseResponse<BookDTO>> save(@RequestBody BookDTO book) {
        bookService.save(book);
        return ResponseEntity.ok(BaseResponse.success(book));
    }

    @Operation(summary = "Lưu nhiều sách", description = "Tạo mới nhiều sách cùng lúc")
    @PostMapping("/many-save")
    public ResponseEntity<BaseResponse<List<BookDTO>>> saveMany(@RequestBody List<BookDTO> books) {
        books.forEach(bookService::save);
        return ResponseEntity.ok(BaseResponse.success(books));
    }

    @Operation(summary = "Tìm sách theo ID")
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<BaseResponse<BookDTO>> findById(
        @Parameter(description = "ID của sách", required = true) @PathVariable Integer id) {
        return ResponseEntity.ok(BaseResponse.success(bookService.findById(id)));
    }

    @Operation(summary = "Lấy tất cả sách")
    @GetMapping("/get-all")
    public ResponseEntity<BaseResponse<List<BookDTO>>> getAll() {
        return ResponseEntity.ok(BaseResponse.success(bookService.getAll()));
    }

    @Operation(summary = "Test varargs", description = "Demo gọi hàm có tham số biến đổi")
    @GetMapping("/test-varargs")
    public ResponseEntity<BaseResponse<List<String>>> testVariableArguments() {
        return ResponseEntity.ok(BaseResponse.success(
            bookService.testVariableArgumentsWithoutAnnotation("Chu", "Van", "Nam")
        ));
    }

    @Operation(summary = "Danh sách phân trang", description = "Lọc + phân trang sách")
    @GetMapping("")
    public ResponseEntity<BaseResponse<Object>> list(
        @RequestParam(value = "keyword", required = false) String keyword,
        @RequestParam(name = "page", defaultValue = "1") int pageNum,
        @RequestParam(name = "size", defaultValue = "10") int pageSize,
        @RequestParam(name = "sort", defaultValue = "name") String sort,
        @RequestParam(name = "direction", defaultValue = "DESC") String direction
    ) {
        return ResponseEntity.ok(BaseResponse.success(
            bookService.list(keyword, pageNum, pageSize, sort, direction)
        ));
    }

    @Operation(summary = "Xóa sách theo ID")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BaseResponse<String>> delete(@PathVariable Integer id) {
        boolean status = bookService.delete(id);
        if (status) {
            return ResponseEntity.ok(BaseResponse.success("Xoá thông tin sách thành công"));
        }
        return new ResponseEntity<>(BaseResponse.error("404", "Không tìm thấy sách"), HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Cập nhật một phần thông tin sách")
    @PatchMapping("/update-book/{id}")
    public ResponseEntity<BaseResponse<Object>> updatePartial(
        @PathVariable Integer id, @RequestBody BookDTO book) {
        Map<String, Object> data = new HashMap<>();
        addIfValid(data, "name", book.getName(), value -> !value.isBlank());
        addIfValid(data, "description", book.getDescription(), value -> !value.isBlank());
        addIfValid(data, "copies", book.getCopies(), value -> value > 0);
        addIfValid(data, "bookType", book.getBookType(), value -> value > 0);
        addIfValid(data, "status", book.getStatus(), value -> value > 0);

        return ResponseEntity.ok(BaseResponse.success(bookService.partialUpdateBook(data, id)));
    }

    @Operation(summary = "Tìm kiếm sách theo tên (PathVariable)")
    @GetMapping("/get-by-name/{name1}")
    public ResponseEntity<BaseResponse<String>> getByName(@PathVariable String name1) {
        return ResponseEntity.ok(BaseResponse.success("Tìm kiếm sách theo tên: " + name1));
    }

    @Operation(summary = "Tìm kiếm sách theo tên (RequestParam)")
    @GetMapping("/get-by-name")
    public ResponseEntity<BaseResponse<String>> getByName2(@RequestParam String name) {
        bookService.testVariableArguments("Chu", "Van", "Nam");
        return ResponseEntity.ok(BaseResponse.success("Tìm kiếm sách theo tên: " + name));
    }

    @Operation(summary = "Danh sách sách sắp xếp theo tên")
    @GetMapping("/get-list-books-sorted-by-name")
    public ResponseEntity<BaseResponse<Set<BookEntity>>> getListBooksSortedByName() {
        return ResponseEntity.ok(BaseResponse.success(bookService.getListBooksSortedByName()));
    }

    // util method
    private <T> void addIfValid(Map<String, Object> map, String key, T value, Predicate<T> isValid) {
        if (value != null && isValid.test(value)) {
            map.put(key, value);
        }
    }
}
