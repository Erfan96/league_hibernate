package entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "stadium")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Stadium {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "stadium_name", nullable = false)
    private String name;

    @Column(name = "capacity", nullable = false)
    private Double capacity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id", nullable = false)
    private City city;
}
