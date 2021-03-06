package kr.ac.jejunu.diary.diary.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddDiaryResponseDto {
    private Long id;
    private String imagePath;
    private String content;
}