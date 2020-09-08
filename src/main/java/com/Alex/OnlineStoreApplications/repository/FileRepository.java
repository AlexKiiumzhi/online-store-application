package com.Alex.OnlineStoreApplications.repository;

import com.Alex.OnlineStoreApplications.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository <File, Long> {
    }
