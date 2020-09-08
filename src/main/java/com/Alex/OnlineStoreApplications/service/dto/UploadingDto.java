package com.Alex.OnlineStoreApplications.service.dto;

import org.springframework.web.multipart.MultipartFile;

public class UploadingDto {

    private MultipartFile file;
    private Long customerId;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }


}
