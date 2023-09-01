package org.maklashev.goldengroup.model.entity.production;

import jakarta.persistence.*;
import lombok.Data;
import org.maklashev.goldengroup.model.entity.gypsumboard.GypsumBoard;
import org.maklashev.goldengroup.model.entity.gypsumboard.GypsumBoardCategory;

@Data
@Entity
@Table(name = "board_production")
public class BoardProduction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "productionLog_id")
    private ProductionList productionList;

    @ManyToOne
    @JoinColumn(name = "gypsum_board_id")
    private GypsumBoard gypsumBoard;

    @ManyToOne
    @JoinColumn(name = "gboard_category_id")
    private GypsumBoardCategory gypsumBoardCategory;

    @Column(name = "value")
    private float value;
}
