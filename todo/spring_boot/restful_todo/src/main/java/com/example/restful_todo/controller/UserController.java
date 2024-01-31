package com.example.restful_todo.controller;

import com.example.restful_todo.model.*;
import com.example.restful_todo.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.crypto.password.*;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/")
@RestController
public class UserController {
    @Autowired
    private UserService UserService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserController(UserService UserService) {
        this.UserService = UserService;
    }

    private HashedPassword hashPassword(String password) {
        return new HashedPassword(passwordEncoder.encode(password));
    }

    @GetMapping("/todo_items/sign_in")
    public ResponseEntity<User> getUser(@RequestBody String userIdString,
            @RequestBody String password) {
        return new ResponseEntity<User>(
                this.UserService.getUser(new UserId(userIdString), hashPassword(password)),
                HttpStatus.OK);
    }

    @PostMapping("/todo_items/sign_up")
    public ResponseEntity<User> createUser(@RequestBody String userIdString,
            @RequestBody String password) {
        return new ResponseEntity<User>(
                this.UserService.createUser(new UserId(userIdString), hashPassword(password)),
                HttpStatus.CREATED);
    }

    @PatchMapping("/todo_items/change_password")
    public ResponseEntity<User> updateUser(@RequestBody String userIdString,
            @RequestBody String passwordBefore, @RequestBody String passwordAfter) {
        return new ResponseEntity<User>(this.UserService.updateUser(new UserId(userIdString),
                hashPassword(passwordBefore), hashPassword(passwordAfter)), HttpStatus.CREATED);
    }

    @DeleteMapping("/todo_items/delete_account")
    public ResponseEntity<HttpStatus> deleteUser(@RequestBody String userIdString,
            @RequestBody String password) {
        this.UserService.deleteUser(new UserId(userIdString), hashPassword(password));
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }
}
