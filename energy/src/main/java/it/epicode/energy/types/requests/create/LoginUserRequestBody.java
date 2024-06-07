package it.epicode.energy.types.requests.create;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginUserRequestBody {

    @NotBlank(message = "email cannot be empty")
    @Email(message = "email does not have the right format")
    private String email;

    @NotBlank(message = "password cannot be empty")
    private String password;}
