package com.example.manager_users.common.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CommonResponse<T> {
    @Schema(
            requiredMode = Schema.RequiredMode.NOT_REQUIRED,
            description = "The message API response"
    )
    private MessageResponse message;
    @Schema(
            requiredMode = Schema.RequiredMode.REQUIRED,
            description = "the API data response"
    )
    private T data;
    @Schema(
            requiredMode = Schema.RequiredMode.REQUIRED,
            description = "Whether the API response is successful"
    )
    private boolean isSuccess;

    @Schema(
            requiredMode = Schema.RequiredMode.NOT_REQUIRED,
            description = "Total pages response with a pageable API"
    )
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer totalPages;

    private Long totalRows;

    @Schema(
            requiredMode = Schema.RequiredMode.NOT_REQUIRED,
            description = "Current page number response with pageable API"
    )
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer page;

    @Schema(
            requiredMode = Schema.RequiredMode.NOT_REQUIRED,
            description = "Current page size response with pageable API"
    )
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer size;

    public static <T> CommonResponse<T> createSuccessData(T data) {
        return CommonResponse.<T>builder().data(data).isSuccess(true).build();
    }

    public static <T> CommonResponse<T> createSuccessData(T data, MessageResponse message) {
        return CommonResponse.<T>builder().data(data).isSuccess(true).message(message).build();
    }
}
