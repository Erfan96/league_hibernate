package entities;

import lombok.*;
import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "match_event")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatchEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "type", nullable = false)
    private String Type;

    @Column(name = "date_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date dateTime;

    @ManyToOne
    @JoinColumn(name = "match_id", nullable = false)
    private Matches matches;

    @ManyToMany
    @JoinTable(name = "mat_eve_use",
             joinColumns = @JoinColumn(name = "MATCHEVENT_ID", referencedColumnName = "id"),
             inverseJoinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "id"))
    private Set<User> users;
}
