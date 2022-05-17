package com.javaspring.curso.dao;

import com.javaspring.curso.models.Usuario;

import java.util.List;

public interface UsuarioDao {
    List<Usuario>getUsuaros();

    void eliminar(Long id);

    void insertar(Usuario usuario);

    Usuario obtenerUsuario(Usuario usuario);
}
