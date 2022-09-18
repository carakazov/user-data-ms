package notes.project.userdatasystem.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ApiModel(description = "Запрос на изменение дополнительных данных клиента")
public class ChangeAdditionalInfoDto {
    @ApiModelProperty(value = "Название параметра")
    private String type;
    @ApiModelProperty(value = "Новое значение")
    private String newValue;
}
