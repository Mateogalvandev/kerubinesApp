package com.kerubinesapp.mateogdev.service;

import com.kerubinesapp.mateogdev.dto.UsuarioDto;
import com.kerubinesapp.mateogdev.model.Usuario;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUsuarioService extends UserDetailsService {

    public Usuario guardarUsuario(UsuarioDto usuarioDto);

    public List<Usuario> usuarioLista();

    public Usuario usuarioObtenerId(Long id);

    public Usuario actualizarUsuario(Usuario usuario);

    public void eliminarUsuario(Long id);
}
