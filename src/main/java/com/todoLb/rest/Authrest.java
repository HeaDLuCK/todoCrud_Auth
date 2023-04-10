package com.todoLb.rest;

import javax.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todoLb.Document.RefreshToken;
import com.todoLb.Document.User;
import com.todoLb.Repository.Refreshtokenrepository;
import com.todoLb.Repository.UserRepo;
import com.todoLb.dto.LoginDTO;
import com.todoLb.dto.SingupDTO;
import com.todoLb.dto.TokenDTO;
import com.todoLb.jwt.jwtHelper;

@RequestMapping("/api/auth")
@RestController
public class Authrest {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private Refreshtokenrepository refreshtokenrepository;
    @Autowired
    private jwtHelper jwtHelper;
    @Autowired
    private UserRepo userrepo;
    @Autowired
    private PasswordEncoder passwordencoder;

    @PostMapping("/login")
    @Transactional
    public ResponseEntity<?> Login(@Valid @RequestBody LoginDTO dto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user= (User) authentication.getPrincipal();
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setOwner(user);
        refreshtokenrepository.save(refreshToken);
        String accessToken =jwtHelper.generateAccessToken(user);
        String refreshTokenString =jwtHelper.generateRefreshToken(user,refreshToken.getId());
        return ResponseEntity.ok(new TokenDTO(user.getId(),accessToken,refreshTokenString));
    }

    @PostMapping("signup")
    public ResponseEntity<?> Signup(@Valid @RequestBody SingupDTO sdto) {
        User user = new User(sdto.getUsername(), sdto.getEmail(), passwordencoder.encode(sdto.getPassword()));
        userrepo.save(user);
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setOwner(user);
        refreshtokenrepository.save(refreshToken);
        String accessToken = jwtHelper.generateAccessToken(user);
        String refreshTokenString = jwtHelper.generateRefreshToken(user, refreshToken.getId());
        return ResponseEntity.ok(new TokenDTO(user.getId(), accessToken, refreshTokenString));
    }
}
