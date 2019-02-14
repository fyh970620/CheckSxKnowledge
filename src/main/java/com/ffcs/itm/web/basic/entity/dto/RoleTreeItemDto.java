package com.ffcs.itm.web.basic.entity.dto;

import com.ffcs.itm.web.basic.entity.Role;

public class RoleTreeItemDto extends Role {
    private int pId;
    private boolean isParent;

    public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	public boolean getIsParent() {
		return isParent;
	}
	public void setIsParent(boolean isParent) {
		this.isParent = isParent;
	}
}
