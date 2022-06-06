package kr.ac.jejunu.diary.diary.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import kr.ac.jejunu.diary.user.domain.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity

public class Diary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String imagePath;
    private String content;

    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonManagedReference
    private User user;

    public void addUser(User user){
        this.user=user;
        this.user.getDiaryArrayList().add(this);
    }
}