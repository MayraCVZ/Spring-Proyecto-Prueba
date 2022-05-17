package com.javaspring.curso.dao;

import com.javaspring.curso.models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
@Transactional
public class UsuarioDaoImp implements UsuarioDao{

    @PersistenceContext
     EntityManager em;

    @Override
    @Transactional
    public List<Usuario> getUsuaros() {
        String query= "FROM Usuario";
        return em.createQuery(query).getResultList();
    }

    @Override
    public void eliminar(Long id) {
        Usuario usuario= em.find(Usuario.class,id);
        em.remove(usuario);
    }

    @Override
    public void insertar(Usuario usuario) {
        em.merge(usuario);
    }
    @Override
    public Usuario obtenerUsuario(Usuario usuario) {
        String query= "FROM Usuario where email = :email";
        List<Usuario> lista=em.createQuery(query)
                 .setParameter("email",usuario.getEmail())
                 .getResultList();

        if (lista.isEmpty()){
            return null;
        }
        String passHashed=lista.get(0).getPassword();

        Argon2 argon2= Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        if(argon2.verify(passHashed,usuario.getPassword())){
            return lista.get(0);
        }
        else {
            return null;
        }

    }
}
