package kr.ac.jejunu.diary.user.controller;

import kr.ac.jejunu.diary.resolver.Login;
import kr.ac.jejunu.diary.user.domain.User;
import kr.ac.jejunu.diary.user.dto.ResponseDto;
import kr.ac.jejunu.diary.user.dto.UserSignRequestDto;
import kr.ac.jejunu.diary.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @PostMapping("/api/register")
    public ResponseDto signUp(@RequestBody UserSignRequestDto userSignRequestDto){
        return userService.signUp(userSignRequestDto);
    }

    @PostMapping("/api/login")
    public ResponseEntity<ResponseDto> logIn(@RequestBody UserSignRequestDto userSignRequestDto, HttpServletRequest httpServletRequest){
        Optional<User> optionalUser = userService.logIn(userSignRequestDto);
        if(optionalUser.isPresent()){
            HttpSession session = httpServletRequest.getSession(true);
            User user = optionalUser.get();
            log.info("로그인:{}",user.getUserId());
            session.setAttribute("user", user);
            return new ResponseEntity<ResponseDto>(new ResponseDto(202,"로그인에 성공하였습니다."), HttpStatus.valueOf(202));
        }
        else{
            return new ResponseEntity<ResponseDto>(new ResponseDto(401,"로그인에 실패하였습니다."), HttpStatus.valueOf(401));
        }
    }

    @PostMapping("/api/logout")
    public ResponseDto logout(HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession(false);
        if(session!=null){
            session.invalidate();
        }
        return new ResponseDto(202,"로그아웃 되었습니다.");
    }

    @PutMapping("/api/user/update")
    public ResponseDto userUpdate(@Login User user,@RequestBody Map<String,String> password){
        userService.userUpdate(user,password.get("password"));
        return new ResponseDto(202,"업데이트가 완료되었습니다.");
    }

    @DeleteMapping("/api/user/delete")
    public ResponseDto userDelete(@Login User user){
        userService.delete(user);
        return new ResponseDto(202,"사용자 제거가 완료되었습니다.");
    }
}