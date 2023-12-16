package com.stc.assessment.DTO;

public class DownloadFileDTO {
    private Long fileId;
    private Long groupId;

    public DownloadFileDTO() {
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
