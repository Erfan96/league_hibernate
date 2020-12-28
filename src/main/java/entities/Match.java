package entities;

import lombok.*;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "matches")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "season", nullable = false)
    private Integer season;

    @Column(name = "date_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "stadium_id", nullable = false)
    private Stadium stadium;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "home_team_id")
    private Team homeTeam;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "away_team_id")
    private Team awayTeam;

    @OneToMany(mappedBy = "match", fetch = FetchType.LAZY)
    private Set<MatchEvent> matchEvents;
}
