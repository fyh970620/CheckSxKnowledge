package com.ffcs.itm.web.basic.entity;

public class BasicTreeNode {
    private Long treeId;

    private String publicTreeKey;

    private Long pTreeId;

    private String treeLabel;

    private String treeIco;

    private Long sortId;

    private String state;

    private Long getValueCfgId;

    private String remark;

    private String unifyCode;
    
    private Long level;
    
	private Long busiId;
    
    private Long treeTypeId;
    
    private String disabled;

    private boolean isOpen = true;

    public Long getTreeId() {
        return treeId;
    }

    public void setTreeId(Long treeId) {
        this.treeId = treeId;
    }

    public String getPublicTreeKey() {
        return publicTreeKey;
    }

    public void setPublicTreeKey(String publicTreeKey) {
        this.publicTreeKey = publicTreeKey;
    }

    public Long getpTreeId() {
        return pTreeId;
    }

    public void setpTreeId(Long pTreeId) {
        this.pTreeId = pTreeId;
    }

    public String getTreeLabel() {
        return treeLabel;
    }

    public void setTreeLabel(String treeLabel) {
        this.treeLabel = treeLabel;
    }

    public String getTreeIco() {
        return treeIco;
    }

    public void setTreeIco(String treeIco) {
        this.treeIco = treeIco;
    }

    public Long getSortId() {
        return sortId;
    }
        

    public Long getLevel() {
		return level;
	}

	public void setLevel(Long level) {
		this.level = level;
	}

	public void setSortId(Long sortId) {
        this.sortId = sortId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getGetValueCfgId() {
        return getValueCfgId;
    }

    public void setGetValueCfgId(Long getValueCfgId) {
        this.getValueCfgId = getValueCfgId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUnifyCode() {
        return unifyCode;
    }

    public void setUnifyCode(String unifyCode) {
        this.unifyCode = unifyCode;
    }

    public Long getBusiId() {
		return busiId;
	}

	public void setBusiId(Long busiId) {
		this.busiId = busiId;
	}

	public Long getTreeTypeId() {
		return treeTypeId;
	}

	public void setTreeTypeId(Long treeTypeId) {
		this.treeTypeId = treeTypeId;
	}

	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}