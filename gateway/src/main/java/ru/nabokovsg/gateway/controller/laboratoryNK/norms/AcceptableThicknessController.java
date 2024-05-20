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
import ru.nabokovsg.gateway.client.laboratoryNK.norms.AcceptableThicknessClient;
import ru.nabokovsg.gateway.dto.laboratoryNK.norms.acceptableThickness.NewAcceptableThicknessDto;
import ru.nabokovsg.gateway.dto.laboratoryNK.norms.acceptableThickness.UpdateAcceptableThicknessDto;

@RestController
@RequestMapping(
        value = "/WorkVisionWeb/nk/equipment/norms/thickness",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Данные минимальных допустимых толщин стенок элементов оборудования",
        description="API для работы с данными норм минимальных допустимых стенок элементов оборудования")
public class AcceptableThicknessController {

    private final AcceptableThicknessClient client;

    @Operation(summary = "Добавить новые данные допустимых толщины стенки")
    @PostMapping
    public Mono<Object> save(@RequestBody @Valid @Parameter(name = "Данные допустимой толщины стенки")
                             NewAcceptableThicknessDto thicknessDto) {
        return client.save(thicknessDto);
    }

    @Operation(summary = "Изменение данных допустимых толщины стенки")
    @PatchMapping
    public Mono<Object> update(@RequestBody @Valid @Parameter(name = "Данные допустимой толщины стенки")
                               UpdateAcceptableThicknessDto thicknessDto) {
        return client.update(thicknessDto);
    }

    @Operation(summary = "Получить данные допустимых толщин толщины стенки элементов оборудования")
    @GetMapping("/{id}")
    public Flux<Object> getAll(@PathVariable @NotNull @Positive
                                             @Parameter(name = "Индентификатор типа оборудования") Long id) {
        return client.getAll(id);
    }

    @Operation(summary = "Удалить данные допустимых толщины стенки")
    @DeleteMapping("/{id}")
    public Mono<String> delete(@PathVariable @NotNull @Positive @Parameter(name = "Индентификатор") Long id) {
        return client.delete(id);
    }
}