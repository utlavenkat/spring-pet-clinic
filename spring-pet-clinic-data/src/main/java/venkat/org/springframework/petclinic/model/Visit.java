package venkat.org.springframework.petclinic.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true,onlyExplicitlyIncluded = true)
@Data
@Entity
@Table(name = "visits")
public class Visit extends BaseEntity {
    @Column(name = "date")
    private LocalTime date;

    @Column(name = "description")
    private String description;

    @ManyToOne
    private Pet pet;

}
