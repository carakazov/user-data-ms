package notes.project.userdatasystem.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel(description = "Описание клиента")
public class ClientDto {
    @ApiModelProperty(value = "Имя")
    private String name;
    @ApiModelProperty(value = "Фамилия")
    private String surname;
    @ApiModelProperty(value = "Отчество")
    private String middleName;
    @ApiModelProperty(value = "Дата рождения")
    private LocalDate birthDate;
    @ApiModelProperty(value = "Внешний ID пользователя")
    private UUID externalId;
    @ApiModelProperty(value = "Почта")
    private String email;
    @ApiModelProperty(value = "Дополнительная информация по пользователю")
    private List<AdditionalInfoDto> additionalInfo;
}
