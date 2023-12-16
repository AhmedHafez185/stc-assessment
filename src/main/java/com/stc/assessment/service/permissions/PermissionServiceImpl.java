package com.stc.assessment.service.permissions;

import com.stc.assessment.DTO.PermissionDTO;
import com.stc.assessment.entity.Permission;
import com.stc.assessment.entity.PermissionGroup;
import com.stc.assessment.repository.PermissionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public PermissionDTO createPermission(PermissionDTO permissionDTO) {
        Permission permission = modelMapper.map(permissionDTO, Permission.class);
        permission.setGroup(new PermissionGroup(permissionDTO.getGroupId()));
        permissionRepository.save(permission);
        permissionDTO.setId(permission.getId());
        return permissionDTO;
    }
}
