package venkat.org.springframework.petclinic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true,onlyExplicitlyIncluded = true)
@Data
@Entity
@Table(name = "visits")
public class Visit extends BaseEntity {
    @Column(name = "date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @Column(name = "description")
    private String description;

    @ManyToOne
    private Pet pet;

    public boolean isNew() {
        return this.getId() == null;
    }

    public String toString() {
        return description;
    }

}
