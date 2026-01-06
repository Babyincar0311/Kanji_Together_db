package org.t2404e.kanji_together_db.entity;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.t2404e.kanji_together_db.enums.ExamType;

import java.time.LocalDateTime;
import java.util.List;
@Entity
@Table(name = "exams")
@Data
public class Exams {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String name;
    public String question;

    @Enumerated(EnumType.STRING)
    public ExamType exam_type;

    @OneToMany(mappedBy = "exam")
    public List<Questions> questions;

    @CreationTimestamp
    public LocalDateTime create_at;

    @UpdateTimestamp
    public LocalDateTime edit_at;

    public Integer create_by;
    public Integer edit_by;
}
