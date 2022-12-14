package notes.project.userdatasystem.controller;

import java.util.UUID;

import javax.validation.Valid;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import notes.project.userdatasystem.dto.ChangeClientInfoRequestDto;
import notes.project.userdatasystem.dto.ClientDto;
import notes.project.userdatasystem.dto.SystemClientListResponseDto;
import notes.project.userdatasystem.service.api.ClientService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/client")
@Api("Контроллер по управлению клиентами")
public class ClientController {
    private final ClientService clientService;

    @GetMapping("{externalId}")
    @ApiOperation("Запрос клиента по его external id")
    public ClientDto getClient(
        @PathVariable(name = "externalId") @ApiParam(value = "Внешний id пользователя") UUID externalId)
    {
        return clientService.getSingleClient(externalId);
    }

    @GetMapping("{systemName}/list")
    @ApiOperation("Запрос всех клиентов системы")
    public SystemClientListResponseDto getAllClients(
        @PathVariable(name = "systemName") @ApiParam(value = "Название системы") String systemName
    ) {
        return clientService.getAllClientsOfSystem(systemName);
    }

    @PutMapping
    public ClientDto changeClientPersonalInfo(
        @RequestBody @Valid ChangeClientInfoRequestDto request,
        @ApiParam(value = "Признак создания новых параметров") @RequestParam(name = "createNew", defaultValue = "false") Boolean createNew
        ) {
        return clientService.changePersonalInfo(request, createNew);
    }
}
