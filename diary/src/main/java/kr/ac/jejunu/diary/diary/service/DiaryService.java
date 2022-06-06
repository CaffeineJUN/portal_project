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
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class DiaryService {
    private final UserRepository userRepository;
    private final DiaryRepository diaryRepository;

    @Transactional
    public AddDiaryResponseDto addDiary(User user, MultipartFile image, String content) {
        User user1 = userRepository.findById(user.getId()).get();
        String path = System.getProperty("user.dir");
        Diary diary = new Diary();
        diary.setContent(content);
        if (image != null) {
            diary.setImagePath(path + "/images/" + save(image));
        }
        diary.addUser(user1);
        diaryRepository.save(diary);
        return new AddDiaryResponseDto(diary.getImagePath(),diary.getContent());
    }

    public String save(MultipartFile file) {
        UUID uuid = UUID.randomUUID();
        File dest = new File("./images/" + uuid + ".jpg");
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
}
