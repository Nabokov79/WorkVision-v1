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
import ru.nabokovsg.gateway.client.laboratoryNK.equipmentDiagnosed.EquipmentElementClient;
import ru.nabokovsg.gateway.dto.laboratoryNK.equipmentDiagnosed.element.NewElementDto;
import ru.nabokovsg.gateway.dto.laboratoryNK.equipmentDiagnosed.element.UpdateElementDto;

@RestController
@RequestMapping(
        value = "/WorkVisionWeb/nk/diagnosed/equipment/elements",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Tag(name="Элемент",
        description="API для работы с данными элементов оборудования")
public class EquipmentElementController {

    private final EquipmentElementClient client;

    @Operation(summary = "Добавление нового элемента")
    @PostMapping
    public Mono<Object> save(@RequestBody @Valid @Parameter(description = "Элемент") NewElementDto elementDto) {
        return client.save(elementDto);
    }

    @Operation(summary = "Изменение данных элемента")
    @PatchMapping
    public Mono<Object> update(@RequestBody @Valid @Parameter(description = "Элемент") UpdateElementDto elementDto) {
        return client.update(elementDto);
    }

    @Operation(summary = "Получить элемент")
    @GetMapping("/{id}")
    public Mono<Object> get(@PathVariable @NotNull @Positive @Parameter(description = "Индентификатор") Long id) {
        return client.get(id);
    }

    @Operation(summary = "Получить все элементы оборудования")
    @GetMapping("/all/{id}")
    public Flux<Object> getAll(@PathVariable @NotNull @Positive
                                             @Parameter(description = "Индентификатор оборудования") Long id) {
        return client.getAll(id);
    }

    @Operation(summary = "Удаление элемента")
    @DeleteMapping("/{id}")
    public Mono<String> delete(@PathVariable @NotNull @Positive @Parameter(description = "Индентификатор") Long id) {
        return client.delete(id);
    }
}
