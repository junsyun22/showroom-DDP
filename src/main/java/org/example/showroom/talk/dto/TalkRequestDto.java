package org.example.showroom.talk.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TalkRequestDto {

    @JsonProperty("member_id")
    private Long memberId;  // userId 대신 memberId로 변경

    private String question;

    @JsonProperty("area_size")
    private String areaSize;

    @JsonProperty("housemate_num")
    private String housemateNum;
}
