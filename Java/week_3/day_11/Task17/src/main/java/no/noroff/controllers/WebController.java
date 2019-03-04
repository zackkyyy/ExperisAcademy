package no.noroff.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import no.noroff.models.Person;
import no.noroff.repositories.RepositoryPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;


@Controller
public class WebController {
    RepositoryPerson repositoryPerson;

    @Autowired
    public void setRepositoryPerson(RepositoryPerson repositoryPerson) {
        this.repositoryPerson = repositoryPerson;
    }

    @ApiOperation(value = "View information about a given person", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the person"),
            @ApiResponse(code = 401, message = "You are not authorized to view this resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The person you were trying to find is not in the list")})
    @GetMapping("/person/search/{search}")
    @ResponseBody
    public List<Person> getPersons(@PathVariable final String search) {
        return repositoryPerson.lookUp(search);
    }

    @ApiOperation(value = "Retrieve a list of all the individuals", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved the list"),
            @ApiResponse(code = 401, message = "You are not authorized to view this resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The list could not be retrieved")})
    @GetMapping("/person/all")
    @ResponseBody
    public List<Person> getAllPersons() {
        return repositoryPerson.getPersons();
    }


    @ApiOperation(value = "Add a person to the registry", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully added to the list"),
            @ApiResponse(code = 401, message = "You are not authorized to view this resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The person could not be added")})
    @PostMapping("/person/add")
    @ResponseBody
    public String addPerson(@RequestBody Person p) {
        System.out.println("\n" + p.toString() + "\n");
        String ret;

        boolean result = repositoryPerson.addPerson(p);

        if (result) {
            ret = "Added person " + p.getFirstName() + " " + p.getLastName() + ", successfully.";
        } else {
            ret = "Added person " + p.getFirstName() + " " + p.getLastName() + ", failed.";
        }

        return ret;
    }
    @RequestMapping("/surprise")
    public String surprise(){
        return "rickrolled";
    }

    @RequestMapping("/")
    @ApiIgnore
    public String index() {
        return "index";
    }
}
