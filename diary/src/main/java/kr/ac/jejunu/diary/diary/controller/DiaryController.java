package kr.ac.jejunu.diary.diary.controller;

import kr.ac.jejunu.diary.diary.domain.Diary;
import kr.ac.jejunu.diary.diary.dto.AddDiaryResponseDto;
import kr.ac.jejunu.diary.diary.service.DiaryService;
import kr.ac.jejunu.diary.resolver.Login;
import kr.ac.jejunu.diary.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class DiaryController {
    private final DiaryService diaryService;

    @GetMapping("/api/user/diary")
    public List<AddDiaryResponseDto> getAllDiary(@Login User user){
        return diaryService.getAllDiary(user);
    }

    @PostMapping("/api/user/diary")
    public AddDiaryResponseDto addDiary(@Login User user, @RequestParam(name = "image",required = false) MultipartFile image,
                                        @RequestParam(name = "content") String contentMap){
        return diaryService.addDiary(user,image,contentMap);
    }
}