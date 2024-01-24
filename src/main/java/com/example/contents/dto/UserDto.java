package com.example.contents.dto;

import com.example.contents.entity.User;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String email;
    @Setter
    private String phone;
    @Setter
    private String bio;
    @Setter
    private String avatar;

    // static factory method
    public static UserDto fromEntity(User entity) {
        return new UserDto(
                entity.getId(),
                entity.getUsername(),
                entity.getEmail(),
                entity.getPhone(),
                entity.getBio(),
                entity.getAvatar()
        );
    }
}
