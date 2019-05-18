package pl.pociecha.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.pociecha.models.User;
import pl.pociecha.models.UserDao;

@RestController
@RequestMapping("/")
public class ControllerHome {

    @Autowired
    private UserDao userDao;


    @RequestMapping(path = "/home")//, produces = "text/html; charset=UTF-8")
    @ResponseBody

    public String home() {
        return "Cześć Łukasz";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/addUser")
    @ResponseBody
    public String addUser() {
        User user = new User();
        user.setFirstName("Łukasz");
        user.setLastName("Pociecha");
        userDao.addUser(user);

        return "Utworzono użytkownika: " + user.getFirstName() + " " + user.getLastName()
                + "o id: " + user.getId();

    }

    @GetMapping(path = "/getUser/id/{id}", produces = "application/json; charset=utf-8")
    @ResponseBody
    public User getUserById(@PathVariable(name = "id") long id){
        User user = userDao.getUserById(id);
        return user;
    }
}


