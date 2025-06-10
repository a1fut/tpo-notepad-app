package org.example.tpoapi;

import org.example.tpoapi.model.Note;
import org.example.tpoapi.repository.NoteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TpoApiApplicationTests {

    @Autowired
    private NoteRepository repo;

    @Test
    void addNoteToTable(){
        Note note = new Note("Title 1", "Content 1");

        repo.save(note);

        System.out.println(repo.findAll());
    }
}
