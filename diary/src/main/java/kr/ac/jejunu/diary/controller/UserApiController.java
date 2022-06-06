package kr.ac.jejunu.diary.controller;

import kr.ac.jejunu.diary.dto.ResponseDto;
import kr.ac.jejunu.diary.dto.UserSignRequestDto;
import kr.ac.jejunu.diary.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseDto signUp(@RequestBody UserSignRequestDto userSignRequestDto){
        return userService.signUp(userSignRequestDto);
    }
}
