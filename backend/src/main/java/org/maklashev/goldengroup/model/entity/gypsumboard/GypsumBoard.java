package org.maklashev.goldengroup.model.entity.gypsumboard;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.maklashev.goldengroup.model.entity.Product;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "gypsum_board")
@DiscriminatorValue("GypsumBoard")
public class GypsumBoard extends Product {
    @ManyToOne
    @JoinColumn(name = "board_type_id")
    private BoardType boardType;

    @ManyToOne
    @JoinColumn(name = "edge_id")
    private Edge edge;

    @ManyToOne
    @JoinColumn(name = "thickness_id")
    private Thickness thickness;

    @ManyToOne
    @JoinColumn(name = "width_id")
    private Width width;

    @ManyToOne
    @JoinColumn(name = "length_id")
    private Length length;
}





