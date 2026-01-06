package Loja_Virtual.Services;


import Loja_Virtual.DTOS.UsuarioRequestDTO;
import Loja_Virtual.Entities.Carrinho;
import Loja_Virtual.Entities.Usuario;
import Loja_Virtual.Repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
@Service
public class CadastoUsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario novoUsuario(UsuarioRequestDTO dto){

        if(usuarioRepository.findByEmail_usuario(dto.email()).isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Este email já está cadastrado!");
        }

        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome_usuario(dto.nome());
        novoUsuario.setEmail_usuario(dto.email());
        novoUsuario.setSenha_usuario(dto.senha());

        Carrinho carrinho = new Carrinho();
        carrinho.setUsuario(novoUsuario);
        novoUsuario.setCarrinho(carrinho);

        return usuarioRepository.save(novoUsuario);
    }
}
