package com.ffcs.itm.web.basic.entity.dto;

import com.ffcs.itm.web.basic.entity.Organization;

public class OrganizationTreeItemDto extends Organization {
    
    private int childCount;
    
    private String tag;

    private String icon;
    
    private boolean isParent;
    
    private boolean open;
    
    public int getChildCount() {
        return childCount;
    }

    public void setChildCount(int childCount) {
        this.childCount = childCount;
    }
    
    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public boolean getIsParent() {
        return isParent;
    }

    public void setIsParent(boolean isParent) {
        this.isParent = isParent;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
}
