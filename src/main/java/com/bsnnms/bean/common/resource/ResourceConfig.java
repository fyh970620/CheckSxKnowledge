package com.bsnnms.bean.common.resource;

public class ResourceConfig {

	private String path;

	private String version;
	
	private String defaultTagName;

	private String importType;
	
	private int isDynamicLoad;
	
	public String getImportType() {
		return importType;
	}

	public void setImportType(String importType) {
		this.importType = importType;
	}

	public String getDefaultTagName() {
		return defaultTagName;
	}

	public void setDefaultTagName(String defaultTagName) {
		this.defaultTagName = defaultTagName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public void setIsDynamicLoad(int isDynamicLoad) {
		this.isDynamicLoad = isDynamicLoad;
	}

	public int getIsDynamicLoad() {
		return isDynamicLoad;
	}

}
