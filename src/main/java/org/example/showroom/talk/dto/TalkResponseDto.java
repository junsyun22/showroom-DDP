package org.example.showroom.talk.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class TalkResponseDto {

    private Long id;

    @JsonProperty("userId")
    private String userId;  // Member의 userId

    private String memberName;  // Member의 이름 추가

    private String question;

    @JsonProperty("area_size")
    private String areaSize;

    @JsonProperty("housemate_num")
    private String housemateNum;

    private LocalDateTime createdAt;
}
