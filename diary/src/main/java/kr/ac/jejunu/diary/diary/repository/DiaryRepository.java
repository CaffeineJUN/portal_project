package kr.ac.jejunu.diary.diary.repository;

import kr.ac.jejunu.diary.diary.domain.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryRepository extends JpaRepository<Diary,Long> {
}