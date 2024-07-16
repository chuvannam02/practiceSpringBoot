package com.test.practiceProject.Repository;

import com.test.practiceProject.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    // Anotation @Repository đánh dấu UserRepository là 1 Bean và chịu trách nhiệm giao tiếp với DB
    // SpringBoot sẽ tự tìm thấy và khởi tạo ra đối tượng UserRepository trong Context.
    // Việc tạo ra UserRepository hoàn toàn tự động và tự config vì chúng ta đã kế thưa JpaRepository
}
