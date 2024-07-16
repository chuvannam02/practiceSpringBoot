package com.test.practiceProject.Repository;

import com.test.practiceProject.DTO.BookDTO;
import com.test.practiceProject.Entity.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BookRepository extends JpaRepository<BookEntity, Integer> {
    @Query(value = "select * from books as b where b.name like '%?1%'", nativeQuery = true)
    List<BookDTO> getALL(String keyword);

    List<BookDTO> findByNameAndDescription(String name, String description);
    @Query(value = "select count(*) from books as b where b.name = ?1", nativeQuery = true)
    Integer checkNameIsDuplicate(String name);

    @Query(value = "SELECT * " +
            "       from books b " +
            "       WHERE :keyword is null or b.name LIKE CONCAT('%', :keyword, '%') or b.description LIKE CONCAT('%', :keyword, '%')",
            nativeQuery = true
    )
    Page<BookEntity> getPagedList(@Param("keyword") String keyword, Pageable pageable);

}
