package com.projet.react.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    private UsersRepository repository;

    @GetMapping("/users")
    public List<Users> findUsers(){
        return repository.findAll();
    }
    @GetMapping("/user/{id}")
    public Users findUser(@PathVariable Long id){
        Users user = repository.findUserById(id);
        if (user != null){
            return user;
        }
        return new Users();
    }

    @DeleteMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable Long id){
            repository.deleteById(id);
    }

    @PostMapping("/user/")
    public Users createUser(@RequestParam Users newUser){
        if (newUser != null){
            newUser.setId(UUID.randomUUID().node());
        }
        return new Users();
    }
}
