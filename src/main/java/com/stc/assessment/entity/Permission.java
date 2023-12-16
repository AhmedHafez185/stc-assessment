package com.stc.assessment.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "PERMISSIONS")
public class Permission {
    @Id
    @Column(name="ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic(optional = false)
    @Column(name = "USER_EMAIL", nullable = false)
    private String userEmail;
    @Basic(optional = false)
    @Column(name = "PERMISSION_LEVEL", nullable = false)
    private String permissionLevel;
    @ManyToOne
    @JoinColumn(name = "group_id")
    private PermissionGroup group;

    public Permission() {
    }

    public Permission(long id, String userEmail, String permissionLevel) {
        this.id = id;
        this.userEmail = userEmail;
        this.permissionLevel = permissionLevel;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPermissionLevel() {
        return permissionLevel;
    }

    public void setPermissionLevel(String permissionLevel) {
        this.permissionLevel = permissionLevel;
    }

    public PermissionGroup getGroup() {
        return group;
    }

    public void setGroup(PermissionGroup group) {
        this.group = group;
    }
}
