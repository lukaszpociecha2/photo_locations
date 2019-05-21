package pl.pociecha.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.pociecha.models.User;
import pl.pociecha.models.UserDetails;
import pl.pociecha.repositories.GenericRepository;
import pl.pociecha.repositories.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/")
public class ControllerHome {

    @Autowired
    private GenericRepository<User, Long> userRepository;


    public ControllerHome() {

    }


    @RequestMapping(path = "/home")//, produces = "text/html; charset=UTF-8")
    @ResponseBody

    public String home() {
        return "Cześć Łukasz";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/addUser")

    public User addUser() {
        User user = new User();
        user.setFirstName("Łukasz");
        user.setLastName("Pociecha");
        UserDetails userDetails = new UserDetails("lawyer");
        user.setUserDetails(userDetails);

        userRepository.save(user);

        return user;

    }

    @GetMapping(path = "/getUser/{id}", produces = "application/json; charset=utf-8")
    public User getUserById(@PathVariable(name = "id") long id) {
        return userRepository.get(id);

    }

    @GetMapping("/updateUser/{id}")
    public User updateUser(@PathVariable(name = "id") Long id) {
        User userToUpdate = userRepository.get(id);
        return userRepository.update(userToUpdate);

    }

    @GetMapping("/getUsers")
    public List<User> getUsers() {
        return userRepository.getAll();
    }

    @GetMapping("/deleteUser/{id}")
    public String delete(@PathVariable(name = "id") Long id) {
        User userToDelete = userRepository.get(id);
        if (userToDelete == null) {
            return "No such user";
        }
        userRepository.delete(userToDelete);
        return "" + userToDelete.getFirstName() + " " + userToDelete.getLastName() + " skasowany.";
    }
}



