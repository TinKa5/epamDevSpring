package epam.ua.javacore.controller;

import epam.ua.javacore.exception.NotFoundException;
import epam.ua.javacore.model.Developer;
import epam.ua.javacore.service.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/developer")
public class DeveloperController {

    DeveloperService service;

    @Autowired
    public DeveloperController(DeveloperService service) {
        this.service = service;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Developer get(@PathVariable Long id) {
        Developer developer = null;
        try {
            developer = service.get(id);
        } catch (NotFoundException e) {
        }
        return developer;
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    List<Developer> get() {
        return service.getAll().stream().collect(Collectors.toList());
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    Developer add(@RequestBody Developer developer) {
        Developer newDeveloper = null;
        try {
            newDeveloper = service.add(developer);
        } catch (NotFoundException e) {
        }
        return newDeveloper;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        try {
            service.delete(id);
        } catch (NotFoundException e) {
        }
    }
}
