package au.davidwrz.user.infrastracture.web;

import au.davidwrz.user.application.RegisterUserDto;
import au.davidwrz.user.domain.RegisterUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/user")
class UserController {

    private final RegisterUser registerUser;

    UserController(RegisterUser registerUser) {
        this.registerUser = registerUser;
    }

    @PostMapping("/register")
    public void addUser(@RequestBody RegisterUserDto registerUserDto) {
        log.info("User registration{}", registerUserDto);
        registerUser.register(registerUserDto);
    }
}
