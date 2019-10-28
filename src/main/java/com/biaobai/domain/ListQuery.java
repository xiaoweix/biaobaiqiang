package com.biaobai.domain;

import com.biaobai.utils.CommonQuery;
import lombok.Data;

@Data
public class ListQuery extends CommonQuery {
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
