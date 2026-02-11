package main.compilation.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import main.compilation.domain.Compilation;
import main.compilation.dto.CompilationDto;
import main.compilation.dto.NewCompilationDto;
import main.compilation.dto.UpdateCompilationRequest;
import main.compilation.mapper.CompilationDomainDto;
import main.compilation.service.CompilationService;
import main.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/compilations")
public class AdminCompilationController {
    private final CompilationService service;
    private final CompilationDomainDto mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompilationDto postCompilation(@RequestBody @Valid NewCompilationDto dto) throws NotFoundException {
        Compilation compilation = service.postCompilation(mapper.toDomain(dto));
        return mapper.toDto(compilation);
    }

    @DeleteMapping("/{compId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompilation(@PathVariable Long compId) throws NotFoundException {
        service.deleteCompilation(compId);
    }

    @PatchMapping("/{compId}")
    public CompilationDto patchCompilation(@RequestBody @Valid UpdateCompilationRequest dto, @PathVariable Long compId) throws NotFoundException {
        Compilation compilation = service.patchCompilation(compId, dto);
        return mapper.toDto(compilation);
    }
}
