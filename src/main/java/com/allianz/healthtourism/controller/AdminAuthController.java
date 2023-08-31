package com.allianz.healthtourism.controller;

import com.allianz.healthtourism.model.requestDTO.LoginRequestDTO;
import com.allianz.healthtourism.model.requestDTO.UserRequestDTO;
import com.allianz.healthtourism.service.UserService;
import com.allianz.healthtourism.util.security.JWTUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("admin-auth")
public class AdminAuthController {
    private final AuthenticationManager authManager;
    private final JWTUtil jwtUtil;
    private final UserService userService;

    public AdminAuthController(AuthenticationManager authManager, JWTUtil jwtUtil, UserService userService) {
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }


    @PostMapping("login")
    public Map<String, Object> loginHandler(@RequestBody LoginRequestDTO body) {
        UsernamePasswordAuthenticationToken authInputToken =
                new UsernamePasswordAuthenticationToken(body.getEmail(), body.getPassword());
        authManager.authenticate(authInputToken);
        String token = jwtUtil.generateToken(body.getEmail());
        Map<String, Object> authorizationMap = new HashMap<>();
        authorizationMap.put("jwt-token", token);
        return authorizationMap;
    }


    @PostMapping("register")
    public ResponseEntity<Boolean> loginHandler(@RequestBody UserRequestDTO requestDTO) {
        userService.saveAdminUser(requestDTO);
        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
    }

}
