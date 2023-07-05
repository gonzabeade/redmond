package ar.edu.itba.bd2.redmond.controllers;

import ar.edu.itba.bd2.redmond.dto.UserDto;
import ar.edu.itba.bd2.redmond.form.RegisterUserForm;
import ar.edu.itba.bd2.redmond.model.exceptions.UserNotFoundException;
import ar.edu.itba.bd2.redmond.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Register user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User registered"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public UserDto register(@Valid @RequestBody RegisterUserForm form) {
        return new UserDto(
                userService.registerUser(form.getRedmondId(), form.getCbu(), form.getCuil(), form.getPassword())
        );
    }

    @Operation(summary = "Get user by id")
    @GetMapping("/{id}")
    public UserDto get(@PathVariable String id) {
        return new UserDto(
                userService.getUserByRedmondIdWithBalance(id).orElseThrow(UserNotFoundException::new)
        );
    }
}
