package com.juandlr.customer.dto;


import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "Response",
        description = "Schema to hold successful response information"
)
public record ResponseDto(String statusCode, String statusMsg) {
}
