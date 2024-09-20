package org.example.showroom.talk.service;


import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class TalkCleanupServiceTest {
//
//    @Autowired
//    private TalkCleanupService talkCleanupService;
//
//    @Autowired
//    private TalkRepository talkRepository;
//
//    @BeforeEach
//    public void setUp() {
//        // 테스트 데이터 추가
//        Talk oldTalk = new Talk(1L, "Old message", "30평", "2명", LocalDateTime.now().minusWeeks(2));
//        talkRepository.save(oldTalk);
//
//        Talk recentTalk = new Talk(2L, "Recent message", "25평", "3명", LocalDateTime.now());
//        talkRepository.save(recentTalk);
//    }
//
//    @AfterEach
//    public void tearDown() {
//        // 테스트 후 데이터 정리
//        talkRepository.deleteAll();
//    }
//
//    @Test
//    public void testCleanUpOldTalks() {
//        // 초기 데이터 확인
//        List<Talk> allTalks = talkRepository.findAll();
//        assertThat(allTalks).hasSize(2);
//
//        // 1주일 이상 된 메시지 삭제
//        talkCleanupService.cleanUpOldTalks();
//
//        // 삭제 후 데이터 확인
//        List<Talk> remainingTalks = talkRepository.findAll();
//        assertThat(remainingTalks).hasSize(1);
//        assertThat(remainingTalks.get(0).getQuestion()).isEqualTo("Recent message");
//    }
}
