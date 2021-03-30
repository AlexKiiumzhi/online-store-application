package com.Alex.OnlineStoreApplications.service.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

public class BuyingDto {

    @NotNull
    @Min(value=1)
    private Long id;
    private List<Long> productIds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }


}
