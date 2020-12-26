package entities;

import lombok.*;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "Match")
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
    private Timestamp date;

    @OneToOne
    @JoinColumn(name = "stadium_id", nullable = false)
    private Stadium stadium;

    @OneToOne
    @JoinColumn(name = "home_team_id", nullable = false)
    private Team homeTeam;

    @OneToOne
    @JoinColumn(name = "away_team_id", nullable = false)
    private Team awayTeam;

    @OneToMany(mappedBy = "match", fetch = FetchType.LAZY)
    private Set<MatchEvent> matchEvents;
}
