package uz.orders.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.orders.db.dao.interfaces.UserDAO;
import uz.orders.db.entities.User;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserDAO userDAO;

    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping(value = "/get")
    public ResponseEntity<List<User>> getAll() {
        return new ResponseEntity<>(userDAO.get(), HttpStatus.OK);
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id) {
        userDAO.getById(id);
        return new ResponseEntity<>(userDAO.getById(id), HttpStatus.OK);

    }

    @GetMapping(value = "/delete/{id}")
    public HttpStatus deleteUser(@PathVariable int id) {
        userDAO.deleteById(id);
        return HttpStatus.OK;
    }

    @PostMapping(value = "/save")
    public ResponseEntity<List<User>> saveUser(@Valid @RequestBody User user) {
        userDAO.saveUser(user);
        return new ResponseEntity<>(userDAO.get(), HttpStatus.OK);
    }
}
