package entities;

import lombok.*;

import javax.persistence.*;

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

    @Column(name = "city_id", nullable = false)
    private Integer cityId;
}