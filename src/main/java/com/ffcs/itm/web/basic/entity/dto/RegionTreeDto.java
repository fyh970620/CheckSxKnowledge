package com.ffcs.itm.web.basic.entity.dto;

import com.ffcs.itm.web.basic.entity.Region;

public class RegionTreeDto extends Region {
    
    private String icon;

    private boolean isParent;
    
    private boolean open;
    
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
