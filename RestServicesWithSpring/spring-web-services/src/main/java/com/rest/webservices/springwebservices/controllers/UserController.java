package com.rest.webservices.springwebservices.controllers;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.rest.webservices.springwebservices.repository.UserRepository;
import com.rest.webservices.springwebservices.repository.UserRepositoryStatic;
import com.rest.webservices.springwebservices.exceptions.UserNotFoundException;
import com.rest.webservices.springwebservices.vo.Post;
import com.rest.webservices.springwebservices.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepositoryStatic userRepositoryStatic;

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path="/get-all-users")
    public MappingJacksonValue getAllUsers() {

        List<User> users = userRepository.findAll();

        MappingJacksonValue mapping = new MappingJacksonValue(users);

        mapping.setFilters(getUserFilter());
        return mapping;
    }

    @GetMapping(path="/get-user/{userId}")
    public MappingJacksonValue getUserById(@PathVariable Integer userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(!optionalUser.isPresent()) {
            throw new UserNotFoundException("User with Id not found : id -> " + userId);
        }
        MappingJacksonValue mapping = new MappingJacksonValue(optionalUser.get());
        mapping.setFilters(getUserFilter());
        return mapping;
    }

    @PostMapping(path="/create-user")
    public ResponseEntity createUser(@Valid @RequestBody User user) {
        User savedUser = userRepository.save(user);
        try {
            URI uri = new URI("/get-user/" + savedUser.getId());
            return ResponseEntity.created(uri).build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(1).build();
    }

    @DeleteMapping("/delete-user/{userId}")
    public ResponseEntity deleteUser(@PathVariable Integer userId) {
        userRepository.deleteById(userId);
        return ResponseEntity.status(200).build();
    }

    @GetMapping("/get-user/posts/{userId}")
    public List<Post> getPostsByUser(@PathVariable Integer userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(!optionalUser.isPresent()) {
            throw new UserNotFoundException("User with Id not found : id -> " + userId);
        }
        return optionalUser.get().getPosts();

    }

    private FilterProvider getUserFilter() {
        SimpleBeanPropertyFilter propertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "dateOfBirth", "posts");
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("UserFilter", propertyFilter);
        return filterProvider;
    }
}
