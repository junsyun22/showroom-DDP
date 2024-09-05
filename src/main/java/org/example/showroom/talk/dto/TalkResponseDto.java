
package org.example.showroom.talk.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class TalkResponseDto {

    private Long id;
    @JsonProperty("user_id")
    private String userId;

    private String question;

    @JsonProperty("area_size")
    private String areaSize;
    @JsonProperty("housemate_num")
    private String housemateNum;
    private LocalDateTime createdAt;
}
