package kr.ac.jejunu.diary.repository;

import kr.ac.jejunu.diary.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}