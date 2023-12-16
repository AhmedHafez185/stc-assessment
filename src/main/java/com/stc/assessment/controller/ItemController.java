package com.stc.assessment.controller;

import com.stc.assessment.DTO.ItemDTO;
import com.stc.assessment.DTO.PermissionDTO;
import com.stc.assessment.DTO.RequestData;
import com.stc.assessment.DTO.ResponseData;
import com.stc.assessment.service.items.ItemService;
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
@RequestMapping("/Item")
public class ItemController extends BaseController {
    private static final Logger logger = LogManager.getLogger(ItemController.class);
    @Autowired
    private ItemService itemService;
    @PostMapping("/createSpace")
    public ResponseEntity<ResponseData> createSpace(@RequestBody RequestData requestData) {
        try {
            ItemDTO itemDTO = (ItemDTO) responseReqBuilder.getRequestData(requestData, ItemDTO.class);
            itemService.createSpace(itemDTO);
            ResponseData apiResponse = responseReqBuilder.wrapSuccessResponse(itemDTO);
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            return wrapException(e, logger);
        }
    }
    @PostMapping("/createFolder")
    public ResponseEntity<ResponseData> createFolder(@RequestBody RequestData requestData) {
        try {
            ItemDTO itemDTO = (ItemDTO) responseReqBuilder.getRequestData(requestData, ItemDTO.class);
            itemService.createFolder(itemDTO);
            ResponseData apiResponse = responseReqBuilder.wrapSuccessResponse(itemDTO);
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            return wrapException(e, logger);
        }
    }
    @PostMapping("/createFile")
    public ResponseEntity<ResponseData> createFile(@RequestBody RequestData requestData) {
        try {
            ItemDTO itemDTO = (ItemDTO) responseReqBuilder.getRequestData(requestData, ItemDTO.class);
            itemService.createFile(itemDTO);
            ResponseData apiResponse = responseReqBuilder.wrapSuccessResponse(itemDTO);
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e) {
            return wrapException(e, logger);
        }
    }
}