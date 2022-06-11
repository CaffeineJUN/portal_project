package kr.ac.jejunu.diary.diary.service;

import kr.ac.jejunu.diary.diary.domain.Diary;
import kr.ac.jejunu.diary.diary.dto.AddDiaryResponseDto;
import kr.ac.jejunu.diary.diary.repository.DiaryRepository;
import kr.ac.jejunu.diary.user.domain.User;
import kr.ac.jejunu.diary.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class DiaryService {
    private final UserRepository userRepository;
    private final DiaryRepository diaryRepository;

//    다이어리 추가 기능 작성한 글과 이미지를 저장, 이때 이미지는 로컬의 /images 경로에 저장
    @Transactional
    public AddDiaryResponseDto addDiary(User user, MultipartFile image, String content) {
        User user1 = userRepository.findById(user.getId()).get();
        String path = System.getProperty("user.dir");
        Diary diary = new Diary();
        diary.setContent(content);
        if (image != null) {
            diary.setImagePath(path + "/front/public/images/" + save(image));
        }
        diary.addUser(user1);
        diaryRepository.save(diary);
        return new AddDiaryResponseDto(diary.getId(),diary.getImagePath(),diary.getContent());
    }

    public String save(MultipartFile file) {
        UUID uuid = UUID.randomUUID();
        File dest = new File("./front/public/images/" + uuid + ".jpg");
        try {
            makeFile(dest, ((MultipartFile) file).getBytes());
            return dest.getName();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void makeFile(File dest, byte[] bytes) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(dest);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            bufferedOutputStream.write(bytes);
            bufferedOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    유저의 다이어리 데이터를 배열 형태로 전부 가져옴
    @Transactional
    public List<AddDiaryResponseDto> getAllDiary(User user) {
        User user1 = userRepository.findById(user.getId()).get();
        List<Diary> diaryArrayList = user1.getDiaryArrayList();
        List<AddDiaryResponseDto> collect = diaryArrayList.stream().map(diary -> {
            return new AddDiaryResponseDto(diary.getId(), diary.getImagePath(), diary.getContent());
        })
                .collect(Collectors.toList());
        return collect;
    }

//    다이어리의 id 값을 입력 받으면 해당 다이어리는 삭제
    @Transactional
    public void deleteDiary(Long diaryId) {
        Optional<Diary> byId = diaryRepository.findById(diaryId);
        byId.orElseThrow(()->new IllegalArgumentException("다이어리가 없습니다."));
        Diary diary = byId.get();
        diaryRepository.delete(diary);
    }
}