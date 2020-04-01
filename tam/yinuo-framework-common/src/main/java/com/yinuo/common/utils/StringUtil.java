package com.yinuo.common.utils;


import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类, 继承org.apache.commons.lang3.StringUtils类
 */
public class StringUtil extends org.apache.commons.lang3.StringUtils {

	public static final String NUM_REGEX = ".*\\d+.*";
	public static final String MOBILE_REGEX = "^((13[0-9])|(15[0-9])|(18[0-9])|(17[0-9]))\\d{8}$";

	/**
	 * @desc 将String类型数据去除首尾两端的空格之后,转换成字符串
	 * @param str 需要进行去除首尾两端的空格的字符串
	 * @param defaultVal 默认值
	 * @return
	 */
	public static String trim(String str, String defaultVal) {
		if(isNull(str)){
			return defaultVal;
		}
		return trim(str);
	}


	/**
	 * 获取一定长度的随机数字字符串。
	 *
	 * @param length 指定字符串长度。
	 * @return 一定长度的数字字符串。
	 */
	public static String randomNumberString(int length) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < length; i++) {
			stringBuilder.append(new SecureRandom().nextInt(10));
		}
		return stringBuilder.toString();
	}

	/**
	 * @desc 判断指定的字符串是否为空
	 * @param str
	 * @return
	 */
	public static boolean isNull(String str) {
		return StringUtils.isBlank(str)
				|| "null".equals(str.toLowerCase())
				|| "".equals(str.trim())
				|| str.trim().length() == 0
				|| "undefined".equals(str);
	}

	/**
	 * @desc 将指定字符串的首字母转换成大写,其他字符维持不变
	 * @param str 需要转换的字符串
	 * @return
	 */
	public static String toFirstUpperCase(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1, str.length());
	}

	public static String lowerFirst(String str){
		if(StringUtil.isBlank(str)) {
			return "";
		} else {
			return str.substring(0,1).toLowerCase() + str.substring(1);
		}
	}
	
	public static String upperFirst(String str){
		if(StringUtil.isBlank(str)) {
			return "";
		} else {
			return str.substring(0,1).toUpperCase() + str.substring(1);
		}
	}

	/**
	 * 替换掉HTML标签方法
	 */
	public static String replaceHtml(String html) {
		if (isBlank(html)){
			return "";
		}
		String regEx = "<.+?>";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(html);
		return m.replaceAll("");
	}

	/**
	 * 缩略字符串（不区分中英文字符）
	 * @param str 目标字符串
	 * @param length 截取长度
	 * @return
	 */
	public static String abbr(String str, int length) {
		if (str == null) {
			return "";
		}
		try {
			StringBuilder sb = new StringBuilder();
			int currentLength = 0;
			for (char c : replaceHtml(StringEscapeUtils.unescapeHtml4(str)).toCharArray()) {
				currentLength += String.valueOf(c).getBytes("GBK").length;
				if (currentLength <= length - 3) {
					sb.append(c);
				} else {
					sb.append("...");
					break;
				}
			}
			return sb.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 缩略字符串（替换html）
	 * @param str 目标字符串
	 * @param length 截取长度
	 * @return
	 */
	public static String rabbr(String str, int length) {
		return abbr(replaceHtml(str), length);
	}
		
	
	/**
	 * 转换为Double类型
	 */
	public static Double toDouble(Object val){
		if (val == null){
			return 0D;
		}
		try {
			return Double.valueOf(trim(val.toString()));
		} catch (Exception e) {
			return 0D;
		}
	}

	/**
	 * 转换为Float类型
	 */
	public static Float toFloat(Object val){
		return toDouble(val).floatValue();
	}

	/**
	 * 转换为Long类型
	 */
	public static Long toLong(Object val){
		return toDouble(val).longValue();
	}

	/**
	 * 转换为Integer类型
	 */
	public static Integer toInteger(Object val){
		return toLong(val).intValue();
	}

	/**
	 * 转义mysql 查询 特殊  字符   %
	 * @param text
	 * @return string v
	 */
	public static String escapeText(String text){
		if(StringUtils.isBlank(text)){
			return "";
		}
		return text.replaceAll("%", "\\\\%");
	}
	
	/**
	 * @desc 按照指定的分隔符将字符串分割后存入字符串List
	 * @param str 需要分割的字符串
	 * @param split 分隔符
	 * @return
	 */
	public static List<String> strToList(String str, String split) {
		if(isEmpty(str)) {
			return null;
		}
		// 按照指定分隔符进行分割成字符串数组
		String[] sArr = str.split(split);
		return Arrays.asList(sArr);
	}

	/**
	 * @desc 得到一个字符串的长度,显示的长度,一个汉字或日韩文长度为2,英文字符长度为1
	 * @param s 需要得到长度的字符串
	 * @return int 得到的字符串长度
	 */
	public static int calcStrLen(String s) {
		int len = 0;
		if(isEmpty(s)){
			return len;
		}

		String chinese = "[\u4e00-\u9fa5]";
		// 获取字段值的长度,如果含中文字符,则每个中文字符长度为2,否则为1
		for (int i = 0; i < s.length(); i++) {
			// 获取一个字符
			String temp = s.substring(i, i + 1);
			// 判断是否为中文字符
			if (temp.matches(chinese)) {
				// 如果为UTF-8编号,则中文字符长度为3,否则为2
				len += 3;
			} else {
				// 其他字符长度为1
				len += 1;
			}
		}
		return len;
	}

	/**
	 * @desc 判断传进来的字符串，是否 大于指定的字节，如果大于递归调用直到小于指定字节数 ，一定要指定字符编码，因为各个系统字符编码都不一样，字节数也不一样
	 * @param s   原始字符串
	 * @param num 传进来指定字节数
	 * @return String 截取后的字符串
	 * throws UnsupportedEncodingException
	 */
	public static String subStr(String s, int num) throws UnsupportedEncodingException {
		int changdu = s.getBytes("UTF-8").length;
		if (changdu > num) {
			s = s.substring(0, s.length() - 1);
			s = subStr(s, num);
		}
		return s;
	}

	/**
	 * @desc 判断字符串中是否包含数字
	 * @param str 需要进行判断的字符串
	 * @return
     */
	public static boolean isContainNum(String str){
		Pattern pattern = Pattern.compile(NUM_REGEX);
		Matcher isNum = pattern.matcher(str);
		return isNum.matches();
	}


	/**
	 * @desc 将驼峰式命名的字符串转换为下划线大写方式。如果转换前的驼峰式命名的字符串为空，则返回空字符串
	 * 例如：HelloWorld->HELLO_WORLD
	 * @param name 转换前的驼峰式命名的字符串
	 * @return 转换后下划线大写方式命名的字符串
	 */
	public static String underscoreName(String name) {
		StringBuilder result = new StringBuilder();
		if (name != null && name.length() > 0) {
			// 将第一个字符处理成大写
			result.append(name.substring(0, 1).toUpperCase());
			// 循环处理其余字符
			for (int i = 1; i < name.length(); i++) {
				String s = name.substring(i, i + 1);
				// 在大写字母前添加下划线
				if (s.equals(s.toUpperCase()) && !Character.isDigit(s.charAt(0))) {
					result.append("_");
				}
				// 其他字符直接转成大写
				result.append(s.toUpperCase());
			}
		}
		return result.toString();
	}

	/**
	 * @desc 将下划线大写方式命名的字符串转换为驼峰式。如果转换前的下划线大写方式命名的字符串为空，则返回空字符串
	 * 例如：HELLO_WORLD->helloWorld
	 * @param name 转换前的下划线大写方式命名的字符串
	 * @return 转换后的驼峰式命名的字符串
	 */
	public static String cameLowerName(String name) {
		name = camelName(name);
		if(isNull(name)){
			return "";
		}
		return  name.substring(0, 1).toLowerCase() + name.substring(1);
	}


	/**
	 * @desc 将下划线大写方式命名的字符串转换为驼峰式。如果转换前的下划线大写方式命名的字符串为空，则返回空字符串
	 * 例如：HELLO_WORLD->HelloWorld
	 * @param name 转换前的下划线大写方式命名的字符串
	 * @return 转换后的驼峰式命名的字符串
	 */
	public static String camelName(String name) {
		name = name.toLowerCase();
		StringBuilder result = new StringBuilder();
		// 快速检查
		if(isNull(name)) {
			// 没必要转换
			return "";
		} else if (!name.contains("_")) {
			// 不含下划线，仅将首字母小写
			return name.substring(0, 1).toLowerCase() + name.substring(1);
		}
		// 用下划线将原始字符串分割
		String camels[] = name.split("_");
		for (String camel :  camels) {
			// 跳过原始字符串中开头、结尾的下换线或双重下划线
			if (camel.isEmpty()) {
				continue;
			}
			camel = trim(camel);
			// 处理真正的驼峰片段, 首字母大写
			result.append(camel.substring(0, 1).toUpperCase());
			result.append(camel.substring(1));
		}
		return result.toString();
	}

	/**
	 * @desc 将字符串转换成Map对象
	 * @param str 需要转换的字符串
	 * @param pSplit 外层分隔符
	 * @param sSplit 内衬分隔符
	 */
	public static Map<String, Object> strToMap(String str, String pSplit, String sSplit) {
		Map<String, Object> dataMap = new HashMap<>();
		if(isNull(str)){
			return dataMap;
		}

		String [] strArr = str.split(pSplit, -1);
		for(int i=0; i<strArr.length; i++) {
			String[] sonArr = strArr[i].split(sSplit, -1);
			if("status".equals(sonArr[0])) {
				dataMap.put(sonArr[0], StringUtil.strToList(sonArr[1], ","));
			} else {
				dataMap.put(sonArr[0], sonArr[1]);
			}
		}
		return dataMap;
	}

	/**
	 * @desc 根据指定字符串和指定的分隔符,计算该字符串被分割的数据的记录条数
	 * @param str 需要截取计算的字符串
	 * @param split 分隔符
	 * @return
	 */
	public static int calcStrSplitNum(String str, String split) {
		return str.split(split, -1).length;
	}

	/**
	 * @desc 处理特殊的HTML字符串编码
	 * @param str
	 * @return
	 */
	public static String HTMLEncode(String str) {
		if (str != null) {
			//需要放在前面替换，以防止替换掉其他已经替换过一次的，如 "\\", "&#92;"
			str = replace(str, "&", "&amp;");
			str = replace(str, "'", "&#39;");
			str = replace(str, "\"", "&quot;");
			str = replace(str, "<", "&lt;");
			str = replace(str, ">", "&gt;");
			str = replace(str, "<<", "&raquo;");
			str = replace(str, ">>", "&laquo;");
			str = replace(str, "'", "");
			str = replace(str, "\"", "");
			str = replace(str, "\\r\\n", "\n");
			str = replace(str, "\r\n", "\n");
			//需要放在\n之后替换，以防止替换掉\n的\
			str = replace(str, "\\", "&#92;");
			str = replace(str, "  ", "　");
			str = replace(str, "&amp;amp;", "&amp;");
			str = replace(str, "&amp;quot;", "&quot;");
			str = replace(str, "&amp;lt;", "&lt;");
			str = replace(str, "&amp;gt;", "&gt;");
			str = replace(str, "&amp;nbsp;", "&nbsp;");
		}
		return str;
	}

	/**
	 * @desc 判断一个字符串是否为手机号码
	 * @param mobiles 手机号码串
	 * @return
	 */
	public static boolean isMobileNo(String mobiles) {
		Pattern p = Pattern.compile(MOBILE_REGEX);
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	/**
	 * @desc 根据传递的值,如果为空,则赋予默认之
	 * @param value 传递的需要判断的字符串
	 * @param  defVal 当为空时,设置的默认之
	 * @return
	 */
	public static String getValByDef(String value, String defVal) {
		if(isNull(value)){
			return defVal;
		}

		return trim(value);
	}

	/**
	 * @desc 用值填充属性指定字符串中的占位符{0},{1}...,值的顺序必须和参数的顺序是一致的
	 * @param value 指定的字符串
	 * @param values 对应属性文件中的占位符的信息
	 * @return 将占位符中的信息对应填充后的字符串
	 */
	public static String getValueToRepSplace(String value, String... values) {

		//如果没有参数
		if (values == null || values.length == 0) {
			return value;
		}
		//如果属性文件中没有值,则返回空字符串
		if (StringUtil.isNull(value)) {
			return "";
		}

		StringBuffer buffer = new StringBuffer();

		//遍历参数数组
		for (int i = 0; i < values.length; i++) {
			//替换前清空原有替换值
			buffer.delete(0, buffer.length());
			Pattern pattern = Pattern.compile("\\{" + i + "\\}");
			Matcher matcher = pattern.matcher(value);
			while (matcher.find()) {
				matcher.appendReplacement(buffer, values[i]);
			}
			matcher.appendTail(buffer);
			//进行下一次替换
			value = buffer.toString();
		}
		//返回后替换的字符串
		return buffer.toString();
	}

	/**
	 * @desc java实现javascript中的escape编码函数;多用于URL编码与解码
	 * @param src 需要进行编码的字符串
	 * @return
	 */
	public static String escape(String src) {
		int i;
		char j;
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length() * 6);
		for (i = 0; i < src.length(); i++) {
			j = src.charAt(i);
			if (Character.isDigit(j) || Character.isLowerCase(j)
					|| Character.isUpperCase(j)){
				tmp.append(j);
			}
			else if (j < 256) {
				tmp.append("%");
				if (j < 16){
					tmp.append("0");
				}
				tmp.append(Integer.toString(j, 16));
			} else {
				tmp.append("%u");
				tmp.append(Integer.toString(j, 16));
			}
		}
		return tmp.toString();
	}

	/**
	 * @desc java实现javascript中的unescape解码函数;多用于URL编码与解码
	 * @param src 需要进行解码的字符串
	 * @return
	 */
	public static String unescape(String src) {
		if(isNull(src)){
			return null;
		}

		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length());
		int lastPos = 0, pos = 0;
		char ch;
		while (lastPos < src.length()) {
			pos = src.indexOf("%", lastPos);
			if (pos == lastPos) {
				if (src.charAt(pos + 1) == 'u') {
					ch = (char) Integer.parseInt(
							src.substring(pos + 2, pos + 6), 16);
					tmp.append(ch);
					lastPos = pos + 6;
				} else {
					ch = (char) Integer.parseInt(
							src.substring(pos + 1, pos + 3), 16);
					tmp.append(ch);
					lastPos = pos + 3;
				}
			} else {
				if (pos == -1) {
					tmp.append(src.substring(lastPos));
					lastPos = src.length();
				} else {
					tmp.append(src.substring(lastPos, pos));
					lastPos = pos;
				}
			}
		}
		return tmp.toString();
	}

	/**
	 * @desc 去掉字符串中的所有html标签
	 * @param htmlStr
	 * @return
	 */
	public static String delHTMLTag(String htmlStr){
		String regEx_script="<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式
		String regEx_style="<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式
		String regEx_html="<[^>]+>"; //定义HTML标签的正则表达式

		Pattern p_script=Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE);
		Matcher m_script=p_script.matcher(htmlStr);
		htmlStr=m_script.replaceAll(""); //过滤script标签

		Pattern p_style=Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE);
		Matcher m_style=p_style.matcher(htmlStr);
		htmlStr=m_style.replaceAll(""); //过滤style标签

		Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE);
		Matcher m_html=p_html.matcher(htmlStr);
		htmlStr=m_html.replaceAll(""); //过滤html标签

		return htmlStr.trim(); //返回文本字符串
	}

	/**
	 * @desc 使用SecureRandom随机生成Long字符串.
	 */
	public static String randomLong() {
		SecureRandom random = new SecureRandom();
		return String.valueOf(Math.abs(random.nextLong()));
	}
	
	
	/**
	 * 字符串 运算 转换   返回 计算 结果    ( 暂时  简单 处理  计算 结果 值  )
	 * source= "10+({0}{1}-1)*2"
	 * values="1","2"
	 * @param source
	 * @param values
	 * @return Object
	 * @throws Exception
	 */
	public static Double getExpressEval(String source, String... values) throws Exception {
		String script=getValueToRepSplace(source, values);
		ScriptEngineManager manager = new ScriptEngineManager();  
	    ScriptEngine engine = manager.getEngineByName("js");
	    Object object=engine.eval(script);
	    if(object!=null){
	    	return (Double)object;
	    }
	    return new Double(0);
	}

	public static boolean isLetterDigitOrChinese(String str) {
		String regex = "^[a-z0-9A-Z\u4e00-\u9fa5]+$";//其他需要，直接修改正则表达式就好
		return str.matches(regex);
	}

	/**
	 * @desc 判断字符串是否为字母
	 * @param str 需要判断的字符串
	 * @return
	 */
	public static boolean isLetter(String str) {
		String regex = "^[a-zA-Z]+$";//其他需要，直接修改正则表达式就好
		return str.matches(regex);
	}

	/**
	 * @desc 判断字符串是否为数字
	 * @param str 需要判断的字符串
	 * @return
	 */
	public static boolean isDigit(String str) {
		String regex = "^[0-9]+$";//其他需要，直接修改正则表达式就好
		return str.matches(regex);
	}

	/**
	 * @desc 判断字符串是否为中文
	 * @param str 需要判断的字符串
	 * @return
	 */
	public static boolean isChinese(String str) {
		String regex = "^[\u4e00-\u9fa5]+$";//其他需要，直接修改正则表达式就好
		return str.matches(regex);
	}

	/**
	 * @desc 去掉指定字符串的开头的指定字符
	 * @param str 需要进行截取的字符串
	 * @param trim 需要去掉的字符串
	 * @return 处理后的字符串
	 */
	public static String sideTrimStart(String str, String trim) {
		// null或者空字符串的时候不处理
		if(isNull(str) || isNull(trim)) return str;

		// 结束位置
		int epos = 0;

		// 正规表达式
		String regpattern = "" + trim + "*+";
		Pattern pattern = Pattern.compile(regpattern, Pattern.CASE_INSENSITIVE);

		// 去掉开头的指定字符
		Matcher matcher = pattern.matcher(str);
		if (matcher.lookingAt()) {
			epos = matcher.end();
			str = str.substring(epos);
		}
		// 返回处理后的字符串
		return str;
	}

	/**
	 * @desc 去掉指定字符串末尾的指定字符
	 * @param str 需要进行截取的字符串
	 * @param trim 需要去掉的指定字符
	 * @return
	 */
	public static String sideTrimEnd(String str, String trim) {
		// null或者空字符串的时候不处理
		if(isNull(str) || isNull(trim)){
			return str;
		}

		// 结束位置
		int epos;

		// 正规表达式
		String regpattern = "" + trim + "*+";
		Pattern pattern = Pattern.compile(regpattern, Pattern.CASE_INSENSITIVE);

		// 去掉结尾的指定字符
		StringBuffer buffer = new StringBuffer(str).reverse();
		Matcher matcher = pattern.matcher(buffer);
		if (matcher.lookingAt()) {
			epos = matcher.end();
			str = new StringBuffer(buffer.substring(epos)).reverse().toString();
		}
		// 返回处理后的字符串
		return str;
	}

	/**
	 * @desc 去掉指定字符串的开头和结尾的指定字符
	 * @param str 需要进行截取的字符串
	 * @param trim 需要去掉的字符串
	 * @return 处理后的字符串
	 */
	public static String sideTrim(String str, String trim) {
		// null或者空字符串的时候不处理
		if(isNull(str) || isNull(trim)) return str;

		// 结束位置
		int epos = 0;

		// 正规表达式
//		String regpattern = "[" + trim + "]*+";
		String regpattern = "" + trim + "*+";
		Pattern pattern = Pattern.compile(regpattern, Pattern.CASE_INSENSITIVE);

		// 去掉结尾的指定字符
		StringBuffer buffer = new StringBuffer(str).reverse();
		Matcher matcher = pattern.matcher(buffer);
		if (matcher.lookingAt()) {
			epos = matcher.end();
			str = new StringBuffer(buffer.substring(epos)).reverse().toString();
		}

		// 去掉开头的指定字符
		matcher = pattern.matcher(str);
		if (matcher.lookingAt()) {
			epos = matcher.end();
			str = str.substring(epos);
		}

		// 返回处理后的字符串
		return str;
	}

	/**
	 * [身份证号] 152****9358，前面保留3位明文，后面保留3位明文
	 *
	 * @param name
	 * @param index 3
	 * @param end 3
	 * @return
	 */
	public static String around(String name,int index,int end) {
		if (StringUtils.isBlank(name)) {
			return "";
		}
		return StringUtils.left(name, index).concat(StringUtils.removeStart(StringUtils.leftPad(StringUtils.right(name, end), StringUtils.length(name), "*"), "***"));
	}

	/**
	 *    类型+三位编号（从1开始，不够前补0）
	 * @param equipmentType 类型
	 * @param equipmentNo 编号
	 * @return 递增之后的编号
	 */
	public static String getNewEquipmentNo(String equipmentType, String equipmentNo){
		equipmentType+=DateUtils.data2StringForamt(new Date(),"yyyyMMdd");
		String newEquipmentNo = "001";
		if(equipmentNo != null && !equipmentNo.isEmpty()){
			int newEquipment = Integer.parseInt(equipmentNo) + 1;
			newEquipmentNo = String.format(equipmentType + "%03d", newEquipment);
		}
		return newEquipmentNo;
	}

	/**
	 *
	 * @param equipmentType 类型
	 * @param number 编号
	 * @return 递增编号
	 */
	public static String getEquipmentNo(String equipmentType,String number){
		if(number.indexOf(equipmentType)<0){
			return "";
		}
		String str = number.split(equipmentType)[1].substring(8);
		return str;
	}
}