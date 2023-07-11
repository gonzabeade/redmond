package ar.edu.itba.bd2.redmond.controllers;

import ar.edu.itba.bd2.redmond.dto.ElasticUserDto;
import ar.edu.itba.bd2.redmond.dto.UserDto;
import ar.edu.itba.bd2.redmond.form.RegisterUserForm;
import ar.edu.itba.bd2.redmond.model.User;
import ar.edu.itba.bd2.redmond.model.elastic.ElasticUser;
import ar.edu.itba.bd2.redmond.model.exceptions.UserNotFoundException;
import ar.edu.itba.bd2.redmond.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Users")
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
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(hidden = true))),
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public UserDto register(@Valid @RequestBody RegisterUserForm form) {
        return new UserDto(
                userService.registerUser(form.getRedmondId(), form.getCbu(), form.getCuil(), form.getPassword())
        );
    }

    @Operation(summary = "Get user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content(schema = @Schema(hidden = true))),
    })
    @SecurityRequirement(name = "bearerAuth")
    @PostAuthorize("returnObject.redmondId == authentication.name")
    @GetMapping("/{id}")
    public UserDto get(@PathVariable String id) {
        return new UserDto(
                userService.getUserByRedmondIdWithBalance(id).orElseThrow(UserNotFoundException::new)
        );
    }

    @Operation(summary = "Optimized Redmond ID Search")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "204", description = "No content", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content(schema = @Schema(hidden = true))),
    })
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    public ResponseEntity<List<ElasticUserDto>> search(@RequestParam String search) {
        List<ElasticUser> users = userService.search(search);
        if(users.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(
                users.stream()
                        .map(ElasticUserDto::new)
                        .collect(Collectors.toList())
        );
    }
}
