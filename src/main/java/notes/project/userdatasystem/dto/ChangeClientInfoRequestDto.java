package notes.project.userdatasystem.dto;

import java.util.UUID;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel(description = "Запрос на изменение личных данных")
public class ChangeClientInfoRequestDto {
    @ApiModelProperty(value = "Внешний ID клиента")
    private UUID clientExternalId;
    @ApiModelProperty(value = "Новые данные")
    private ChangePersonalInfoDto clientInfo;
}
