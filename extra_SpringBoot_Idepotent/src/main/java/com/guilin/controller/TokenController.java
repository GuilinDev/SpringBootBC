package com.guilin.controller;

import com.guilin.service.TokenUtilService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TokenController {

    @Autowired
    private TokenUtilService tokenUtilService;

    /**
     * get Token Endpoint
     * @return Token String
     */
    @GetMapping("/token")
    public String getToken() {
        // mimic user info
        String userInfo = "myIdempotence";
        // get Token
        return tokenUtilService.generateToken(userInfo);
    }

    /**
     * Endpoint Idempotence test
     * @param token idempotent strings
     * @return execute true or false
     */
    public String test(@RequestHeader(value = "token")String token) {
        // for testing purpose, use the same mimic userinfo
        String userInfo = "myIdempotence";
        // check Token and userinfo to see whether these two are idempotent
        boolean result = tokenUtilService.validateToken(token, userInfo);

        return result ? "Idempotence OK - normal call" : "Idempotence Repeated";
    }
}
