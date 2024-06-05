package it.epicode.energy.controllers;

import it.epicode.energy.entities.User;
import it.epicode.energy.services.UserService;
import it.epicode.energy.types.requests.create.CreateUserRequestBody;
import it.epicode.energy.types.requests.update.UpdateUserRequestBody;
import it.epicode.energy.types.responses.DeleteUserResponseBody;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping
    public ResponseEntity<Page<User>> getUsers(@RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "10") int size,
                                               @RequestParam(defaultValue = "id") String sortBy) {
        return new ResponseEntity<>(userService.retrieveAllUsers(page, size, sortBy), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable UUID userId) {
        return new ResponseEntity<>(userService.retrieveUserById(userId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody @Validated CreateUserRequestBody userRequestBody, BindingResult validation) throws BadRequestException {
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors()
                    .stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .reduce("", (acc, curr) -> acc+curr));
        }
        return new ResponseEntity<>(userService.addUser(userRequestBody), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> updateUser(@RequestBody @Validated UpdateUserRequestBody userRequestBody,
                                           @PathVariable UUID userId,
                                           BindingResult validation) throws BadRequestException {
        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors()
                    .stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .reduce("", (acc, curr) -> acc+curr));
        }
        return new ResponseEntity<>(userService.editUser(userId, userRequestBody), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteUserResponseBody> deleteUser(@PathVariable UUID userId) {
        return new ResponseEntity<>(userService.removeUser(userId), HttpStatus.OK);
    }
}
