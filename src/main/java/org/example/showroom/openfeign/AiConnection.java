package org.example.showroom.openfeign;

import org.example.showroom.talk.dto.TalkRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "aiTest", url="http://meta-ai.iptime.org:8282")
public interface AiConnection {
    @PostMapping(value="ask")
    ChatAnswerDTO postSomeData(@RequestBody TalkRequestDto talkRequestDto);
}
