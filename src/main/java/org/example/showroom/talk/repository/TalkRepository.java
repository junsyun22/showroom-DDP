package org.example.showroom.talk.repository;

import org.example.showroom.talk.entity.Talk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

public interface TalkRepository extends JpaRepository<Talk, Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Talk t WHERE t.createdAt < :expiryDate")
    void deleteTalksOlderThan(LocalDateTime expiryDate);
}
