package notes.project.filesystem.model;

import java.util.function.LongFunction;
import javax.persistence.*;

import lombok.Data;

@Data
@Entity(name = "additional_info")
public class AdditionalInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "additional_info_type_id")
    private AdditionalInfoType type;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    private String value;
}
