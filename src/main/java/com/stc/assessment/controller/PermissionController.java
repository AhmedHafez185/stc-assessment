package com.stc.assessment.controller;

import com.stc.assessment.DTO.PermissionDTO;
import com.stc.assessment.DTO.RequestData;
import com.stc.assessment.DTO.ResponseData;
import com.stc.assessment.service.permissions.PermissionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Group")
public class PermissionController extends BaseController {
    private static final Logger logger = LogManager.getLogger(PermissionController.class);
    @Autowired
    private PermissionService permissionService;
    @PostMapping("/createPermission")
    public ResponseEntity<ResponseData> createPermission(@RequestBody RequestData requestData) {
        try {
            PermissionDTO permissionDTO = (PermissionDTO) responseReqBuilder.getRequestData(requestData, PermissionDTO.class);
            permissionService.createPermission(permissionDTO);
            ResponseData apiResponse = responseReqBuilder.wrapSuccessResponse(permissionDTO);
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            return wrapException(e, logger);
        }
    }
}