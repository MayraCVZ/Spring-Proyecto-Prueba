package com.javaspring.curso.controllers;

import com.javaspring.curso.dao.UsuarioDao;
import com.javaspring.curso.models.Usuario;
import com.javaspring.curso.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private UsuarioDao usuarioDao;
    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String login(@RequestBody Usuario usuario) {
        Usuario usuarioLoging= usuarioDao.obtenerUsuario(usuario);
        if(usuarioLoging !=null){
            return jwtUtil.create(usuarioLoging.getId().toString(),usuarioLoging.getEmail());

        }
        return "FAIL";
    }
}
