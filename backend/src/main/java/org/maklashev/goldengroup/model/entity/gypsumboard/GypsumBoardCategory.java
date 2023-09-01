package org.maklashev.goldengroup.model.entity.gypsumboard;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "gboard_category")
public class GypsumBoardCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;
}
