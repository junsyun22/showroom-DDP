
package org.example.showroom.talk.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class TalkResponseDto {

    private Long id;
    private String memberId;
    private String text;
    private LocalDateTime createdAt;
}
