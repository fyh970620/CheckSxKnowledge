package com.ffcs.itm.web.basic.entity.dto;

import java.util.ArrayList;
import java.util.List;

public class MenuDto {

    private long slevel;
    
    private long catalogId;
    
    private String shortDescription;
    
    private long parentCatalogId;
    
    private String icon;
    
    private String remark;
    
    private String serverUrlName;
    
    private List<MenuDto> childMenuList;
     
    private String path;
    
    private List<MenuDto> moreMenuList;
    
    private String openType;

    public long getSlevel() {
        return slevel;
    }

    public void setSlevel(long slevel) {
        this.slevel = slevel;
    }

    public long getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(long catalogId) {
        this.catalogId = catalogId;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public long getParentCatalogId() {
        return parentCatalogId;
    }

    public void setParentCatalogId(long parentCatalogId) {
        this.parentCatalogId = parentCatalogId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getServerUrlName() {
        return serverUrlName;
    }

    public void setServerUrlName(String serverUrlName) {
        this.serverUrlName = serverUrlName;
    }

    public List<MenuDto> getChildMenuList() {
        if(this.childMenuList == null) {
            this.setChildMenuList(new ArrayList<MenuDto>());
        }
        return this.childMenuList;
    }

    public void setChildMenuList(List<MenuDto> childMenuList) {
        this.childMenuList = childMenuList;
    }
    
    public void addChildMenuDto(MenuDto menuDto) {
        if(this.childMenuList == null) {
            this.setChildMenuList(new ArrayList<MenuDto>());
        }
        this.childMenuList.add(menuDto);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<MenuDto> getMoreMenuList() {
        if(this.moreMenuList == null) {
            this.setMoreMenuList(new ArrayList<MenuDto>());
        }
        return this.moreMenuList;
    }

    public void setMoreMenuList(List<MenuDto> moreMenuList) {
        this.moreMenuList = moreMenuList;
    }

    public void addMoreMenuDto(MenuDto menuDto) {
        if(this.moreMenuList == null) {
            this.setMoreMenuList(new ArrayList<MenuDto>());
        }
        this.moreMenuList.add(menuDto);
    }

    public String getOpenType() {
        return openType;
    }

    public void setOpenType(String openType) {
        this.openType = openType;
    }
}
