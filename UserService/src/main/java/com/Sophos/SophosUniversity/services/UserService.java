package com.Sophos.SophosUniversity.services;


import com.Sophos.SophosUniversity.entities.User;

import com.Sophos.SophosUniversity.exceptions.InternalServerErrorException;
import com.Sophos.SophosUniversity.exceptions.UserNotFoundException;
import com.Sophos.SophosUniversity.repository.UserRepository;
import com.Sophos.SophosUniversity.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService implements IUserService{


    @Autowired
    private UserRepository repository;

    @Autowired
    private JWTUtil jwtUtil;

    @Override
    public User getUserById(Long id) throws Exception{
        try{
            return (User) repository.findById(id).get();
        } catch (DataAccessException ex){
            ex.printStackTrace();
            throw new InternalServerErrorException("Error to access the database");
        } catch(RuntimeException ex){
            throw new UserNotFoundException("User no encontrado con ID: " + id);
        }
    }

    @Override
    public String addNewUser(User user) throws Exception{
        try{

            Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);

            //(nIteracionesDeHasheo,UsoDeMemoria, Paralelismos, VAlorAHashear)
            String hash = argon2.hash(1, 1024,1,user.getUser_password());
            user.setUser_password(hash);

           repository.save(user);
           return "Usuario registrado correctamente";
        } catch (DataAccessException ex){
            ex.printStackTrace();
            throw new InternalServerErrorException("Error to access the database");
        }

    }


    @Override
    public String verifyUser(User user) throws Exception{
        try{

            List<User> list = repository.findAllUsersByuserMail(user.getUser_mail());


            if(list.isEmpty()){
                return null;
            }
            
            String passwordHashed = list.get(0).getUser_password();


            Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);

           if (argon2.verify(passwordHashed,user.getUser_password())){

               list.get(0).getUser_password();

               String token = jwtUtil.create(String.valueOf(list.get(0).getUser_id()),list.get(0).getUser_mail());

                return token;
           }
            
           
           return null;


        } catch (DataAccessException ex){
            ex.printStackTrace();
            throw new InternalServerErrorException("Error to access the database");
        } catch(RuntimeException ex){
            System.out.println(ex);
            throw new UserNotFoundException( "User no encontrado con email: " + user.getUser_mail());
        }
    }



    @Override
    public boolean validateToken(String token) throws Exception{

        String usuarioId = jwtUtil.getKey(token);

        if(repository.existsById(Long.valueOf(usuarioId))){
            return true;
        }else{
            return false;
        }

    }




}
