package org.maklashev.goldengroup.model.entity.delays;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "division")
public class Division {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;
}
