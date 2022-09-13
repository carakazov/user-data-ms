package notes.project.filesystem.model;

import java.time.LocalDate;
import javax.persistence.*;

import lombok.Data;

@Data
@Entity(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "system_id")
    private System system;

    private String name;

    private String surname;

    private String middleName;

    private String email;

    private String externalId;

    private LocalDate dateOfBirth;
}
