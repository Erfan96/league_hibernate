package entities;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "result")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "match_id", nullable = false)
    private Matches matches;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id",nullable = false)
    private Team teamId;

    @Column(name = "place", nullable = false)
    private String place;

    @Column(name = "goal", nullable = false)
    private Integer Goal;

    @Column(name = "score", nullable = false)
    private Integer score;
}
