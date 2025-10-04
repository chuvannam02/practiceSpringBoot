package com.test.practiceProject.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Cấu trúc phản hồi chuẩn")
public class BaseResponse<T> {

    @Schema(description = "Thời điểm phản hồi (UTC)", example = "2025-10-04T12:30:15.123Z")
    @JsonProperty("timestamp")
    private Instant timestamp;

    @Schema(description = "Mã lỗi. '0' nghĩa là thành công", example = "0")
    @JsonProperty("error_code")
    private String error_code;

    @Schema(description = "Thông điệp phản hồi", example = "SUCCESS")
    @JsonProperty("message")
    private String message;

    @Schema(description = "Dữ liệu phụ, thường dùng cho phân trang")
    private Object object;

    @Schema(description = "Payload dữ liệu chính trả về")
    @JsonProperty("data")
    private T data;

    public BaseResponse() {
        this.timestamp = Instant.now();
        this.error_code = "0";
        this.message = "SUCCESS";
    }

    public BaseResponse(T data) {
        this();
        this.data = data;
    }

    public <T> void setObject(T properties) {
        this.object = properties;
    }
    public <T> void setPageResponseObject(PageResponse<T> page) {
        this.object = page;
    }

    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(data);
    }

    public static <T> BaseResponse<T> error(String errorCode, String message) {
        BaseResponse<T> response = new BaseResponse<>();
        response.setError_code(errorCode);
        response.setMessage(message);
        response.setData(null);
        return response;
    }
}
