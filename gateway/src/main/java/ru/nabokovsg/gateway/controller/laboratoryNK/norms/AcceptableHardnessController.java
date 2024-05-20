package ru.nabokovsg.gateway.controller.laboratoryNK.norms;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.nabokovsg.gateway.client.laboratoryNK.norms.AcceptableHardnessClient;
import ru.nabokovsg.gateway.dto.laboratoryNK.norms.acceptableHardness.NewAcceptableHardnessDto;
import ru.nabokovsg.gateway.dto.laboratoryNK.norms.acceptableHardness.UpdateAcceptableHardnessDto;

@RestController
@RequestMapping(
        value = "/WorkVisionWeb/nk/equipment/norms/hardness",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Данные допустимых твердости металла элементов оборудования",
        description="API для работы с данными норм допустимой твердости металла элементов оборудования")
public class AcceptableHardnessController {

    private final AcceptableHardnessClient client;

    @Operation(summary = "Добавить новые данные допустимой твердости металла элементов оборудования")
    @PostMapping
    public Mono<Object> save(@RequestBody @Valid @Parameter(name = "Данные допустимой твердости металла")
                                                                 NewAcceptableHardnessDto hardnessDto) {
        return client.save(hardnessDto);
    }

    @Operation(summary = "Изменение данных допустимой твердости металла элементов оборудования")
    @PatchMapping
    public Mono<Object> update(@RequestBody @Valid @Parameter(name = "Данные допустимой твердости металла")
                                                                 UpdateAcceptableHardnessDto hardnessDto) {
        return client.update(hardnessDto);
    }

    @Operation(summary = "Получить данные допустимых значений твердости металла элементов оборудования")
    @GetMapping("/{id}")
    public Flux<Object> getAll(@PathVariable @NotNull @Positive
                                                      @Parameter(name = "Индентификатор типа оборудования") Long id) {
        return client.getAll(id);
    }

    @Operation(summary = "Удалить данные допустимой твердости металла элементов")
    @DeleteMapping("/{id}")
    public Mono<String> delete(@PathVariable @NotNull @Positive @Parameter(name = "Индентификатор") Long id) {
        return client.delete(id);
    }
}