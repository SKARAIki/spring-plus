package org.example.expert.domain.common.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.expert.domain.common.enums.UserRole;

@Getter
public class AuthUser {

    private final Long id;
    private final String email;
    private final String nickName;
    private final UserRole userRole;

    public AuthUser(Long id, String email, String nickName, UserRole userRole) {
        this.id = id;
        this.email = email;
        this.nickName = nickName;
        this.userRole = userRole;
    }
}
