package com.ffcs.itm.web.common.utils;

import com.google.common.collect.Sets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.ClassUtils;

import java.io.IOException;
import java.util.Set;

public class PackageUtils {
	private final static Logger logger = LoggerFactory.getLogger(PackageUtils.class);
	// '#' 会被替换成当前的classpath
	private final static String prefixReg = "^(#)|(.+?/classes/main/)|(.+?/classes/)|(.+?\\.jar!/)";

	/**
	 *
	 * 获取匹配的包名列表，支持通配符，如 getPackage("**.ffcs.itm.*") 返回 com.ffcs.itm.utils,com.ffcs.itm.common
	 * @param packageName 包名，
	 * @return 返回匹配的包名列表
	 */
	public static Set<String> getPackages(String packageName) {

		//利用ClassLoad得到当前classpath对应的路径
		String defaultClassPath = ClassUtils.getDefaultClassLoader().getResource("").getPath();
		String rootPathReg = prefixReg.replaceFirst("#", defaultClassPath);
		String packagePath = ClassUtils.convertClassNameToResourcePath(packageName);
		String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + packagePath;
		ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();

		Resource[] resources;
		try {
			resources = resourcePatternResolver.getResources(packageSearchPath);
			Set<String> packageNames = Sets.newHashSet();
			String _path, _packageName;
			for (Resource resource : resources) {
				_path = resource.getURL().getPath().replaceFirst("/$", "");
				_packageName = ClassUtils.convertResourcePathToClassName(_path.replaceFirst(rootPathReg, ""));
				packageNames.add(_packageName);
			}

			return packageNames;

		} catch (IOException e) {
			logger.error("资源路径不存在:" + packageSearchPath, e);
			return Sets.newHashSetWithExpectedSize(0);
		}
	}
}