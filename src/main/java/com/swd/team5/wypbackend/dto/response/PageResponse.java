package com.swd.team5.wypbackend.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PageResponse<T> {
    private int pageNo;
    private int pageSize;
    private int totalPage;
    private int totalElement;
    private String[] sortBy;
    private T items;
}
