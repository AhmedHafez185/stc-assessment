package com.stc.assessment.service.files;

import com.stc.assessment.DTO.DownloadFileDTO;
import org.springframework.transaction.annotation.Transactional;

public interface FileService {
    public byte[] downloadFile(DownloadFileDTO downloadFileDTO) throws Exception;
}
