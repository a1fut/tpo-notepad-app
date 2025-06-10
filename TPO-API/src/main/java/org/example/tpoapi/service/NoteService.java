package org.example.tpoapi.service;

import jakarta.transaction.Transactional;
import org.aspectj.weaver.ast.Not;
import org.example.tpoapi.exceptions.NoRecordInDatabaseException;
import org.example.tpoapi.model.Note;
import org.example.tpoapi.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class NoteService {

    private final NoteRepository repo;


    public NoteService(@Autowired NoteRepository repo) {
        this.repo = repo;
    }

    public Note create(Note note) {
        return repo.save(note);
    }

    public List<Note> getAll() {
        return repo.findAll();
    }

    public Note get(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new NoRecordInDatabaseException("No record with id: " + id + " in the database"));
    }

    public Note update(Long id, Note updatedNote) {
        Note toUpdate = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Note not found"));
        toUpdate.setTitle(updatedNote.getTitle());
        toUpdate.setContent(updatedNote.getContent());
        return repo.save(toUpdate);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }


}
