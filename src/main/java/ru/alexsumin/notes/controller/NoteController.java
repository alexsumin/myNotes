package ru.alexsumin.notes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(value = {"/list", "/"}, method = RequestMethod.GET)
    public String hello(ModelMap model) {
        List<Note> list = service.list();
        NoteBean bean = new NoteBean();
        bean.setList(list);
        model.put("list", list);
        return "list";
    }


}
