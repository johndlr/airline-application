package com.juandlr.reservation.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor @NoArgsConstructor @Getter @Setter
@Schema(name = "Customer", description = "Schema to hold customer information")
public class CustomerDto {


    @Schema(
            description = "Represents the customer name", example = "Tony"
    )
    @NotEmpty(message = "Name cannot be null or empty")
    @Size(min = 2, max = 50, message = "The length of the name should be between 2 and 50 characters")
    private String name;

    @Schema(
            description = "Represents the customer last name", example = "Stark"
    )
    @NotEmpty(message = "Last name cannot be null or empty")
    @Size(min = 2, max = 50, message = "The length of the last name should be between 2 and 50 characters")
    private String lastName;

    @Schema(
            description = "Represents the customer email", example = "tonystark@example.com"
    )
    @NotEmpty(message = "Email cannot be null or empty")
    @Email(message = "Email should be valid")
    private String email;

    @Schema(
            description = "Represents the customer mobile number", example = "5512345678"
    )
    @NotEmpty(message = "Mobile number cannot be null or empty")
    @Size(min = 10, max = 10, message = "The length of the mobile number should be exactly 10 digits")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be only digitis and length of 10")
    private String mobileNumber;

    @Schema(
            description = "Represents the customer address",
            example = "{\n" +
                    "        \"street\": \"456 Secondary St\",\n" +
                    "        \"city\": \"Los Angeles\",\n" +
                    "        \"state\": \"CA\",\n" +
                    "        \"zipCode\": \"70850\"\n" +
                    "    }"
    )
    @Valid
    private AddressDto addressDto;
}
