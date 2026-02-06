package main.compilation.service;

import main.compilation.domain.Compilation;
import main.compilation.dto.UpdateCompilationRequest;
import main.exception.NotFoundException;

import java.util.List;

public interface CompilationService {

    Compilation postCompilation(Compilation compilation) throws NotFoundException;

    void deleteCompilation(Long compId) throws NotFoundException;

    Compilation patchCompilation(Long compId, UpdateCompilationRequest dto) throws NotFoundException;

    List<Compilation> getCompilation(Boolean pinned, Integer from, Integer size);

    Compilation getCompilationById(Long compId) throws NotFoundException;
}
