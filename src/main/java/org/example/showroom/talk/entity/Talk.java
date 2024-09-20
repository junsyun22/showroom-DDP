package org.example.showroom.talk.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Talk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userId", nullable = false)
    private String userId;

    @Column(nullable = false, length = 1000)
    private String question;

    @Column(nullable = true)
    private String areaSize;

    @Column(nullable = true)
    private String housemateNum;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public Talk(String userId, String question, String areaSize, String housemateNum, LocalDateTime createdAt) {
        this.userId = userId;
        this.question = question;
        this.areaSize = areaSize;
        this.housemateNum = housemateNum;
        this.createdAt = createdAt;
    }

    // 필요한 경우 특정 필드를 업데이트할 메서드를 제공
    public void updateQuestion(String newQuestion) {
        this.question = newQuestion;
    }
}