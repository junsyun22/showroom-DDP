package org.example.showroom.talk.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Talk {


    public Talk(Long id, String memberId, String question, String areaSize, String housemateNum,LocalDateTime createdAt) {
        this.id = id;
        this.memberId = memberId;
        this.question = question;
        this.housemateNum = housemateNum;
        this.areaSize = areaSize;
        this.createdAt = createdAt;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String memberId;

    @Column(nullable = false, length = 1000)
    private String question;

    @Column(nullable = true)
    private String areaSize;

    @Column(nullable = true)
    private String housemateNum;

    @Column(nullable = false)
    private LocalDateTime createdAt;



    // 테스트 전용 생성자 (권장하지 않음)
    protected Talk(String memberId, String question, String areaSize ,String housemateNum ,LocalDateTime createdAt) {
        this.memberId = memberId;
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
