package ssuSoftware.hearHear.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ssuSoftware.user.auth.dto.LoginResponse;
import ssuSoftware.user.oauth.dto.UserToken;
import ssuSoftware.user.oauth.OauthService;

@RestController
@RequiredArgsConstructor
@Slf4j
public class OAuthController {

    private final OauthService oauthService;


    @GetMapping("/login/{provider}")
    public ResponseEntity<LoginResponse> login(@PathVariable String provider, @RequestBody UserToken userToken){
        LoginResponse loginResponse = oauthService.loginWithToken(provider, userToken);
        return ResponseEntity.ok().body(loginResponse);
    }

}


