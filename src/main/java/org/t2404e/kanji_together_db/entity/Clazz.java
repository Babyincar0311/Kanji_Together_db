package org.t2404e.kanji_together_db.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "clazz")
@Data
public class Clazz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    // Validation Rules: Not blank, 3-100 chars
    @NotBlank(message = "name must not be blank")
    @Size(min = 3, max = 100, message = "name must be between 3 and 100 characters")
    @Column(nullable = false, length = 100)
    public String name;

    // Validation Rules: Max 500 chars
    @Size(max = 500, message = "description must not exceed 500 characters")
    @Column(length = 500)
    public String description;

    @JsonProperty("is_active")
    public Boolean is_active;

    @CreationTimestamp
    @Column(updatable = false)
    public LocalDateTime create_at;

    @UpdateTimestamp
    public LocalDateTime edit_at;

    public Integer create_by;
    public Integer edit_by;

    @ManyToMany
    @JoinTable(
            name = "clazz_rel_users",
            joinColumns = @JoinColumn(name = "clazz_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    public List<Users> users;
}