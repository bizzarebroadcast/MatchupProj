package org.perscholas.casestudy.database.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;
@Entity
@Getter
@Setter
@Table(name = "characters")
public class gCharacter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "image_url")
    private String imageUrl;

    // Constructors, getters, setters, etc.
}