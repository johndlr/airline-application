package com.juandlr.customer.controller;

import com.juandlr.customer.constants.HttpConstants;
import com.juandlr.customer.dto.CustomerContactInfoDto;
import com.juandlr.customer.dto.CustomerDto;
import com.juandlr.customer.dto.ErrorResponseDto;
import com.juandlr.customer.dto.ResponseDto;
import com.juandlr.customer.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class CustomerController {
        private final CustomerService customerService;

        private final CustomerContactInfoDto customerContactInfoDto;

        @Operation(summary = "Create Customer REST API", description = "REST API to create a new customer")
        @ApiResponses({
                @ApiResponse(responseCode = "201", description = "HTTP Status CREATED", content = @Content(schema = @Schema(implementation = ResponseDto.class))),
                @ApiResponse(responseCode = "400", description = "HTTP Status BAD REQUEST", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
                @ApiResponse(responseCode = "409", description = "HTTP Status CONFLICT", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
                @ApiResponse(responseCode = "500", description = "HTTP Status INTERNAL SERVER ERROR", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
        })
        @PostMapping("/create")
        public ResponseEntity<ResponseDto> createCustomer(@Valid @RequestBody CustomerDto customerDto) {
                customerService.createCustomer(customerDto);
                return ResponseEntity
                        .status(HttpStatus.CREATED.value())
                        .body(new ResponseDto(HttpConstants.STATUS_201, HttpConstants.MESSAGE_201));
        }

        @Operation(summary = "Fetch Customer Details REST API", description = "REST API to fetch customer")
        @ApiResponses({
                @ApiResponse(responseCode = "200", description = "HTTP Status OK", content = @Content(schema = @Schema(implementation = CustomerDto.class))),
                @ApiResponse(responseCode = "400", description = "HTTP Status BAD REQUEST", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
                @ApiResponse(responseCode = "404", description = "HTTP Status NOT FOUND", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
                @ApiResponse(responseCode = "500", description = "HTTP Status INTERNAL SERVER ERROR", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
        })
        @GetMapping("/fetch")
        public ResponseEntity<CustomerDto> fetchCustomer(
                @Valid @NotEmpty(message = "Flight number can not be a null or empty") @Size(min = 10, max = 10, message = "The length of the mobile number should be exactly 10 digits") @RequestParam String mobileNumber) {
                CustomerDto customerDto = customerService.fetchCustomer(mobileNumber);
                return ResponseEntity.status(HttpStatus.OK.value()).body(customerDto);
        }

        @Operation(summary = "Update Customer Details REST API", description = "REST API to update customer")
        @ApiResponses({
                @ApiResponse(responseCode = "200", description = "HTTP Status OK", content = @Content(schema = @Schema(implementation = CustomerDto.class))),
                @ApiResponse(responseCode = "400", description = "HTTP Status BAD REQUEST", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
                @ApiResponse(responseCode = "404", description = "HTTP Status NOT FOUND", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
                @ApiResponse(responseCode = "500", description = "HTTP Status INTERNAL SERVER ERROR", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
        })
        @PutMapping("/update")
        public ResponseEntity<ResponseDto> updateCustomer(@Valid @RequestBody CustomerDto customerDto) {
                customerService.updateCustomer(customerDto);
                return ResponseEntity.status(HttpStatus.OK.value())
                        .body(new ResponseDto(HttpConstants.STATUS_200, HttpConstants.MESSAGE_200));
        }

        @Operation(summary = "Delete Customer Details REST API", description = "REST API to delete customer based on a mobile number")
        @ApiResponses({
                @ApiResponse(responseCode = "200", description = "HTTP Status OK", content = @Content(schema = @Schema(implementation = CustomerDto.class))),
                @ApiResponse(responseCode = "400", description = "HTTP Status BAD REQUEST", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
                @ApiResponse(responseCode = "404", description = "HTTP Status NOT FOUND", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
                @ApiResponse(responseCode = "500", description = "HTTP Status INTERNAL SERVER ERROR", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
        })
        @DeleteMapping("/delete")
        public ResponseEntity<ResponseDto> deleteCustomer(
                @Valid @NotEmpty(message = "Flight number can not be a null or empty") @Size(min = 10, max = 10, message = "The length of the mobile number should be exactly 10 digits") @RequestParam String mobileNumber) {
                customerService.deleteCustomer(mobileNumber);
                return ResponseEntity.status(HttpStatus.OK.value())
                        .body(new ResponseDto(HttpConstants.STATUS_200, HttpConstants.MESSAGE_200));
        }

        @Operation(summary = "Fetch Contact Info", description = "Contact information details that can be reached out in case of any issues")
        @ApiResponses({
                @ApiResponse(responseCode = "200", description = "HTTP Status OK", content = @Content(schema = @Schema(implementation = CustomerContactInfoDto.class))),
                @ApiResponse(responseCode = "400", description = "HTTP Status BAD REQUEST", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))),
                @ApiResponse(responseCode = "500", description = "HTTP Status INTERNAL SERVER ERROR", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))
        })
        @GetMapping("/contact-info")
        public ResponseEntity<CustomerContactInfoDto> getContactInfo() {
                return ResponseEntity.status(HttpStatus.OK).body(customerContactInfoDto);
        }
}
