/*
 * Copyright 2002-2005 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ffcs.itm.web.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * apache common lang3 StringUtils 的补充
 */
public abstract class ItmStringUtils {
	private static final String NEAD_TO_CONVERT_CHAR = "([/:()!])";

	private static final Random ran = new Random();

	private static final String REG_POSTFIX = ".*(?=\\.)";

	public static String getUniqueID() {
		return Long.toString(System.currentTimeMillis()) + "_" + Integer.toString(ran.nextInt());
	}

	public static String getUniqueFileName(String fileName) {
		Pattern p = Pattern.compile(REG_POSTFIX);
		Matcher m = p.matcher(fileName);
		String uniqueFileName = getUniqueID();
		uniqueFileName = (m.find()) ? m.replaceFirst(uniqueFileName) : uniqueFileName;

		return uniqueFileName;
	}

	public static String encodeString(String sourceString, String sysCharset, String charset)
			throws UnsupportedEncodingException {
		if (sourceString == null || sourceString.length() == 0)
			return sourceString;
		return new String(sourceString.getBytes(sysCharset), "GB2312");
	}

	public static String[] encodeArray(String[] sourceArray, String sysCharset, String charset)
			throws UnsupportedEncodingException {
		if (sourceArray == null || sourceArray.length == 0)
			return sourceArray;
		String[] result = new String[sourceArray.length];
		for (int i = 0; i < sourceArray.length; i++) {
			result[i] = encodeString(sourceArray[i], sysCharset, charset);
		}
		return result;
	}

	public static String gbToUtf8(String src) {
		byte[] b = src.getBytes();
		char[] c = new char[b.length];
		for (int x = 0; x < b.length; x++) {
			c[x] = (char) (b[x] & 0x00FF);
		}
		return new String(c);
	}

	/**
	 * 将以 aaa:bbb:ccc:ddd:eee 格式的字符串转换成 map 对象存储
	 *
	 * @param param
	 * @return Map
	 * @author chenxunxin
	 */
	public static void param2Map(Map<String, Object> paramMap, String param, String value) {
		if (!(param == null || "".equals(param) || value == null)) {
			if (param.indexOf(":") == -1) {
				paramMap.put(param, value);
			} else {
				String key = param.substring(0, param.indexOf(":"));
				Map<String, Object> map = null;
				if (paramMap.containsKey(key)) {
					map = (Map<String, Object>) paramMap.get(key);
				} else {
					map = new HashMap<String, Object>();
					paramMap.put(key, map);
				}
				param = param.substring(param.indexOf(":") + 1, param.length());
				param2Map(map, param, value);
			}
		}
	}

