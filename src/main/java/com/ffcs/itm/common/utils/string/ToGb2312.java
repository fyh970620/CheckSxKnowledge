package com.ffcs.itm.common.utils.string;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Administrator
 *
 */
public class ToGb2312 {
	/**
	 *
	 *
	 * @author: panchh
	 * @修改记录：
	 *
	 * ==============================================================<br>
	 * 日期:2007-9-17      panchh         创建方法，并实现其功能
	 *
	 * ==============================================================<br>
	 */
	public ToGb2312() {
	}
	/**
	 *
	 * @param s String
	 * @return String
	 * @throws Exception Exception
	 * @author: panchh
	 * @修改记录：
	 *
	 * ==============================================================<br>
	 * 日期:2007-9-17      panchh         创建方法，并实现其功能
	 *
	 * ==============================================================<br>
	 */
	public static String convertinto(String s) throws Exception {
		if (s == null) {
			return null;
		}
		String str;
		byte[] inputBytes = s.getBytes("8859_1");
		str = new String(inputBytes, "GBK");
		return str;
	}
	/**
	 *
	 * @param s String
	 * @return String
	 * @author: panchh
	 * @修改记录：
	 *
	 * ==============================================================<br>
	 * 日期:2007-9-17      panchh         创建方法，并实现其功能
	 *
	 * ==============================================================<br>
	 */
	public static String delNameSpace(String s) {
		if (s == null) {
			return null;
		}
		String regEx = "\\sxmlns(|:ns\\d+)=\"http://[^\"]+\"|ns\\d+:";

		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(s);
		s = m.replaceAll("");
		return s;
	}
	/**
	 *
	 * @param s String
	 * @return String
	 * @throws Exception Exception
	 * @author: panchh
	 * @修改记录：
	 *
	 * ==============================================================<br>
	 * 日期:2007-9-17      panchh         创建方法，并实现其功能
	 *
	 * ==============================================================<br>
	 */
	public static String convertback(String s) throws Exception {
		if (s == null) {
			return null;
		}
		String str;
		String regEx = "\\sxmlns(|:ns\\d+)=\"http://[^\"]+\"|ns\\d+:";

		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(s);
		s = m.replaceAll("");

		byte[] inputBytes = s.getBytes("UTF-8");
		str = new String(inputBytes, "8859_1");
		return str;
	}
	/**
	 *
	 * @param s String
	 * @return String
	 * @throws Exception Exception
	 * @author: panchh
	 * @修改记录：
	 *
	 * ==============================================================<br>
	 * 日期:2007-9-17      panchh         创建方法，并实现其功能
	 *
	 * ==============================================================<br>
	 */
	public static String convertbackForOss(String s) throws Exception {
		byte[] inputBytes = s.getBytes("GBK");
		return new String(inputBytes, "ISO8859_1");
	}
	/**
	 *
	 * @param string String
	 * @return String
	 * @throws Exception Exception
	 * @author: panchh
	 * @修改记录：
	 *
	 * ==============================================================<br>
	 * 日期:2007-9-17      panchh         创建方法，并实现其功能
	 *
	 * ==============================================================<br>
	 */
	public static String getStr(String string) throws Exception {
		String s = "";
		if (string != null) {
			s = convertinto(string);
		}
		return s;
	}
	/**
	 *
	 * @param seed String
	 * @return String
	 * @throws Exception Exception
	 * @author: panchh
	 * @修改记录：
	 *
	 * ==============================================================<br>
	 * 日期:2007-9-17      panchh         创建方法，并实现其功能
	 *
	 * ==============================================================<br>
	 */
	public static String convertintoForXml(String seed) throws Exception {
		if (seed == null) {
			return null;
		}
		seed = convertinto(seed);
		seed = seed.replaceAll("<", "__1");
		seed = seed.replaceAll(">", "__2");
		seed = seed.replaceAll("&", "__3");
		seed = seed.replaceAll("'", "__4");
		seed = seed.replaceAll("\"", "__5");
		seed = seed.replaceAll(" ", "__6");
		seed = seed.replaceAll("\n", "__7");
		seed = seed.replaceAll("\r", "__8");
		seed = seed.replaceAll("\t", "__0");
		return seed;
	}
	/**
	 *
	 * @param seed String
	 * @return String
	 * @throws Exception Exception
	 * @author: panchh
	 * @修改记录：
	 *
	 * ==============================================================<br>
	 * 日期:2007-9-17      panchh         创建方法，并实现其功能
	 *
	 * ==============================================================<br>
	 */
	public static String convertForXml(String seed) throws Exception {
		// seed=convertinto(seed);
		seed = seed.replaceAll("__1", "<");
		seed = seed.replaceAll("__2", ">");
		seed = seed.replaceAll("__3", "&");
		seed = seed.replaceAll("__4", "'");
		seed = seed.replaceAll("__5", "\"");
		seed = seed.replaceAll("__6", " ");
		seed = seed.replaceAll("__7", " ");
		seed = seed.replaceAll("__8", " ");
		seed = seed.replaceAll("__0", " ");
		return seed;
	}

}