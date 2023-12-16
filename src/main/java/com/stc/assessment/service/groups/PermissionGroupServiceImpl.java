package com.stc.assessment.service.groups;

import com.stc.assessment.DTO.PermissionGroupDTO;
import com.stc.assessment.entity.Permission;
import com.stc.assessment.entity.PermissionGroup;
import com.stc.assessment.repository.PermissionGroupRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PermissionGroupServiceImpl implements PermissionGroupService {
    @Autowired
    private PermissionGroupRepository permissionGroupRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public PermissionGroupDTO createPermissionGroup(PermissionGroupDTO permissionGroupDTO) {
        PermissionGroup permissionGroup = modelMapper.map(permissionGroupDTO, PermissionGroup.class);
        permissionGroupRepository.save(permissionGroup);
        permissionGroupDTO.setId(permissionGroup.getId());
        return permissionGroupDTO;
    }
}
