package com.spacex.gandalf.dto.pagination;

import lombok.Data;

@Data
public class PaginationDTO {
    private Integer pageNum;
    private Integer size;
}
