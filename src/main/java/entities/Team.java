package entities;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "team")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "team_name", nullable = false)
    private String name;

    @OneToOne
    @JoinColumn(name = "coach_id")
    private User coach;

    @OneToMany(mappedBy = "team")
    private Set<User> players;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @OneToOne
    @JoinColumn(name = "stadium", nullable = false)
    private Stadium stadium;

    @OneToMany(mappedBy = "team")
    private Set<Contract> contracts;
}
