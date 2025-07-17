package com.kerubinesapp.mateogdev.repository;

import com.kerubinesapp.mateogdev.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public Usuario findByUsername(String username);
}
