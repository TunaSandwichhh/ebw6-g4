package it.epicode.energy.services;

import it.epicode.energy.entities.User;
import it.epicode.energy.repositories.UserRepository;
import it.epicode.energy.types.requests.create.CreateUserRequestBody;
import it.epicode.energy.types.requests.update.UpdateUserRequestBody;
import it.epicode.energy.types.responses.DeleteUserResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Page<User> retrieveAllUsers(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return userRepository.findAll(pageable);
    }

    public User retrieveUserById(UUID userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
    }

    public User retrieveByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow( () -> new RuntimeException("User not found with email: " + email));
    }

    public User addUser(CreateUserRequestBody userRequestBody) {
        User userToCreate = new User();
        setUserFields(userToCreate, userRequestBody);

        return userRepository.save(userToCreate);
    }

    public User editUser(UUID userId, UpdateUserRequestBody userRequestBody) {
        User userToUpdate = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        updateUserFields(userToUpdate, userRequestBody);

        return userRepository.save(userToUpdate);
    }

    public DeleteUserResponseBody removeUser(UUID userId) {
        User userToDelete = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        User userToShow = new User();

        setUserFieldsForDeletion(userToShow, userToDelete);

        userRepository.delete(userToShow);

        return  new DeleteUserResponseBody("User deleted successfully", userToShow);
    }


    public void setUserFields(User userToCreate, CreateUserRequestBody userRequestBody) {
        userToCreate.setUsername(userRequestBody.getUsername());
        userToCreate.setEmail(userRequestBody.getEmail());
        userToCreate.setPassword(passwordEncoder.encode(userRequestBody.getPassword()));
        userToCreate.setFirstName(userRequestBody.getFirstName());
        userToCreate.setLastName(userRequestBody.getLastName());
        userToCreate.setAvatarUrl(userRequestBody.getAvatarUrl());
    }

    public void updateUserFields(User userToUpdate, UpdateUserRequestBody userRequestBody) {
        if (userRequestBody.getUsername() != null) {
            userToUpdate.setUsername(userRequestBody.getUsername());
        }
        if (userRequestBody.getEmail() != null) {
            userToUpdate.setEmail(userRequestBody.getEmail());
        }
        if (userRequestBody.getPassword() != null) {
            userToUpdate.setPassword(passwordEncoder.encode(userRequestBody.getPassword()));
        }
        if (userRequestBody.getFirstName() != null) {
            userToUpdate.setFirstName(userRequestBody.getFirstName());
        }
        if (userRequestBody.getLastName() != null) {
            userToUpdate.setLastName(userRequestBody.getLastName());
        }
        if (userRequestBody.getAvatarUrl() != null) {
            userToUpdate.setAvatarUrl(userRequestBody.getAvatarUrl());
        }
    }

    public void setUserFieldsForDeletion(User userToCreate, User userRequestBody) {
        userToCreate.setUsername(userRequestBody.getUsername());
        userToCreate.setEmail(userRequestBody.getEmail());
        userToCreate.setPassword(passwordEncoder.encode(userRequestBody.getPassword()));
        userToCreate.setFirstName(userRequestBody.getFirstName());
        userToCreate.setLastName(userRequestBody.getLastName());
        userToCreate.setAvatarUrl(userRequestBody.getAvatarUrl());
    }
}
