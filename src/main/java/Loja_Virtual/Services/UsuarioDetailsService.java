package Loja_Virtual.Services;

import Loja_Virtual.Repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioDetailsService implements UserDetailsService{
    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username){
        return usuarioRepository.findByemailUsuario(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado"));
    }

}
