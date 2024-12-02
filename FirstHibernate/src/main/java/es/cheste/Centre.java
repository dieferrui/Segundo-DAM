package es.cheste;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Centre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private int codi;

    @Column
    private String centre;

    @Column
    private String direccio;

    @Column
    private String localitat;

    @Column
    private int telefon;

    @Column
    private String query;

    @ManyToOne
    @JoinColumn(name = "regim_id", nullable = false)
    private Regim regim;

    @ManyToOne
    @JoinColumn(name = "provincia_id", nullable = false)
    private Provincia provincia;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Cicle> cicles = new ArrayList<>();
}
