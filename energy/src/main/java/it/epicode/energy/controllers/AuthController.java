package it.epicode.energy.controllers;

import it.epicode.energy.entities.User;
import it.epicode.energy.services.AuthService;
import it.epicode.energy.services.UserService;
import it.epicode.energy.types.requests.create.CreateUserRequestBody;
import it.epicode.energy.types.requests.create.LoginUserRequestBody;
import it.epicode.energy.types.responses.LoginUserResponseBody;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody @Validated CreateUserRequestBody userRequestBody, BindingResult validation) throws BadRequestException {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors()
                    .stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .reduce("", (acc, curr) -> acc + curr));
        }
        return new ResponseEntity<>(userService.addUser(userRequestBody), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginUserResponseBody> login (@RequestBody @Validated LoginUserRequestBody loginUserRequestBody, BindingResult validation) throws BadRequestException {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors()
                    .stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .reduce("", (acc, curr) -> acc + curr));
        }
        return new ResponseEntity<>(authService.authenticateUserAndToken(loginUserRequestBody), HttpStatus.OK);
    }
}
