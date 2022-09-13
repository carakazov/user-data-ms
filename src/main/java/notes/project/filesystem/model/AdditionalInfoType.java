package notes.project.filesystem.model;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity(name = "additional_info_types")
public class AdditionalInfoType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "system_id")
    private System system;

    private String type;
}
