package kr.ac.jejunu.diary.service;

import kr.ac.jejunu.diary.domain.User;
import kr.ac.jejunu.diary.dto.ResponseDto;
import kr.ac.jejunu.diary.dto.UserSignRequestDto;
import kr.ac.jejunu.diary.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    //회원정보를 저장하고 비밀번호는 BCrypt라이브러리를 이용하여 해싱
    public ResponseDto signUp(UserSignRequestDto userSignRequestDto) {
        checkUser(userSignRequestDto);
        User user=new User();
        user.setUserId(userSignRequestDto.getUserId());
        user.setPassword(BCrypt.hashpw(userSignRequestDto.getPassword(),BCrypt.gensalt()));
        userRepository.save(user);
        return new ResponseDto(202,"회원가입이 완료되었습니다.");
    }

    private void checkUser(UserSignRequestDto userSignRequestDto) {
        Optional<User> byUserId = userRepository.findByUserId(userSignRequestDto.getUserId());
        if(byUserId.isPresent()){
            throw new IllegalArgumentException("회원가입 실패");
        }
    }

    public Optional<User> logIn(UserSignRequestDto userSignRequestDto) {
        Optional<User> optionalUser = userRepository.findByUserId(userSignRequestDto.getUserId());
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            if(BCrypt.checkpw(userSignRequestDto.getPassword(), user.getPassword())){
                return Optional.of(user);
            }
            return Optional.empty();
        }
        return Optional.empty();
    }
}
