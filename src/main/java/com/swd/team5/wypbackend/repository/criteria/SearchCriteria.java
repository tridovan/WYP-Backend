package com.swd.team5.wypbackend.repository.criteria;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchCriteria {
    //product's field
    private String key;

    //= < >
    private String operation;

    private Object value;
}
