package org.example.showroom.talk.service;

import org.example.showroom.talk.entity.Talk;
import org.example.showroom.talk.repository.TalkRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TalkCleanupServiceTest {

    @Autowired
    private TalkCleanupService talkCleanupService;

    @Autowired
    private TalkRepository talkRepository;

    @BeforeEach
    public void setUp() throws Exception {
        // 테스트 데이터 추가
        Talk oldTalk = new Talk("user1", "Old message");
        setField(oldTalk, "createdAt", LocalDateTime.now().minusWeeks(2)); // Reflection으로 필드 설정
        talkRepository.save(oldTalk);

        Talk recentTalk = new Talk("user2", "Recent message");
        setField(recentTalk, "createdAt", LocalDateTime.now());
        talkRepository.save(recentTalk);
    }

    @AfterEach
    public void tearDown() {
        // 테스트 후 데이터 정리
        talkRepository.deleteAll();
    }

    @Test
    public void testCleanUpOldTalks() {
        // 초기 데이터 확인
        List<Talk> allTalks = talkRepository.findAll();
        assertThat(allTalks).hasSize(2);

        // 1주일 이상 된 메시지 삭제
        talkCleanupService.cleanUpOldTalks();

        // 삭제 후 데이터 확인
        List<Talk> remainingTalks = talkRepository.findAll();
        assertThat(remainingTalks).hasSize(1);
        assertThat(remainingTalks.get(0).getText()).isEqualTo("Recent message");
    }

    private void setField(Talk talk, String fieldName, LocalDateTime value) throws Exception {
        Field field = Talk.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(talk, value);
    }
}
