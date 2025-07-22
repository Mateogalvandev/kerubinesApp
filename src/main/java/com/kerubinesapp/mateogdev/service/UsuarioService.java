package com.kerubinesapp.mateogdev.service;

import com.kerubinesapp.mateogdev.dto.UsuarioDto;
import com.kerubinesapp.mateogdev.model.Rol;
import com.kerubinesapp.mateogdev.model.Usuario;
import com.kerubinesapp.mateogdev.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements IUsuarioService{

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    // Inyección por constructor
    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username);
        if (usuario == null){
            throw new UsernameNotFoundException("Usuario o password invalido");
        }
        return new User(usuario.getUsername(),usuario.getPassword(), getAuthorities (usuario.getRolUsuario())
        );
    }

    private Collection<? extends GrantedAuthority> getAuthorities (Usuario.Rol rol){
        return Collections.singletonList(
                new SimpleGrantedAuthority(rol.name())
        );

    }

    @Override
    public Usuario guardarUsuario(UsuarioDto usuarioDto) {
        Usuario usuario = new Usuario(usuarioDto.getNombre(),passwordEncoder.encode(usuarioDto.getPassword()), usuarioDto.getUsername());
        usuario.setRolUsuario(Usuario.Rol.ROLE_USER);
        usuario.setFechaIngreso(LocalDate.now());
        return usuarioRepository.save(usuario);
    }


    @Override
    public List<Usuario> usuarioLista() {
        return usuarioRepository.findAll();

    }

    @Override
    public Usuario usuarioObtenerId(Long id) {
        Usuario usuario = usuarioRepository.findById(id).get();
        return usuario;
    }

    @Override
    public Usuario actualizarUsuario(Usuario usuario) {

        return usuarioRepository.save(usuario);
    }

    @Override
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}
