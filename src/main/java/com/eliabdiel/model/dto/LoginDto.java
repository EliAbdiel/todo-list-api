package com.eliabdiel.model.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginDto(
        @NotBlank String usernameOrEmail,
        @NotBlank String password) {
}
