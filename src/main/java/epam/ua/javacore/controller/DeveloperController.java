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
    Developer get(@PathVariable Long id) throws NotFoundException{
        return service.get(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    List<Developer> get() {
        return service.getAll().stream().collect(Collectors.toList());
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    Developer add(@RequestBody Developer developer) throws NotFoundException {
       return service.add(developer);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) throws NotFoundException{
        service.delete(id);
       }
}
