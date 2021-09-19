package pl.sda.travel360.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Travel {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne
    private Country country;

    @Column(length = 5000)
    private String desctription;

    @ManyToMany
    private Set<TravelParcitipals> parcitipants;
}
