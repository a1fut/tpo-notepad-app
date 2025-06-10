package org.example.tpoapi.controller;


import jakarta.validation.Valid;
import org.aspectj.weaver.ast.Not;
import org.example.tpoapi.model.Note;
import org.example.tpoapi.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.awt.image.LookupOp;
import java.util.List;

@Controller
@RequestMapping()
@Validated
public class NoteController {

    private final NoteService service;

    public NoteController(@Autowired NoteService service) {
        this.service = service;
    }


    @GetMapping("/new")
    public String showNewNoteForm(Model model) {
        model.addAttribute("note", new Note());
        return "new";
    }

    @PostMapping
    public String create(@Valid Note note, BindingResult br) {
        if (br.hasErrors())
            return "new_note";

        service.create(note);
        return "redirect:/";
    }
    @GetMapping("/update/{id}")
    public String showUpdateForm(Model model, @PathVariable Long id){
        model.addAttribute("note", service.get(id));
        return "update";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @Valid @ModelAttribute("note") Note updatedNote, BindingResult br){
        if (br.hasErrors())
            return "update";
        service.update(id, updatedNote);
        return "redirect:/";
    }


    @GetMapping
    public String listNotes(Model model) {
        model.addAttribute("notes", service.getAll());
        return "notes";
    }

    @GetMapping("/{id}")
    public Note getOne(@PathVariable Long id) {
        return service.get(id);
    }

//    @PutMapping("/{id}")
//    public Note update(@PathVariable Long id, @RequestBody @Valid Note updated) {
//        return service.update(id, updated);
//    }

    @DeleteMapping("/notes/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}
