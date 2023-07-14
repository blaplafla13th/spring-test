package test.ngocpt.auth.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class RegisterForm {
    @Max(value=25)
    @NotBlank
    private String username;
    @Email
    @NotBlank
    private String email;
    @Pattern(regexp="^(?=(.*[a-z])+)(?=(.*[A-Z])+)(?=(.*[0-9])+)(?=(.*[!@#$%^&*()\\-_+.])+).{8,24}$",
        message = "Password must contain 8 to 24 character and have at least 1 lowercase, 1 uppercase, 1 number, 1 special character")
    @NotBlank
    private String password;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Past
    private LocalDate dateOfBirth;
}
