package com.stc.assessment.controller;

import com.stc.assessment.DTO.PermissionGroupDTO;
import com.stc.assessment.DTO.RequestData;
import com.stc.assessment.DTO.ResponseData;
import com.stc.assessment.service.groups.PermissionGroupService;
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
public class PermissionGroupController extends BaseController {
    private static final Logger logger = LogManager.getLogger(PermissionGroupController.class);
@Autowired
private PermissionGroupService groupService;
    @PostMapping("/createPermissionGroup")
    public ResponseEntity<ResponseData> createPermissionGroup(@RequestBody RequestData requestData) {
        try {
            PermissionGroupDTO permissionGroupDTO = (PermissionGroupDTO) responseReqBuilder.getRequestData(requestData, PermissionGroupDTO.class);
            groupService.createPermissionGroup(permissionGroupDTO);
            ResponseData apiResponse = responseReqBuilder.wrapSuccessResponse(permissionGroupDTO);
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            return wrapException(e, logger);
        }
    }
}
