package com.Alex.OnlineStoreApplications.service.Impl;

import com.Alex.OnlineStoreApplications.Exceptions.CustomerNotFoundException;
import com.Alex.OnlineStoreApplications.Exceptions.FileStorageException;
import com.Alex.OnlineStoreApplications.entity.Customer;
import com.Alex.OnlineStoreApplications.entity.File;
import com.Alex.OnlineStoreApplications.repository.CustomerRepository;
import com.Alex.OnlineStoreApplications.repository.FileRepository;
import com.Alex.OnlineStoreApplications.service.IFileService;
import com.Alex.OnlineStoreApplications.service.dto.UploadingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class FileService implements IFileService {

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public void uploadFile(UploadingDto uploadingDto) {
        Optional<Customer> optionalCustomer = customerRepository.findById(uploadingDto.getCustomerId());
        if (optionalCustomer.isPresent()) {
            try {
                if (Objects.requireNonNull(uploadingDto.getFile().getOriginalFilename()).contains("..")) {
                    throw new FileStorageException(uploadingDto.getFile().getOriginalFilename());
                } else {
                    Customer customer = customerRepository.getOne(uploadingDto.getCustomerId());
                    customer.getFiles().add(mapUploadingDtoToFile(uploadingDto));
                    customerRepository.save(customer);
                }
            } catch (IOException ex) {
                throw new FileStorageException(uploadingDto.getFile().getOriginalFilename(), ex);
            }
        } else {
            throw new CustomerNotFoundException();
        }
    }

    public List<File> downloadFiles(Long customerId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
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

