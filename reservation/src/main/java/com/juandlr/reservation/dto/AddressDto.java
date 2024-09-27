package com.juandlr.reservation.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor @Getter @Setter
@Schema(
        name = "Address",
        description = "Schema to hold customer address"
)
public class AddressDto {

    @Schema(
            description = "Represents the street", example = "Williams Street"
    )
    @NotEmpty(message = "Street cannot be null or empty")
    @Size(min = 2, max = 100, message = "The length of the street should be between 2 and 100 characters")
    private String street;

    @Schema(
            description = "Represents the city", example = "Dallas"
    )
    @NotEmpty(message = "City cannot be null or empty")
    @Size(min = 2, max = 50, message = "The length of the city should be between 2 and 50 characters")
    private String city;

    @Schema(
            description = "Represents the state", example = "TX"
    )
    @NotEmpty(message = "State cannot be null or empty")
    @Size(min = 2, max = 50, message = "The length of the state should be between 2 and 50 characters")
    private String state;

    @Schema(
            description = "Represents the zip code", example = "85123"
    )
    @NotEmpty(message = "Zip code cannot be null or empty")
    @Size(min = 5, max = 10, message = "The length of the zip code should be between 5 and 10 characters")
    private String zipCode;
}
