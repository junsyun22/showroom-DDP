package org.example.showroom.log.repository;

import org.example.showroom.log.entity.EventLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventLogRepository extends JpaRepository<EventLog, Long> {
    List<EventLog> findByEventType(String eventType);
    // 여러 이벤트 유형으로 로그를 조회 (AI 관련 이벤트 필터링)
    List<EventLog> findByEventTypeIn(List<String> eventTypes);
}

