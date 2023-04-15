package com.client.api.rasmooplus.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserTypeDto {

    private Long id;

    @NotBlank(message = "Não pode ser nulo ou vazio")
    private String name;

    @NotBlank(message = "Não pode ser nulo ou vazio")
    @Size(min = 10, max = 255)
    private String description;

}
