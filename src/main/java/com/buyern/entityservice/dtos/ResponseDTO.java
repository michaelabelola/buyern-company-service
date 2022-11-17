package com.buyern.entityservice.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@AllArgsConstructor
//@NoArgsConstructor
@RequiredArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO<T> {
    /**
     * "00"= successful,
     * "03"= Bad Request,
     * "04"= entity already exist,
     * "05"= record not found,
     * "91"= other errors,
     */
    private String code;
    private String message;
    private T data;
    private String help;

    public ResponseDTO(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseDTO(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }


    @EqualsAndHashCode(callSuper = true)
    @Data
    @AllArgsConstructor
    @RequiredArgsConstructor
    public static class OfString extends ResponseDTO<String> {
        private String code;
        private String message;
        private String data;
        private String help;

        public OfString(String code, String message) {
            super(code, message);
            this.code = code;
            this.message = message;
        }

        public OfString(String code, String message, String data) {
            super(code, message, data);
            this.code = code;
            this.message = message;
            this.data = data;
        }

    }
}

