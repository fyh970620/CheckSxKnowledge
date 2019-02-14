package com.bsnnms.bean.common.resource;

import java.util.Map;

public class ResourceInfo
{
	private final boolean isDebug;
	private final Map<String, ResourceConfig> allMap;
	private final ResourceConfig[] dynamicImports;
	private final String dynamicImportVersion;
	
	public ResourceInfo(boolean isDebug, Map<String, ResourceConfig> allMap,
			ResourceConfig[] dynamicImports, String dynamicImportVersion)
	{
		super();
		this.isDebug = isDebug;
		this.allMap = allMap;
		this.dynamicImports = dynamicImports;
		this.dynamicImportVersion = dynamicImportVersion;
	}

	public boolean isDebug()
	{
		return isDebug;
	}

	public Map<String, ResourceConfig> getAllMap()
	{
		return allMap;
	}

	public ResourceConfig[] getDynamicImports()
	{
		return dynamicImports;
	}

	public String getDynamicImportVersion()
	{
		return dynamicImportVersion;
	}

}
