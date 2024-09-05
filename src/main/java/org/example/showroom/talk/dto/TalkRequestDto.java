
package org.example.showroom.talk.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TalkRequestDto {

    @JsonProperty("user_id")
    private String userId;

    private String question;

    @JsonProperty("area_size")
    private String areaSize;
    @JsonProperty("housemate_num")
    private String housemateNum;
}
