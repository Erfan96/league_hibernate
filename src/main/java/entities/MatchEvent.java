package entities;

import lombok.*;
import javax.persistence.*;


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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "match_id", nullable = false)
    private Match match;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