	/**
	 *
	 * 将dom4j的文档转换成指定编码格式的字符串
	 *
	 * @param document
	 * @return
	 */
	public static String document2str(Document document, String chartset) {
		String result = "";
		OutputFormat format;
		ByteArrayOutputStream out;
		try {
			format = OutputFormat.createPrettyPrint();
			format.setEncoding(chartset);
			out = new ByteArrayOutputStream();
			XMLWriter writer = new XMLWriter(out, format);
			writer.write(document);
			writer.flush();
			writer.close();
			result = out.toString(format.getEncoding());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 比较两个字符串的相似度
	 *
	 * @param str
	 * @param target
	 * @return
	 */
	private static int compare(String str, String target) {
		int d[][]; // 矩阵
		int n = str.length();
		int m = target.length();
		int i; // 遍历str的
		int j; // 遍历target的
		char ch1; // str的
		char ch2; // target的
		int temp; // 记录相同字符,在某个矩阵位置值的增量,不是0就是1
		if (n == 0) {
			return m;
		}
		if (m == 0) {
			return n;
		}
		d = new int[n + 1][m + 1];
		for (i = 0; i <= n; i++) { // 初始化第一列
			d[i][0] = i;
		}
		for (j = 0; j <= m; j++) { // 初始化第一行
			d[0][j] = j;
		}
		for (i = 1; i <= n; i++) { // 遍历str
			ch1 = str.charAt(i - 1);
			// 去匹配target
			for (j = 1; j <= m; j++) {
				ch2 = target.charAt(j - 1);
				if (ch1 == ch2) {
					temp = 0;
				} else {
					temp = 1;
				}
				// 左边+1,上边+1, 左上角+temp取最小
				d[i][j] = min(d[i - 1][j] + 1, d[i][j - 1] + 1, d[i - 1][j - 1] + temp);
			}
		}
		return d[n][m];
	}

	private static int min(int one, int two, int three) {
		return (one = one < two ? one : two) < three ? one : three;
	}

	/**
	 * 计算两个字符串的相似度
	 *
	 * @param str
	 * @param target
	 * @return
	 */
	public static float getSimilarityRatio(String str, String target) {
		return 1 - (float) compare(str, target) / Math.max(str.length(), target.length());
	}

	/**
	 * 在XML文档中搜索指定标签的元素, 返回符合条件的元素数组.
	 */
	public static String[] getElementsByTag(String xmlDocument, String tagName) {
		Pattern p = Pattern.compile("<" + tagName + "[^>]*?((>.*?</" + tagName + ">)|(/>))");
		Matcher m = p.matcher(xmlDocument);
		ArrayList<String> al = new ArrayList<String>();
		while (m.find())
			al.add(m.group());
		String[] arr = al.toArray(new String[al.size()]);
		al.clear();
		return arr;
	}

	/**
	 * 在XML文档中用xpath模式提取元素,以#为分隔符 如
	 * ROOT#PARENT#CHILD表示提取ROOT元素下的PARENT元素下的CHILD元素
	 */
	public static String getElementBySinglePath(String xmlDocument, String singlePath) {
		String[] path = singlePath.split("#");
		String lastTag = path[path.length - 1];
		String tmp = "(<" + lastTag + "[^>]*?((>.*?</" + lastTag + ">)|(/>)))"; // 最后一个元素,可能是<x>v</x>形式或<x/>形式
		for (int i = path.length - 2; i >= 0; i--) {
			lastTag = path[i];
			tmp = "<" + lastTag + ">.*" + tmp + ".*</" + lastTag + ">";
		}
		Pattern p = Pattern.compile(tmp);
		Matcher m = p.matcher(xmlDocument);
		if (m.find())
			return m.group(1);
		return "";
	}

	/**
	 * 获取指定元素的文本内容
	 *
	 * @param elementString
	 *            String
	 * @return String
	 */
	public static String getElementText(String elementString) {
		Pattern p = Pattern.compile(">([^<>]*)<");
		Matcher m = p.matcher(elementString);
		if (m.find())
			return m.group(1);
		return "";
	}

	public static String getProcessPID() {
		String pid = "";
		try {
			Properties props = System.getProperties();
			String separator = props.getProperty("file.separator");
			// File binDir = new File("").getAbsoluteFile();
			if ("/".equals(separator)) {
				RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
				String name = runtime.getName();
				pid = name.substring(0, name.indexOf('@'));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pid;
	}

	/** 根据网卡取本机配置的IP **/
	public static String getLocalIp() {
		String localIp = "";

		try {
			localIp = InetAddress.getLocalHost().getHostAddress();
			if ("/".equals(System.getProperties().getProperty("file.separator"))) {
				Enumeration<?> netInterfaces = NetworkInterface.getNetworkInterfaces();
				InetAddress ip = null;
				while (netInterfaces.hasMoreElements()) {
					NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
					// if(ni.getName().equals("eth0")) {
					Enumeration<?> e2 = ni.getInetAddresses();
					while (e2.hasMoreElements()) {
						ip = (InetAddress) e2.nextElement();
						if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress()
								&& ip.getHostAddress().indexOf(":") == -1) {
							localIp = ip.getHostAddress();
							break;
						} else {
							continue;
						}
					}
					// }
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return localIp;
	}

	public static String[] getListByReg(String input, Pattern reg) {
		String[] values;
		Matcher m = reg.matcher(input);
		if (m.find()) {
			int groupLength = m.groupCount();
			values = new String[groupLength];
			for (int i = 0; i < groupLength; i++) {
				values[i] = m.group(i + 1);
			}
		} else {
			values = new String[0];
		}
		return values;
	}

	public static String getKeyByReg(String input, Pattern reg) {
		String[] values = getListByReg(input, reg);
		if (values.length > 0) {
			return values[0];
		} else {
			return null;
		}
	}

	// solr query need to convert meaning
	public static String convertMeaningChar(String temp) {
		if (temp == null)
			return "";
		temp = temp.replaceAll(NEAD_TO_CONVERT_CHAR, "\\\\$1");
		return temp;
	}

	/**
	 * 判断字符串是否为数字
	 *
	 * @param str
	 *            空字符串默认是数字
	 * @param rule
	 *            具体的正则表达式 [0-9]* 判断正整数 -?[0-9]+.?[0-9]+ 所有数字
	 * @return
	 */
	public static boolean isNumeric(String str, String regular) {
		if (str == null || str.length() == 0) {
			String rule = regular == null ? "[0-9]*" : regular;
			Pattern pattern = Pattern.compile(rule);
			Matcher isNum = pattern.matcher(str);

			return isNum.matches();
		} else {
			return true;
		}
	}

	public static boolean isNumeric(String str) {
		return isNumeric(str, null);
	}

	/**
	 * 格式化浮点型数字
	 */
	public static String formatFloatToStr(Float str) {
		if (str == null) {
			return "";
		} else {
			java.text.DecimalFormat df = new java.text.DecimalFormat("0.00");
			return df.format(str);
		}
	}

	// 生成8位短UUID，在本机范围内基本唯一，不可大范围使用
	public static String generateShortUuid() {
		StringBuffer shortBuffer = new StringBuffer();
		String uuid = UUID.randomUUID().toString().replace("-", "");
		for (int i = 0; i < 8; i++) {
			String str = uuid.substring(i * 4, i * 4 + 4);
			int x = Integer.parseInt(str, 16);
			shortBuffer.append(chars[x % 0x3E]);
		}

		return shortBuffer.toString();
	}

	private static String[] chars = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
			"n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7",
			"8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S",
			"T", "U", "V", "W", "X", "Y", "Z" };

	public static String getNotNullStr(Object o) {
		if (o == null) {
			return "";
		} else {
			return StringUtils.defaultString(o.toString(), "");
		}
	}
}