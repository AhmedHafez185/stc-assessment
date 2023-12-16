package com.stc.assessment.repository;

import com.stc.assessment.entity.Files;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<Files,Long> {
}
