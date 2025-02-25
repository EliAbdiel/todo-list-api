package com.eliabdiel.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterDto(
        @NotBlank String name,
        @NotBlank String username,
        @NotBlank String email,
        @NotBlank String password) {
}
