package com.ffcs.itm.web.support.freemarker;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import freemarker.core.Environment;
import freemarker.template.*;
import org.springframework.web.servlet.support.RequestContext;
import org.springframework.web.servlet.view.AbstractTemplateView;

import java.util.Map;

/**
 * FreeMarker 标签工具类
 * @author sa
 *
 */
public class DirectiveUtils {
	public static String getString(String name, Map<String, TemplateModel> params) throws TemplateException {
		TemplateModel model = params.get(name);
		if (model == null) {
			return null;
		}
		
		if (model instanceof TemplateScalarModel) {
			return ((TemplateScalarModel) model).getAsString();
		} else {
			throw new DirectiveException("无效的标签参数值，应该是 String 类型");
		}
	}
	

	public static Iterable<String> getStringList(String name, Map<String, TemplateModel> params) throws
            TemplateException {
		String str = getString(name, params);
		if (str == null) {
			return Lists.newArrayListWithExpectedSize(0);
		}
		
		return Splitter.on(',')
	       .trimResults()
	       .omitEmptyStrings()
	       .split(str);
	}
	
	public static Boolean getBoolean(String name, Map<String, TemplateModel> params) throws TemplateException {
		TemplateModel model = params.get(name);
		if (model == null) {
			return false;
		}
		
		if (model instanceof TemplateBooleanModel) {
			return ((TemplateBooleanModel) model).getAsBoolean();
		} else if (model instanceof TemplateNumberModel) {
			return ((TemplateNumberModel) model).getAsNumber().intValue() == 1;
		} else if (model instanceof TemplateScalarModel) {
			String bs = ((TemplateScalarModel) model).getAsString();
			return (bs.equals("1") || bs.equals("true"));
		} else {
			throw new DirectiveException("无效的标签参数值，应该是 Boolean 类型");
		}
	}
	
	public static RequestContext getContext(Environment env) throws TemplateModelException {
		TemplateModel ctx = env.getGlobalVariable(AbstractTemplateView.SPRING_MACRO_REQUEST_CONTEXT_ATTRIBUTE);
		return (RequestContext) ((AdapterTemplateModel) ctx).getAdaptedObject(RequestContext.class);
	}
}
