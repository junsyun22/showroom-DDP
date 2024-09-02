package org.example.showroom.talk.service;

import org.example.showroom.talk.repository.TalkRepository;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@EnableScheduling
public class TalkCleanupService {

    private final TalkRepository talkRepository;

    public TalkCleanupService(TalkRepository talkRepository) {
        this.talkRepository = talkRepository;
    }

    // 매일 자정에 1주일 이상 된 메시지 삭제
    @Scheduled(cron = "0 0 0 * * ?")
    public void cleanUpOldTalks() {
        LocalDateTime expiryDate = LocalDateTime.now().minusWeeks(1);
        talkRepository.deleteTalksOlderThan(expiryDate);
    }
}
