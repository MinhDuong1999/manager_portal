package com.example.manager_users.common.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Builder
@Getter
@Setter
public class MessageResponse {
    @Schema(
            requiredMode = Schema.RequiredMode.NOT_REQUIRED,
            description = "The description of message"
    )
    private String description;

    @Schema(
            requiredMode = Schema.RequiredMode.NOT_REQUIRED,
            description = "The message code"
    )
    private String code;

    @Schema(
            requiredMode = Schema.RequiredMode.NOT_REQUIRED,
            description = "The list of details messages"
    )
    private List<Detail> details;

    @Builder
    @Setter
    @Getter
    public static class Detail {
        String key;
        String value;
    }
}
