package org.maklashev.goldengroup.model.entity.gypsumboard;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "length")
public class Length {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "value")
    private String value;

}
