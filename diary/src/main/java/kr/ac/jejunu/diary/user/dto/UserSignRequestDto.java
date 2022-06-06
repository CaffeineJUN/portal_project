package kr.ac.jejunu.diary.user.dto;

import lombok.Data;

@Data
public class UserSignRequestDto {
    private String userId;
    private String password;
}
