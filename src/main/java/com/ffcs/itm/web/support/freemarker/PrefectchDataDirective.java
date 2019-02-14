package com.ffcs.itm.web.support.freemarker;

import com.ffcs.itm.common.mapper.JsonMapper;
import com.ffcs.itm.web.support.HtmlUtils;
import freemarker.core.Environment;
import freemarker.template.*;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * 预设数据的 FreeMarker 标签
 * 用于将java controller 的设置(model attibute)对象，转化为 angularJs 的 rootScope 对象，或者转化为 json 文本存储于 隐藏的 textarea 中
 * 
 * 在页面中这样使用:
 * 		标签：<@prefectchData names="jsonBeanName,jsonBeanName2"/>
 * 		js 中调用: common.getJsonFromHtmlData('jsonBeanName'); common.getJsonFromHtmlData('jsonBeanName2');
 * 
 * 或者
 * 		<@prefectchData names="jsonBeanName" useAngularScope=true/>
 * 		js 中调用: $scope.prefectchDatas.jsonBeanName
 * 		
 */
@Component
public class PrefectchDataDirective implements TemplateDirectiveModel {
	private JsonMapper jsonMapper = new JsonMapper();
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
	    Iterable<String> names = DirectiveUtils.getStringList("names", params);

		StringBuilder sb = new StringBuilder();
		for (String name : names) {
			Object data = DirectiveUtils.getContext(env).getModel().get(name);
		
			if (data == null) {
				continue;
			}
			
			sb.append(getOutputData(name, data));
		}
		
		if (sb.length() > 0) {
			env.getOut().write(sb.toString());
		}
	}
	
	private String getOutputData(String name, Object data) {
		String jsonData = jsonMapper.toJson(data);
		
        return createHiddenData(name, jsonData);
	}
	
	/**
	 * 生成存储于 textarea 的数据
	 * @param name
	 * @param data
	 * @return 
	 */
	private String createHiddenData(String name, String data) {
		return HtmlUtils.outHtmlData(name, data);
	}
}
