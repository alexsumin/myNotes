package ru.alexsumin.notes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.alexsumin.notes.NoteBean;
import ru.alexsumin.notes.model.Note;
import ru.alexsumin.notes.service.NoteService;

import java.util.List;


@Controller
public class NoteController {
    private final NoteService service;

    @Autowired
    public NoteController(NoteService service) {
        this.service = service;
    }

    //TODO:сделать так, чтобы выводил список только опредленного юзера
    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public String hello(ModelMap model) {
        List<Note> list = service.list();
        NoteBean bean = new NoteBean();
        bean.setList(list);
        model.put("list", list);
        return "list";
    }


    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView createNewForm() {
        ModelAndView modelAndView = new ModelAndView("addForm");
        modelAndView.getModelMap().addAttribute("newNote", new Note());
        return modelAndView;
    }

    @RequestMapping(value = "/submitNew", method = RequestMethod.POST)
    public ModelAndView createNewAction(@ModelAttribute Note newNote) {
        service.add(newNote);
        return new ModelAndView("redirect:list");
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public ModelAndView createNewForm(@RequestParam int id) {
        ModelAndView modelAndView = new ModelAndView("updateForm");
        modelAndView.getModelMap().addAttribute("newNote", service.get(id));
        return modelAndView;
    }


    @RequestMapping(value = "/submitUpdate", method = RequestMethod.POST)
    public ModelAndView updateAction(@ModelAttribute Note newNote) {
        service.update(newNote);
        return new ModelAndView("redirect:list");
    }

    @RequestMapping(value = "/delete")
    public ModelAndView delete(@RequestParam int id) {
        service.delete(id);
        return new ModelAndView("redirect:list");
    }


}
