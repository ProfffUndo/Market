package com.submarine29.market.controller;

import com.submarine29.market.domain.User;
import com.submarine29.market.repo.UserDetailsRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account") // user? registration
public class UserController {
    private final UserDetailsRepo userDetailsRepo;

    @Autowired
    public UserController (UserDetailsRepo userDetailsRepo){
        this.userDetailsRepo = userDetailsRepo;
    }

    @GetMapping
    public List<User> list(){
        return userDetailsRepo.findAll();
    }

    @GetMapping("{id}")
    public User getOne(@PathVariable("id") User user){
        return user;
    }

    @PostMapping
    public User create(@RequestBody User user){
        return userDetailsRepo.save(user);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") User user){
        userDetailsRepo.delete(user);
    }

    @PutMapping("{id}")
    public User update(@PathVariable("id") User userFromDB, @RequestBody User userNew){
        BeanUtils.copyProperties(userNew,userFromDB,"id");
        return userDetailsRepo.save(userFromDB);
    }
}
