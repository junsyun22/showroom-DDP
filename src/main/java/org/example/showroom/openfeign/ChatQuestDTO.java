package org.example.showroom.openfeign;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatQuestDTO {
    @JsonProperty("user_id")
    private String userId;

    private String question;

    @JsonProperty("area_size")
    private String areaSize;
    @JsonProperty("housemate_num")
    private String housemateNum;
}
