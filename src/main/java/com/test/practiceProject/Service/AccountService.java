package com.test.practiceProject.Service;

import com.test.practiceProject.DTO.LoginRequest;
import com.test.practiceProject.Entity.LoginEntity;
import com.test.practiceProject.Error.BadRequestException;
import com.test.practiceProject.Repository.AccountRepository;
import com.test.practiceProject.config.auth.CustomUserDetails;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements UserDetailsService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) {
        // Kiểm tra xem user có tồn tại trong database không?
         LoginEntity user = accountRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(user);
    }

    public UserDetails loadUserById(int id) {
        // Kiểm tra xem user có tồn tại trong database không?
        LoginEntity user = accountRepository.findById(id);
        if (user == null) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            throw new BadRequestException("Không tìm thấy tài khoản tương ứng với id này",status);
        }
        return new CustomUserDetails(user);
    }

    @Transactional
    public void createUser(LoginRequest info) {
        // Builder pattern
        LoginEntity user = LoginEntity.builder()
                .username(info.getUsername())
                .role("ROLE_USER")
                .password(passwordEncoder.encode(info.getPassword()))
                .numberFailures(0)
                .build();

        accountRepository.save(checkIsValid(user));
//        LoginEntity user = new LoginEntity();
//        user.setUsername(info.getUsername());
//        user.setPassword(passwordEncoder.encode(info.getPassword()));
//        user.setRole("ROLE_USER");
//        accountRepository.save(checkIsValid(user));
    }

    private LoginEntity checkIsValid(LoginEntity user) {
        Optional<LoginEntity> existedUser = Optional.ofNullable(accountRepository.findByUsername(user.getUsername()));
        if (existedUser.isPresent()) {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            throw new BadRequestException("Đã tồn tại username này trong hệ thống. Xin vui lòng nhập username khác!", status);
        }
        return user;
    }

    public Authentication authenticate(LoginRequest authentication) throws AuthenticationException {
        String username = authentication.getUsername();
        String password = authentication.getPassword();
        Optional<LoginEntity> acc = Optional.ofNullable(accountRepository.findByUsername(username));
        if (acc.isEmpty()) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            throw new BadRequestException("Tài khoản không tồn tại.", status);
        }
        if (acc.get().getNumberFailures() != null &&  acc.get().getNumberFailures() > 5) {
            HttpStatus status = HttpStatus.FORBIDDEN;
            throw new BadRequestException("Tài khoản của bạn đã bị khóa. Vui lòng liên hệ quản trị.", status);
        }
        boolean result = passwordEncoder.matches(password, acc.get().getPassword());
        if (!result) {
            if (acc.get().getNumberFailures() == null) acc.get().setNumberFailures(0);
            acc.get().setNumberFailures(acc.get().getNumberFailures() + 1);
            accountRepository.saveAndFlush(acc.get());
            HttpStatus status = HttpStatus.BAD_REQUEST;
            throw new BadRequestException("Thông tìn tài khoản, mật khẩu không chính xác", status);
        }
        List<GrantedAuthority> grantedAuths = new ArrayList<>();
        grantedAuths.add(new SimpleGrantedAuthority(acc.get().getRole()));
        return new UsernamePasswordAuthenticationToken(username, null, grantedAuths);
    }

    public void logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(null, null, authentication);
        }

    }
}
