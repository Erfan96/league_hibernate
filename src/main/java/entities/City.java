package entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "city")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "city_name", nullable = false, length = 50)
    private String name;

}
