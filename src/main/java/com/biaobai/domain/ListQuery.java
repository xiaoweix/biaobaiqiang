package com.biaobai.domain;

import com.biaobai.utils.CommonQuery;
import lombok.Data;

@Data
public class ListQuery extends CommonQuery {
    private String userId;
}
