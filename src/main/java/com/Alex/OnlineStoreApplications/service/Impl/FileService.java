package com.Alex.OnlineStoreApplications.service.Impl;

import com.Alex.OnlineStoreApplications.Exceptions.CustomerNotFoundException;
import com.Alex.OnlineStoreApplications.Exceptions.FileStorageException;
import com.Alex.OnlineStoreApplications.entity.User;
import com.Alex.OnlineStoreApplications.entity.File;
import com.Alex.OnlineStoreApplications.repository.UserRepository;
import com.Alex.OnlineStoreApplications.service.dto.UploadingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class FileService implements com.Alex.OnlineStoreApplications.service.FileService {

    @Autowired
    private UserRepository userRepository;

    public void uploadFile(UploadingDto uploadingDto) {
        Optional<User> optionalCustomer = userRepository.findById(uploadingDto.getCustomerId());
        if (optionalCustomer.isPresent()) {
            try {
                if (Objects.requireNonNull(uploadingDto.getFile().getOriginalFilename()).contains("..")) {
                    throw new FileStorageException(uploadingDto.getFile().getOriginalFilename());
                } else {
                    User customer = userRepository.getOne(uploadingDto.getCustomerId());
                    customer.getFiles().add(mapUploadingDtoToFile(uploadingDto));
                    userRepository.save(customer);
                }
            } catch (IOException ex) {
                throw new FileStorageException(uploadingDto.getFile().getOriginalFilename(), ex);
            }
        } else {
            throw new CustomerNotFoundException();
        }
    }

    public List<File> downloadFiles(Long customerId) {
        Optional<User> optionalCustomer = userRepository.findById(customerId);
        if (optionalCustomer.isPresent()) {
            return optionalCustomer.get().getFiles();
        } else {
            throw new CustomerNotFoundException();
        }
    }

    private File mapUploadingDtoToFile(UploadingDto uploadingDto) throws IOException {
        File file = new File();
        file.setFileName(uploadingDto.getFile().getOriginalFilename());
        file.setFileType(uploadingDto.getFile().getContentType());
        file.setData(uploadingDto.getFile().getBytes());
        return file;
    }
}

