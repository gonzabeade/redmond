package ar.edu.itba.bd2.redmond.controllers;

import ar.edu.itba.bd2.redmond.auth.JwtUtils;
import ar.edu.itba.bd2.redmond.dto.JwtDto;
import ar.edu.itba.bd2.redmond.form.LoginForm;
import ar.edu.itba.bd2.redmond.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/login")
public class LoginController {
    private final JwtUtils jwtUtils;
    private final UserService userService;

    @Autowired
    public LoginController(JwtUtils jwtUtils, UserService userService) {
        this.jwtUtils = jwtUtils;
        this.userService = userService;
    }

    @Operation(summary = "Get JWT refresh token")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
    })
    @PostMapping
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginForm form) {
        if(!userService.isLoginValid(form.getRedmondId(), form.getPassword())) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(
                new JwtDto(
                        jwtUtils.generateRefreshToken(form.getRedmondId()),
                        jwtUtils.generateToken(form.getRedmondId())
                )
        );
    }
}
