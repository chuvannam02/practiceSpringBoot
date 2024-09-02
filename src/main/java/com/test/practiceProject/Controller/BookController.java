package com.test.practiceProject.Controller;

import com.test.practiceProject.DTO.BookDTO;
import com.test.practiceProject.Entity.BookEntity;
import com.test.practiceProject.Response.BaseResponse;
import com.test.practiceProject.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

@RestController
@RequestMapping("/v1/book")
@CrossOrigin(origins = "*")

public class BookController {
    @Autowired
    BookService bookService;
    @PostMapping("/save")
    public ResponseEntity<BaseResponse> save(@RequestBody BookDTO book) {
        BaseResponse baseResponse = new BaseResponse();
        bookService.save(book);

        return new ResponseEntity<BaseResponse>(baseResponse, HttpStatus.OK);
    }

    @PostMapping("/many-save")
    public ResponseEntity<BaseResponse> saveMany(@RequestBody List<BookDTO> books) {
        BaseResponse baseResponse = new BaseResponse();
        // for-each loop in Java 8
        for (BookDTO book : books) {
            bookService.save(book);
        }

        return new ResponseEntity<BaseResponse>(baseResponse, HttpStatus.OK);
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<BaseResponse> findById(@PathVariable Integer id) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setObject(bookService.findById(id));

        return new ResponseEntity<BaseResponse>(baseResponse, HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public ResponseEntity<BaseResponse> getAll() {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setObject(bookService.getAll());

        return new ResponseEntity<BaseResponse>(baseResponse, HttpStatus.OK);
    }

    @GetMapping("/test-varargs")
    public ResponseEntity<BaseResponse> testVariableArguments() {
        BaseResponse baseResponse = new BaseResponse();
        bookService.testVariableArguments("Chu", "Van", "Nam");
        baseResponse.setPageResponseObject(null);

        return new ResponseEntity<BaseResponse>(baseResponse, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<BaseResponse> list(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(name = "page", required = false, defaultValue = "1") int pageNum,
            @RequestParam(name = "size", required = false, defaultValue = "10") int pageSize,
            @RequestParam(name = "sort", defaultValue = "name", required = false) String sort,
            @RequestParam(name = "direction", defaultValue = "DESC", required = false) String direction
    ) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setPageResponseObject(bookService.list(keyword, pageNum, pageSize, sort, direction));
        return new ResponseEntity<BaseResponse>(baseResponse, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BaseResponse> delete(@PathVariable Integer id) {
        BaseResponse baseResponse = new BaseResponse();
        boolean status = bookService.delete(id);
        if (status) baseResponse.setMessage("Xoá thông tin sách thành công");

        return new ResponseEntity<BaseResponse>(baseResponse, HttpStatus.OK);
    }

    @PatchMapping("/update-book/{id}")
    public ResponseEntity<BaseResponse> updatePartial(@PathVariable Integer id,@RequestBody BookDTO book) {
        BaseResponse baseResponse = new BaseResponse();
        Map<String, Object> data = new HashMap<>();
        addIfValid(data, "name", book.getName(), value -> !value.isBlank());
        addIfValid(data, "description", book.getDescription(), value -> !value.isBlank());
        addIfValid(data, "copies", book.getCopies(), value -> value > 0);
        addIfValid(data, "bookType", book.getBookType(), value -> value > 0);
        // Ctrl + D: Duplicate line below
        addIfValid(data, "status", book.getStatus(), value -> value > 0);
        baseResponse.setObject(bookService.partialUpdateBook(data, id));

        return new ResponseEntity<BaseResponse>(baseResponse, HttpStatus.OK);
    }

    private <T> void addIfValid(Map<String, Object> map, String key, T value, Predicate<T> isValid) {
        if (value != null && isValid.test(value)) {
            map.put(key, value);
        }
    }
}
