package no.noroff.Task21.controller;

import no.noroff.Task21.DBHandler;
import no.noroff.Task21.model.User;
import no.noroff.Task21.model.Character;
import no.noroff.Task21.model.Class;
import no.noroff.Task21.model.Username;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class WebController {
    private DBHandler dbHandler = new DBHandler();

    @RequestMapping("/")
    public String index() {
        return "Index";
    }

    @GetMapping("/users")
    public List<Username> allUserInfo() {
        List<Username> usernames = new ArrayList<>();
        Username username;

        for(User u : dbHandler.getUser(-1)) {
            username = new Username();
            username.setUsername(u.getUsername());
            usernames.add(username);
        }

        username = null;

        return usernames;
    }

    @GetMapping("/characters")
    public List<Character> allCharacterInfo() {
        return dbHandler.getCharacter(-1);
    }

    @GetMapping("/classes")
    public List<Class> allClassInfo() {
        return dbHandler.getClass("");
    }

    @GetMapping("/user/{id}")
    public List<User> userInfo(@PathVariable int id) {
        return dbHandler.getUser(id);
    }

    @GetMapping("/character/{id}")
    public List<Character> characterInfo(@PathVariable int id) {
        return dbHandler.getCharacter(id);
    }

    @GetMapping("/class/{className}")
    public List<Class> classInfo(@PathVariable String className) {
        return dbHandler.getClass(className);
    }

    @PostMapping("/user")
    public String addUser(@RequestBody User u) {
        String ret;

        boolean result = dbHandler.addUser(u.getUsername(),u.getPassword());

        if (result) {
            ret = "Adding new user " + u.getUsername() + ", successfully.";
        } else {
            ret = "Adding new user " + u.getUsername() + ", failed.";
        }

        return ret;
    }

    @PostMapping("/character")
    public String addCharacter(@RequestBody Character c) {
        String ret;

        boolean result = dbHandler.addCharacter(c.getUsername(), c.getCharacter_name(), c.getLevel(), c.getClassName());

        if (result) {
            ret = "Adding new character " + c.getCharacter_name() + ", successfully.";
        } else {
            ret = "Adding new character " + c.getCharacter_name() + ", failed.";
        }

        return ret;
    }

    @PostMapping("/class")
    public String addClass(@RequestBody Class c) {
        String ret;

        boolean result = dbHandler.addClass(c.getName(), c.getAbility());

        if (result) {
            ret = "Adding new class " + c.getName() + ", successfully.";
        } else {
            ret = "Adding new class " + c.getName() + ", failed.";
        }

        return ret;
    }
}
