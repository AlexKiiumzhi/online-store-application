package com.Alex.OnlineStoreApplications.service;

import com.Alex.OnlineStoreApplications.Exceptions.FileNotFoundException;
import com.Alex.OnlineStoreApplications.Exceptions.FileStorageException;
import com.Alex.OnlineStoreApplications.entity.File;
import com.Alex.OnlineStoreApplications.service.dto.UploadingDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    void uploadFile(UploadingDto uploadingDto);
    List<File> downloadFiles(Long customerId);
}
