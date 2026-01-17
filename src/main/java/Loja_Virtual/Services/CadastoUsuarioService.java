package Loja_Virtual.Services;


import Loja_Virtual.DTOS.UsuarioRequestDTO;
import Loja_Virtual.DTOS.UsuarioResponseDTO;
import Loja_Virtual.Entities.Carrinho;
import Loja_Virtual.Entities.Usuario;
import Loja_Virtual.Repository.UsuarioRepository;
import Loja_Virtual.Security.Role;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@Service
public class CadastoUsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UsuarioResponseDTO novoUsuario(UsuarioRequestDTO dto){

        if(usuarioRepository.findByemailUsuario(dto.email()).isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Este email já está cadastrado!");
        }

        Usuario novoUsuario = new Usuario();
        novoUsuario.setNomeUsuario(dto.nome());
        novoUsuario.setEmailUsuario(dto.email());
        novoUsuario.setRole(Role.USER);

        String senhaCriptografada = passwordEncoder.encode(dto.senha());

        novoUsuario.setSenhaUsuario(senhaCriptografada);

        Carrinho carrinho = new Carrinho();
        carrinho.setUsuario(novoUsuario);
        novoUsuario.setCarrinho(carrinho);

        Usuario usuarioSalvo = usuarioRepository.save(novoUsuario);

        return converterParaDTO(usuarioSalvo);
    }
    private UsuarioResponseDTO converterParaDTO(Usuario usuario){
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNomeUsuario(),
                usuario.getEmailUsuario(),
                usuario.getCarrinho() != null ? usuario.getCarrinho().getId() : null);
    }
}
