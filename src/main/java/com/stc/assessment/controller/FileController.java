package com.stc.assessment.controller;

import com.stc.assessment.DTO.DownloadFileDTO;
import com.stc.assessment.DTO.RequestData;
import com.stc.assessment.entity.Item;
import com.stc.assessment.repository.ItemRepository;
import com.stc.assessment.service.files.FileService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Files")
public class FileController extends BaseController {
    private static final Logger logger = LogManager.getLogger(FileController.class);

    @Autowired
    private FileService fileService;



    @GetMapping("/download")
    public ResponseEntity download(@RequestBody RequestData requestData) {
        try {
            DownloadFileDTO downloadFileDTO = (DownloadFileDTO) responseReqBuilder.getRequestData(requestData, DownloadFileDTO.class);
            byte[] fileContent = fileService.downloadFile(downloadFileDTO);
            ByteArrayResource resource = new ByteArrayResource(fileContent);

            // Set headers for the response
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + downloadFileDTO.getFileId());
            headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);

            // Return ResponseEntity with the file content and headers
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(fileContent.length)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        } catch (Exception e) {
            return wrapException(e, logger);
        }
    }


}
