package com.stc.assessment.service.files;

import com.stc.assessment.DTO.DownloadFileDTO;
import com.stc.assessment.entity.Files;
import com.stc.assessment.entity.Item;
import com.stc.assessment.repository.FileRepository;
import com.stc.assessment.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.util.Optional;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private ItemRepository itemRepository;

    @Override
    @Transactional
    public byte[] downloadFile(DownloadFileDTO downloadFileDTO) throws Exception {
        Optional<Files> file = fileRepository.findById(downloadFileDTO.getFileId());
        if (file.isPresent()) {
            if (!itemRepository.isHasEditAccess(file.get().getItem().getId(), "VIEW")) {
                throw new Exception("You don't has Edit Permission on this File");
            }
        } else {
            throw new Exception("File not exists");
        }
        return file.get().getBinaryContent();
    }
}
