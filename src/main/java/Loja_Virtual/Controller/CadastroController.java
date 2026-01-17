package Loja_Virtual.Controller;

import Loja_Virtual.DTOS.UsuarioRequestDTO;
import Loja_Virtual.DTOS.UsuarioResponseDTO;
import Loja_Virtual.Services.CadastoUsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class CadastroController {
    private final CadastoUsuarioService cadastroUsuarioService;

    @PostMapping("/cadastrar")
    public ResponseEntity<UsuarioResponseDTO> criarUsuario(@Valid @RequestBody UsuarioRequestDTO dto){
        UsuarioResponseDTO novoUsuario = cadastroUsuarioService.novoUsuario(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }
}
