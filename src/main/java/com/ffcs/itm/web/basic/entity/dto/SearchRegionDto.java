package com.ffcs.itm.web.basic.entity.dto;


/**
 * use by searchPanel
 */
public class SearchRegionDto {
	private long id;
	
	private String name;
	
	private long topId;
	
	private String topName;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getTopId() {
		return topId;
	}
	
	public String getTopIdStr() {
		return String.valueOf(topId);
	}

	public void setTopId(long topId) {
		this.topId = topId;
	}

	public String getTopName() {
		return topName;
	}

	public void setTopName(String topName) {
		this.topName = topName;
	}
}
