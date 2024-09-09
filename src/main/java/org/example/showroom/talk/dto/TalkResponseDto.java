package org.example.showroom.talk.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class TalkResponseDto {
    private Long id;
    @JsonProperty("userId")
    private String userId;  // Member's userId
    private String memberName;
    private String answer;
    @JsonProperty("area_size")
    private String areaSize;
    @JsonProperty("housemate_num")
    private String housemateNum;
    private LocalDateTime createdAt;
}