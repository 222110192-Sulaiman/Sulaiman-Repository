package psu.edu.Lab_14.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;

public record Course(
		@Id @PositiveOrZero Long id,
		@NotBlank String code,
		@NotNull @Size(min = 2, message = "must be at least 2 characters") String name,
		String description,
		@NotNull @Min(1) @Max(10) Integer credits) {

}
