package com.mangjakseon.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchPersonDataDTO {

    private Long id;
    private String name;
    private String knownForDepartment;
    private String mediaType;
}
