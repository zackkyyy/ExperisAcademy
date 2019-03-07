package no.noroff.Task21.controller;

import no.noroff.Task21.database.DBHandler;
import no.noroff.Task21.model.User;
import no.noroff.Task21.model.Character;
import no.noroff.Task21.model.Class;
import no.noroff.Task21.model.Username;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class WebController {
    private DBHandler dbHandler = new DBHandler();

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/users")
    @ResponseBody
    public List<Username> allUserInfo() {
        List<Username> usernames = new ArrayList<>();
        Username username;

        for(User u : dbHandler.getUser(-1, true)) {
            username = new Username();
            username.setUsername(u.getUsername());
            usernames.add(username);
        }

        username = null;

        return usernames;
    }

    @GetMapping("/characters")
    @ResponseBody
    public List<Character> allCharacterInfo() {
        return dbHandler.getCharacter(-1);
    }

    @GetMapping("/classes")
    @ResponseBody
    public List<Class> allClassInfo() {
        return dbHandler.getClass("");
    }

    @GetMapping("/user/{id}")
    @ResponseBody
    public List<User> userInfo(@PathVariable int id) {
        return dbHandler.getUser(id, false);
    }

    @GetMapping("/character/{id}")
    @ResponseBody
    public List<Character> characterInfo(@PathVariable int id) {
        return dbHandler.getCharacter(id);
    }

    @GetMapping("/class/{className}")
    @ResponseBody
    public List<Class> classInfo(@PathVariable String className) {
        return dbHandler.getClass(className);
    }

    @PostMapping("/user")
    @ResponseBody
    public String addUser(@ModelAttribute User u) {
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
    @ResponseBody
    public String addCharacter(@ModelAttribute Character c) {
        String ret;

        boolean result = dbHandler.addCharacter(c.getUsername(), c.getCharacter_name(), c.getLevel(), c.getClass_name());

        if (result) {
            ret = "Adding new character " + c.getCharacter_name() + ", successfully.";
        } else {
            ret = "Adding new character " + c.getCharacter_name() + ", failed.";
        }

        return ret;
    }

    @PostMapping("/class")
    @ResponseBody
    public String addClass(@ModelAttribute Class c) {
        String ret;

        boolean result = dbHandler.addClass(c.getClass_name(), c.getAbility());

        if (result) {
            ret = "Adding new class " + c.getClass_name() + ", successfully.";
        } else {
            ret = "Adding new class " + c.getClass_name() + ", failed.";
        }

        return ret;
    }
}
