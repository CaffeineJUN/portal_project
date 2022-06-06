package kr.ac.jejunu.diary.controller;

import kr.ac.jejunu.diary.domain.User;
import kr.ac.jejunu.diary.dto.ResponseDto;
import kr.ac.jejunu.diary.dto.UserSignRequestDto;
import kr.ac.jejunu.diary.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseDto signUp(@RequestBody UserSignRequestDto userSignRequestDto){
        return userService.signUp(userSignRequestDto);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDto> logIn(@RequestBody UserSignRequestDto userSignRequestDto, HttpServletRequest httpServletRequest){
        Optional<User> optionalUser = userService.logIn(userSignRequestDto);
        if(optionalUser.isPresent()){
            HttpSession session = httpServletRequest.getSession(true);
            session.setAttribute("user",optionalUser.get());
            return new ResponseEntity<ResponseDto>(new ResponseDto(202,"로그인에 성공하였습니다."), HttpStatus.valueOf(202));
        }
        else{
            return new ResponseEntity<ResponseDto>(new ResponseDto(401,"로그인에 실패하였습니다."), HttpStatus.valueOf(401));
        }
    }

    @PostMapping("/logout")
    public ResponseDto logout(HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession(false);
        if(session!=null){
            session.invalidate();
        }
        return new ResponseDto(202,"로그아웃 되었습니다.");
    }
}
