package org.perscholas.casestudy.database.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.util.Set;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "matchup")
public class Matchup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "char1", nullable = false)
    private gCharacter char1;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "char2", nullable = false)
    private gCharacter char2;

    @Column(name = "url")
    private String url;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "upload_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date uploadDate;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "matchup", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments;

    // Constructors, getters, setters, etc.
}