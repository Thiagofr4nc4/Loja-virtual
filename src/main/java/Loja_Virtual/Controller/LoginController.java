package Loja_Virtual.Controller;

import Loja_Virtual.DTOS.LoginRequestDTO;
import Loja_Virtual.Security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final  JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<?> Login(@RequestBody LoginRequestDTO dto){

       Authentication auth =  authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.email(), dto.senha())
        );

        String token = jwtService.gerarToken(auth);

        return ResponseEntity.ok(Map.of("token", token));
    }




}
