package com.stc.assessment.DTO;

import com.stc.assessment.entity.Permission;

public class PermissionGroupDTO {
    private long id;
    private String groupName;

    public PermissionGroupDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

}
