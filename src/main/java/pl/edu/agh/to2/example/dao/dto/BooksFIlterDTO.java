package pl.edu.agh.to2.example.dao.dto;

import java.util.List;
import java.util.Optional;

public record BooksFIlterDTO(
        Optional<String> titleContains,
        Optional<String> authorContains,
        Optional<String> genreContains) {
    private boolean isFiltering(List<Optional<?>> optionals) {
        return optionals.stream().anyMatch(Optional::isPresent);
    }

    public boolean isFiltering() {
        return isFiltering(List.of(titleContains, authorContains, genreContains));
    }
}
