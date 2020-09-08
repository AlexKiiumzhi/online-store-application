package com.Alex.OnlineStoreApplications.Exceptions;

import java.io.IOException;

public class FileStorageException extends RuntimeException {

    public FileStorageException(String fileName) {
        super("Sorry! Filename contains invalid path sequence " + fileName);
    }

    public FileStorageException(String fileName, IOException ex) {
        super("Could not store file " + fileName + ". Please try again!", ex);
    }
}
