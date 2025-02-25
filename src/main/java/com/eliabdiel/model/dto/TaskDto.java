package com.eliabdiel.model.dto;

import jakarta.validation.constraints.NotBlank;

public record TaskDto(@NotBlank String title, @NotBlank String description) {
}
