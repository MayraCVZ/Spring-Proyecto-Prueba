package com.javaspring.curso.controllers;

import com.javaspring.curso.dao.UsuarioDao;
import com.javaspring.curso.models.Usuario;
import com.javaspring.curso.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class UsuarioController {
    @Autowired
    private UsuarioDao usuarioDao;
    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/prueba")
    public List<String> prueba() {
        return Arrays.asList(new String[]{"Manzana", "Naranja"});

       /*
       --From a Stream
       List<String> list = Stream.of("foo", "bar")
                .collect(Collectors.toList());

        --assertTrue(list.contains("foo"));
        */
    }

    @RequestMapping(value = "api/usuarios")
    public List<Usuario> getUsuarios(@RequestHeader (value="Authorization") String token) {
    if(!validarToken(token)){
        return null;
    }
        return usuarioDao.getUsuaros();
    }

    private boolean validarToken(String token){
        String usuarioId=jwtUtil.getKey(token);
        return usuarioId!=null;
    }

    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.DELETE)
    public void eliminar(@PathVariable Long id,
                         @RequestHeader (value="Authorization") String token) {
        if(!validarToken(token)){
            return;
        }
        usuarioDao.eliminar(id);
    }

    @RequestMapping(value = "api/usuarios", method = RequestMethod.POST)
    public void insertar(@RequestBody Usuario usuario) {
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1,1024,1,usuario.getPassword());
        usuario.setPassword(hash);
        usuarioDao.insertar(usuario);
    }


    @RequestMapping(value = "api/usuario/{id}")
    public Usuario getUsuario(@PathVariable Long id) {
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre("Pedro Perez");
        usuario.setTelefono("123456789");
        usuario.setEmail("pedro@gmail.com");
        return usuario;
    }
    /*GET con datos estaticos*/
    /*@RequestMapping(value = "api/usuarios")
    public List<Usuario> getUsuarios() {
       List<Usuario> usuarios = new ArrayList<>();

        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Pedro Perez");
        usuario.setTelefono("1234567891");
        usuario.setEmail("pedro@gmail.com");

        Usuario usuario2 = new Usuario();
        usuario2.setId(2L);
        usuario2.setNombre("Juan Perez");
        usuario2.setTelefono("3123456789");
        usuario2.setEmail("juan@gmail.com");

        Usuario usuario3 = new Usuario();
        usuario3.setId(3L);
        usuario3.setNombre("Ana Perez");
        usuario3.setTelefono("2123456789");
        usuario3.setEmail("ana@gmail.com");

        usuarios.add(usuario);
        usuarios.add(usuario2);
        usuarios.add(usuario3);
        return usuarios;
    }*/
}
