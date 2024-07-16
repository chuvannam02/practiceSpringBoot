package com.test.practiceProject.Service;

import com.test.practiceProject.Entity.UserEntity;
import com.test.practiceProject.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
//    @Autowired
//    UserRepository userRepository;
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }
}
