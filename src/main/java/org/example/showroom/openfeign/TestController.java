package org.example.showroom.openfeign;

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
    public ChatAnswerDTO getTest5(@RequestBody ChatQuestDTO chatQuest){
        System.out.println(chatQuest);

        ChatAnswerDTO answer = aiConnection.postSomeData(chatQuest);
        System.out.println(answer);
        return answer;
    }

}
