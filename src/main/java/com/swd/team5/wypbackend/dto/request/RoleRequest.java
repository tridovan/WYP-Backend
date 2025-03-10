package com.swd.team5.wypbackend.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleRequest {
    private String name;
    private String description;
    private Set<String> permissions;

}
