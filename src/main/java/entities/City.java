package entities;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "city")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "city_name", nullable = false, length = 50)
    private String name;

    @OneToMany(mappedBy = "city")
    private Set<Team> teams;

    @OneToMany(mappedBy = "city")
    private Set<Stadium> stadiums;
}
