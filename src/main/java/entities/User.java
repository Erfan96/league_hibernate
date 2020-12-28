package entities;

import lombok.*;
import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "user")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name", nullable = false, length = 50)
    private String fName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lName;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "position", nullable = false)
    private String position;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Contract> contract;

    @Column(name = "enabled", nullable = false)
    private Boolean enabled;

    @ManyToMany(mappedBy = "users",cascade = CascadeType.ALL)
    private Set<MatchEvent> matchEvents;
}
