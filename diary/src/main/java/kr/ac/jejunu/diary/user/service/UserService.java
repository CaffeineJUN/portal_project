package kr.ac.jejunu.diary.user.service;

import kr.ac.jejunu.diary.user.domain.User;
import kr.ac.jejunu.diary.user.dto.ResponseDto;
import kr.ac.jejunu.diary.user.dto.UserSignRequestDto;
import kr.ac.jejunu.diary.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

//    프론트에서 입력 받은 id,pw를 저장, 이때 pw는 BCrypt를 사용하여 해싱
    @Transactional
    public ResponseDto signUp(UserSignRequestDto userSignRequestDto) {
        checkUser(userSignRequestDto);
        User user=new User();
        user.setUserId(userSignRequestDto.getUserId());
        user.setPassword(BCrypt.hashpw(userSignRequestDto.getPassword(),BCrypt.gensalt()));
        userRepository.save(user);
        return new ResponseDto(202,"회원가입이 완료되었습니다.");
    }

//    입력한 id,pw를 db의 id,pw와 비교하여 동일하다면 로그인 성공
    @Transactional
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

//    기존의 유저 정보를 찾고 해당 유저의 pw를 변경, 변경된 pw 또한 해싱
    @Transactional
    public void userUpdate(User user, String password) {
        Optional<User> id = userRepository.findById(user.getId());
        User user1 = id.get();
        user1.setPassword(BCrypt.hashpw(password,BCrypt.gensalt()));

    }

//    유저의 id를 찾고 해당 유저 삭제
    @Transactional
    public void delete(User user) {
        Optional<User> byId = userRepository.findById(user.getId());
        userRepository.delete(byId.get());
    }

    private void checkUser(UserSignRequestDto userSignRequestDto) {
        Optional<User> byUserId = userRepository.findByUserId(userSignRequestDto.getUserId());
        if(byUserId.isPresent()){
            throw new IllegalArgumentException("회원가입 실패");
        }
    }
}