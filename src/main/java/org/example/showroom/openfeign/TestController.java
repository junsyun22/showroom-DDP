package org.example.showroom.openfeign;

import org.example.showroom.talk.dto.TalkRequestDto;
import org.example.showroom.talk.dto.TalkResponseDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private AiConnection aiConnection;

    public TestController(AiConnection aiConnection) {
        this.aiConnection = aiConnection;
    }

    @PostMapping("aitest")
    public TalkResponseDto getTest5(@RequestBody ChatQuestDTO chatQuestDTO){
        System.out.println(chatQuestDTO);

        TalkResponseDto answer = aiConnection.postSomeData(chatQuestDTO);
        System.out.println(answer);
        return answer;
    }

}
