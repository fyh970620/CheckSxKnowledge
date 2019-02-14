package com.ffcs.itm.web.support;

import java.util.HashMap;
import java.util.Map;

public class HtmlUtils {
	private final static StringTemplate HTML_DATA_TEMPLATE = new StringTemplate(
			"<TEXTAREA id=\"{$key}.{ds}\" style=\"display:none\">{$data}</TEXTAREA>");

	public static String outHtmlData(String id, String data) {
		String htmlData = HtmlUtils.Encode(data);
		Map<String, String> attr = new HashMap<String, String>(2);
		attr.put("key", id);
		attr.put("data", htmlData);
		return HTML_DATA_TEMPLATE.apply(attr);
	}
	
        /**
         * 编码XML,将一些特殊字符如'&','<','>','"'进行转译
         * 
         * @param str
         *                需要编码的字符串
         * @return 编码后的字符串
         */
        public static String Encode(String str) {
                str = HtmlUtils.toNoNull(str);
                StringBuffer tmp = new StringBuffer();
                for (int i = 0; i < str.length(); i++) {
                        char a = str.charAt(i);
                        if (a == '&') 
                        {
                                tmp.append("&amp;");
                        }
                        else if (a == '<') 
                        {
                                tmp.append("&lt;");
                        }
                        else if (a == '>') 
                        {
                                tmp.append("&gt;");
                        }
                        else if (a == '"') 
                        {
                                tmp.append("&quot;");
                        }
                        else 
                        {
                                tmp.append(a);
                        }
                }
                return tmp.toString();
        }
        
        public static String toNoNull(String str)
        {
            return (str == null) ? "" : str;
        }
}
