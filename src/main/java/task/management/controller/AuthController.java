package task.management.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import task.management.dto.user.UserLoginDto;
import task.management.dto.user.UserRegistrationRequestDto;
import task.management.dto.user.UserResponseDto;
import task.management.dto.user.UserResponseLoginDto;
import task.management.security.AuthenticationService;
import task.management.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Endpoints for user authentication")
public class AuthController {

    private final UserService userService;
    private final AuthenticationService authenticationService;

    @Operation(summary = "Registration",
            description = "Takes user data, validates them and returns registered user")
    @GetMapping("/registration")
    public UserResponseDto register(@RequestBody @Valid UserRegistrationRequestDto request) {
        return userService.register(request);
    }

    @Operation(summary = "Logging",
            description = "Takes email and password, returns JWT")
    @GetMapping("/login")
    public UserResponseLoginDto login(@RequestBody @Valid UserLoginDto userLoginDto) {
        return authenticationService.authenticate(userLoginDto);
    }
}
