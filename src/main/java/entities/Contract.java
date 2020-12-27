package entities;

import lombok.*;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "contract")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "season")
    private Integer season;

    @Column(name = "years", nullable = false)
    private Integer years;

    @Column(name = "salary", nullable = false)
    private Double salary;

    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id")
    private Team team;

    @Column(name = "enabled", nullable = false)
    private Boolean enabled;
}
