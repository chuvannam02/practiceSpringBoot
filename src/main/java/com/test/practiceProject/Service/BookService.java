package com.test.practiceProject.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.practiceProject.DTO.BookDTO;
import com.test.practiceProject.Entity.BookEntity;
import com.test.practiceProject.Error.BadRequestException;
import com.test.practiceProject.Error.ErrorCustom;
import com.test.practiceProject.Repository.BookRepository;
import com.test.practiceProject.Response.PageResponse;
import com.test.practiceProject.Utils.CommonUtils;
import com.test.practiceProject.config.type.BookType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.lang.reflect.Field;
import java.util.*;

@Service
public class BookService {
    private final static Logger log = LoggerFactory.getLogger(BookService.class);
    @Autowired
    BookRepository bookRepository;
    @Autowired
    private ObjectMapper objectMapper;

    public void save(BookEntity book) {
        bookRepository.findById(book.getBookId()).ifPresentOrElse(
                existingBook -> {
                    update(book);
                },
                () -> {
                    create(book);
                }
        );
    }

    private BookEntity checkExisted(BookEntity book) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        if (book.getName().isBlank()) throw new BadRequestException("exception.internalServerError", status);
        if (bookRepository.checkNameIsDuplicate(book.getName()) > 0) {
            Map<String, String> additionalInfo = new HashMap<>();
            additionalInfo.put("field", "Tên sách");
            throw new BadRequestException("existed", status, additionalInfo);
        }
        return book;
    }

    private void create(BookEntity book) {
        book.setBookType(BookType.SHORT_STORIES);
        bookRepository.save(checkExisted(book));
    }

    public void update(BookEntity book) {
        Optional<BookEntity> idOfBook = bookRepository.findById(book.getBookId());
        BookEntity b = checkExisted(book);
        if (idOfBook.isPresent()) {
            BookEntity m = idOfBook.get();
            m.setName(b.getName());
            m.setDescription(b.getDescription());
            m.setCopies(b.getCopies());
            m.setBookType(BookType.FICTION);
            bookRepository.save(m);
        } else {
            HttpStatus status = HttpStatus.NOT_FOUND;
            throw new BadRequestException("Không tìm thấy thông tin cuốn sách!", status);
        }
    }

    public BookDTO findById(Integer id) {
        Optional<BookEntity> book = bookRepository.findById(id);
        System.out.println(book);
        if (book.isPresent()) {
            return objectMapper.convertValue(book.get(), BookDTO.class);
        } else {
            HttpStatus status = HttpStatus.NOT_FOUND;
            throw new BadRequestException("Mã định danh không tồn tại!", status);
        }
    }

    public List<BookDTO> getAll() {
        List<BookEntity> books = bookRepository.findAll();
        System.out.println(books);
        List<BookDTO> bookDTOs = new ArrayList<>();
        for (BookEntity book : books) {
            bookDTOs.add(objectMapper.convertValue(book, BookDTO.class));
        }
        return bookDTOs;
    }

    public void testVariableArguments(String... args) {
        //varargs (variable arguments) cho phép một phương thức nhận một số lượng tham số không xác định thuộc 1 kiểu dữ liệu cụ thể
        // Ví dụ String... args thực chất là một mảng String []args nhưng cho phép truyền nhiều tham số riêng lẻ khi gọi phương thức, thay vì phải tạo một mảng thủ công
        // Phương thức có thể được gọi với bất kỳ số lượng tham số nào (Bao gồm không có tham số nào)
        for (String arg : args) {
            log.info(arg);
        }

        // Ví dụ gọi bookService.testVariableArguments("Chu", "Van", "Nam");
        // Sẽ in ra
        //2024-06-13T15:36:08.397+07:00  INFO 1188 --- [practiceProject] [nio-8080-exec-1] c.t.practiceProject.Service.BookService  : Chu
        //2024-06-13T15:36:08.397+07:00  INFO 1188 --- [practiceProject] [nio-8080-exec-1] c.t.practiceProject.Service.BookService  : Van
        //2024-06-13T15:36:08.397+07:00  INFO 1188 --- [practiceProject] [nio-8080-exec-1] c.t.practiceProject.Service.BookService  : Nam

        // Reflection API
        Class<BookEntity> book = BookEntity.class;
        // BookEntity book = new BookEntity();
        // Field[] fields = book.getClass().getDeclaredFields();
        Field[] fields = book.getDeclaredFields();
        ArrayList<String> methodNames = new ArrayList<>();
        for (Field method : fields) {
            methodNames.add(method.getName());
        }
        Collections.sort(methodNames);
        for (String name : methodNames) {
            log.info(name);
        }

    }

    // Phân trang
    public PageResponse<?> list(String keyword, int page, int size, String sort, String direction) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.valueOf(direction), sort));
        Page<BookEntity> list = bookRepository.getPagedList(CommonUtils.trimNullUpper(keyword), pageable);
        return PageResponse.fromList(list);
    }

    public boolean delete(Integer id) {
        boolean existed = bookRepository.existsById(id);
        if (!existed) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            throw new BadRequestException("Bản ghi không tồn tại", status);
        } else {
            bookRepository.deleteById(id);
            return true;
        }
    }

    public BookDTO partialUpdateBook(Map<String, Object> fields, int bookId) {
        Optional<BookEntity> existingBook = bookRepository.findById(bookId);
        if (existingBook.isEmpty()) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            throw new BadRequestException("Không tìm thấy bản ghi sách tương ứng với id= " + bookId, status);
        }

        BookEntity book = existingBook.get();
        fields.forEach((key, value) -> {
            Field field = null;
            try {
                field = BookEntity.class.getDeclaredField(key);
                field.setAccessible(true);
                if (key.equals("bookType")) {
                    value = BookType.valueOf((String) value);
                }
                field.set(book, value);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });

        BookEntity updatedBookEntity = bookRepository.save(book);
        BookDTO bookDTO = new BookDTO();
        BeanUtils.copyProperties(updatedBookEntity, bookDTO);

        return bookDTO;
    }
}
