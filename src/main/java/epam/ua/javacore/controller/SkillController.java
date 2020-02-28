package epam.ua.javacore.controller;

import epam.ua.javacore.exception.NotFoundException;

import epam.ua.javacore.model.Skill;

import epam.ua.javacore.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/skill")
public class SkillController {

    SkillService service;

    @Autowired
    public SkillController(SkillService service) {
        this.service = service;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Skill get(@PathVariable Long id) {
        Skill skill = null;
        try {
            skill = service.get(id);
        } catch (NotFoundException e) {
        }
        return skill;
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    List<Skill> get() {
        return service.getAll().stream().collect(Collectors.toList());
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    Skill add(@RequestBody Skill skill) {
        Skill newSkill = null;
        try {
            newSkill = service.add(skill);
        } catch (NotFoundException e) {
        }
        return newSkill;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        try {
            service.delete(id);
        } catch (NotFoundException e) {
        }

    }
}
