package Loja_Virtual.Config;

import Loja_Virtual.Entities.Usuario;
import Loja_Virtual.Repository.UsuarioRepository;
import Loja_Virtual.Security.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class AdminInitializer {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner criarAdmin(){
        return args -> {
            boolean existeAdmin = usuarioRepository
                    .findByemailUsuario("admin@loja.com")
                    .isPresent();

            if(!existeAdmin){
                Usuario admin = new Usuario();
                admin.setNomeUsuario("Administrador");
                admin.setEmailUsuario("admin@loja.com");
                admin.setSenhaUsuario(passwordEncoder.encode("admin123"));
                admin.setRole(Role.ADMIN);

                usuarioRepository.save(admin);
                System.out.println(">>>>>ADMIN CRIADO COM SUCESSO!<<<<<");
            }
        };
    }
}
