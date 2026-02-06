package main.compilation.controller;

import lombok.RequiredArgsConstructor;
import main.compilation.domain.Compilation;
import main.compilation.dto.CompilationDto;
import main.compilation.mapper.CompilationDomainDto;
import main.compilation.service.CompilationService;
import main.exception.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/compilations")
public class PublicCompilationController {
    private final CompilationService service;
    private final CompilationDomainDto mapper;

    @GetMapping
    public List<CompilationDto> getCompilation(@RequestParam(required = false) Boolean pinned,
                                               @RequestParam(defaultValue = "0") Integer from,
                                               @RequestParam(defaultValue = "10") Integer size) {
        List<Compilation> compilations = service.getCompilation(pinned, from, size);
        return compilations.stream()
                .map(mapper::toDto)
                .toList();
    }

    @GetMapping("/{compId}")
    public CompilationDto getCompilationById(@PathVariable Long compId) throws NotFoundException {
        Compilation compilation = service.getCompilationById(compId);
        return mapper.toDto(compilation);
    }
}

