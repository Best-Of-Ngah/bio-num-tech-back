package com.best.of.ngah.bionumtech.dtos.pagination;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class Paginate<T> implements Serializable {
    private T items;
    private PageInfo pageInfo;
    private Integer totalPage;
}
