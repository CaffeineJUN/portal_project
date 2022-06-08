package kr.ac.jejunu.diary.diary.controller;

import kr.ac.jejunu.diary.diary.domain.Diary;
import kr.ac.jejunu.diary.diary.dto.AddDiaryResponseDto;
import kr.ac.jejunu.diary.diary.service.DiaryService;
import kr.ac.jejunu.diary.resolver.Login;
import kr.ac.jejunu.diary.user.domain.User;
import kr.ac.jejunu.diary.user.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class DiaryController {
    private final DiaryService diaryService;

//    다이어리 정보 가져오기 get
    @GetMapping("/api/user/diary")
    public List<AddDiaryResponseDto> getAllDiary(@Login User user){
        return diaryService.getAllDiary(user);
    }

//    다이어리 작성 기능 post, 작성 글과 이미지 파일 입력 받음
    @PostMapping("/api/user/diary")
    public AddDiaryResponseDto addDiary(@Login User user, @RequestParam(name = "image",required = false) MultipartFile image,
                                        @RequestParam(name = "content") String contentMap){
        return diaryService.addDiary(user,image,contentMap);
    }

//    다이어리 삭제 기능
    @DeleteMapping("/api/user/diary/{diaryId}")
    public ResponseDto deleteDiary(@PathVariable("diaryId")Long diaryId){
        diaryService.deleteDiary(diaryId);
        return new ResponseDto(202,"다이어리가 성공적으로 삭제되었습니다.");
    }
}