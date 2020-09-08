package com.Alex.OnlineStoreApplications.controller;

import com.Alex.OnlineStoreApplications.service.Impl.FileService;
import com.Alex.OnlineStoreApplications.service.dto.UploadingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/uploadFile")
    public void uploadFile(@ModelAttribute UploadingDto uploadingDto) {
        fileService.uploadFile(uploadingDto);
    }

    @GetMapping("/downloadFile/{customerId}")
    public ResponseEntity<List<ByteArrayResource>> downloadFiles(@PathVariable Long customerId) {
        return ResponseEntity.ok()
                .body(fileService.downloadFiles(customerId).stream()
                        .map(file -> new ByteArrayResource(file.getData()))
                        .collect(Collectors.toList()));
    }
}
