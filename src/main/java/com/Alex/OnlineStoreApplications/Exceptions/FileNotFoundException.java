package com.Alex.OnlineStoreApplications.Exceptions;

import java.io.IOException;

public class FileNotFoundException extends RuntimeException {

    public FileNotFoundException () {
        super("File not found");
    }
}
