package kr.ac.jejunu.diary.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDto {
    private Integer stateCode;
    private String message;
}
