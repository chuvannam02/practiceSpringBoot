package com.test.practiceProject.config.auth;

/**
 * @Project: practiceProject
 * @Package: com.test.practiceProject.config.auth  *
 * @Author: ChuVanNam
 * @Date: 3/16/2025
 * @Time: 4:43 PM
 */

import com.test.practiceProject.Repository.Angular.RoleMenuRepository;
import io.jsonwebtoken.Claims;
import java.util.Collections;
import java.util.List;

public class JwtGrantedAuthoritiesConverter {

    private final RoleMenuRepository roleMenuRepository;

    public JwtGrantedAuthoritiesConverter(RoleMenuRepository roleMenuRepository) {
        this.roleMenuRepository = roleMenuRepository;
    }

    @SuppressWarnings("unchecked")
    public List<String> convert(Claims claims) {
        // Giả sử trong claims có trường "roles" chứa danh sách role (nếu không, trả về danh sách rỗng)
        Object roles = claims.get("roles");
        if (roles instanceof List) {
            return (List<String>) roles;
        }
        return Collections.emptyList();
    }
}
