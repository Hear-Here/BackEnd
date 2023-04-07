package ssuSoftware.hearHear.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ssuSoftware.hearHear.core.dto.UserDto;
import ssuSoftware.user.repository.UserRepository;
import ssuSoftware.user.service.UserService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @PostMapping("/join")
    @ResponseBody(HttpStatus.OK)
    public Long join(@Valid @RequestBody UserDto userDto) throws Exception{
        return userService.singUP(userDto);
    }
}
