package ru.nabokovsg.gateway.controller.laboratoryNK.equipmentDiagnosed;

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
import ru.nabokovsg.gateway.client.laboratoryNK.equipmentDiagnosed.PartElementClient;
import ru.nabokovsg.gateway.dto.laboratoryNK.equipmentDiagnosed.partElement.NewPartElementDto;
import ru.nabokovsg.gateway.dto.laboratoryNK.equipmentDiagnosed.partElement.UpdatePartElementDto;

@RestController
@RequestMapping(
        value = "/WorkVisionWeb/nk/diagnosed/equipments/elements/parts",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Подэлемент",
        description="API для работы с данными подэлементов элементов")
public class PartElementController {

    private final PartElementClient client;

    @Operation(summary = "Добавление нового подэлемента")
    @PostMapping
    public Mono<Object> save(@RequestBody @Valid @Parameter(description = "Подэлемент")
                                                    NewPartElementDto partElementDto) {
        return client.save(partElementDto);
    }

    @Operation(summary = "Изменение данных подэлемента")
    @PatchMapping
    public Mono<Object> update(@RequestBody @Valid @Parameter(description = "Подэлемент")
                                                   UpdatePartElementDto partElementDto) {
        return client.update(partElementDto);
    }

    @Operation(summary = "Получить подэлемент")
    @GetMapping("/{id}")
    public Mono<Object> get(@PathVariable @NotNull @Positive @Parameter(description = "Индентификатор") Long id) {
        return client.get(id);
    }

    @Operation(summary = "Получить все подэлементы элементы")
    @GetMapping("/all/{id}")
    public Flux<Object> getAll(@PathVariable @NotNull @Positive
                                             @Parameter(description = "Индентификатор элемента") Long id) {
        return client.getAll(id);
    }

    @Operation(summary = "Удаление подэлемента")
    @DeleteMapping("/{id}")
    public Mono<String> delete(@PathVariable @NotNull @Positive @Parameter(description = "Индентификатор") Long id) {
        return client.delete(id);
    }
}