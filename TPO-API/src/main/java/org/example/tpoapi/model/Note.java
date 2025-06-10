package org.example.tpoapi.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.aspectj.weaver.ast.Not;

import java.time.LocalDateTime;

@Entity
@Table(name = "Note")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    private String title;
    @NotBlank
    @Size(max = 500)
    private String content;
    @Column(name = "CREATION_DATE", nullable = false, updatable = false)
    private LocalDateTime creationDate;


    public Note(){}

    public Note(String title, String content){
        this.title = title;
        this.content = content;
    }

    @PrePersist
    void setDateOnCreate(){
        this.creationDate = LocalDateTime.now();
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
}
