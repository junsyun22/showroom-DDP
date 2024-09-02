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

    @Column(nullable = false)
    private String memberId;

    @Column(nullable = false, length = 1000)
    private String text;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public Talk(String memberId, String text) {
        this.memberId = memberId;
        this.text = text;
        this.createdAt = LocalDateTime.now();
    }

    // 테스트 전용 생성자 (권장하지 않음)
    protected Talk(String memberId, String text, LocalDateTime createdAt) {
        this.memberId = memberId;
        this.text = text;
        this.createdAt = createdAt;
    }

    // 필요한 경우 특정 필드를 업데이트할 메서드를 제공
    public void updateText(String newText) {
        this.text = newText;
    }

}
