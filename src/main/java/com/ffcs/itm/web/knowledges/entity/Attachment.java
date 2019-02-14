package com.ffcs.itm.web.knowledges.entity;

/*附件*/
public class Attachment {
    private Long attachId;
    private String attachPath;
    private String attachName;

    public Long getAttachId() {
        return attachId;
    }

    public void setAttachId(Long attachId) {
        this.attachId = attachId;
    }

    public String getAttachPath() {
        return attachPath;
    }

    public void setAttachPath(String attachPath) {
        this.attachPath = attachPath;
    }

    public String getAttachName() {
        return attachName;
    }

    public void setAttacheName(String attachName) {
        this.attachName = attachName;
    }
}
