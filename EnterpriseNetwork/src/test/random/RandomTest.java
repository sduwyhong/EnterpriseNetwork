package random;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;


import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.CharEncoding;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.enterpriseNetwork.model.Enterprise;
import org.junit.Test;
import org.springframework.util.Assert;
import org.springframework.util.DigestUtils;
import org.springframework.util.MimeTypeUtils;
import org.springframework.util.PatternMatchUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.util.SystemPropertyUtils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.util.TypeUtils;


/**
 * @author wyhong
 * @date 2018-5-1
 */
public class RandomTest {
	
	public void StringUtilsTest(){
		String str = "abcd efghi";
//		String bad_words = "操你妈的大傻逼！";
		
		//缩写：abbreviate(目标字符串，缩写后长度（>=4）)==>用于文章内容或保密内容
//		str = StringUtils.abbreviate(str, 4);
//		str = StringUtils.abbreviateMiddle(str, "...", 6);
		
		//首字母大写/小写==》框架中常用于通过类名设置默认bean名
//		str = StringUtils.capitalize(str);
//		String bar = "EnterpriseDao";
//		System.out.println(StringUtils.uncapitalize(bar));
		
		//判空==》太多了，判断输入是否含有空格
		//null、""、" "、"\n\t"都是true
//		System.out.println(StringUtils.isBlank(null));
//		System.out.println("abc v".contains(" "));
		//""才为true
//		StringUtils.isEmpty(foo);
		
		//删除空格
//		str = StringUtils.deleteWhitespace(str);
		
		//过滤替换
//		bad_words = StringUtils.replaceEach(bad_words, new String[]{"操你妈","傻逼"}, new String[]{"xxx","xxx"});
//		System.out.println(bad_words);
		
		//字符串不存在则用默认
//		System.out.println(StringUtils.defaultString(null, "default"));
		
		//不存在则添加到末尾
		String sql = "INSERT INTO enterprise VALUES(?,?,?,";
//		System.out.println(StringUtils.appendIfMissing(sql, ")", ")"));
//		System.out.println(StringUtils.lastIndexOf(sql, ","));
//		System.out.println(sql.length());
		
		//拼接
//		System.out.println(StringUtils.join("a", "b", "c"));
//		System.out.println(StringUtils.join(new String[]{"a", "b", "c"}, "-"));
		
		//特殊截取
//		System.out.println(StringUtils.substringBeforeLast(sql, ","));
		
		//打印str2中有的而str1中没有的
//		System.out.println(StringUtils.difference("abcde", "abxyz"));
	}
	
	public void FileUtilsTest(){
		//aspectj==>FileUtil
		//apache==>FileUtils
	}
	
	@Test
	public void ClassUtilsTest() throws FileNotFoundException{
		String base = "org.enterpriseNetwork.model";
//		String path = "";
//		System.out.println(path = ClassUtils.convertClassNameToResourcePath(base));
		//根据basePackage获取包下所有class
		File file = ResourceUtils.getFile("classpath:"+base.replace(".", File.separator));
		System.out.println(file.getAbsolutePath());
//		System.out.println(file = new File(StringUtils.substringAfter(RandomTest.class.getClassLoader().getResource(path).toString(), "/")));
		File[] files = file.listFiles();
		for (File _file : files) {
			//className
			System.out.println(base + "." + StringUtils.substringBefore(_file.getName(), "."));
		}
	}
	
	public void SpringRandomUtilsTest(){
			//加密工具类
			System.out.println(DigestUtils.md5DigestAsHex("123456你好".getBytes()));
			
			//File工具类==》spring的FileCopyUtils够用了
//			try {
//				System.out.println(FileCopyUtils.copy(new File("e:\\503.docx"), new File("e:\\Git\\copy_503.docx")));
//				//dir handler
////				FileSystemUtils.copyRecursively(src, dest);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
			
			//produces的值从这取常量，不容易错！！
			System.out.println(MimeTypeUtils.APPLICATION_JSON_VALUE);
			System.out.println(MimeTypeUtils.APPLICATION_OCTET_STREAM_VALUE);
			
			//"*"匹配，牛皮
			System.out.println(PatternMatchUtils.simpleMatch("delete*prise*","deleteEnterpriseInfo"));
			
			//反射工具类，可根据字段名获取field对象
			Field field = ReflectionUtils.findField(Enterprise.class, "name");
			Enterprise en = null;
			field.setAccessible(true);
			try {
				field.set(en = new Enterprise(), "wangyi");
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			System.out.println(en.getName());
			
			try {
				//用这个获取classPath资源
				File file = null;
				System.out.println(file = ResourceUtils.getFile("classpath:spring/application.xml"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			//SocketUtils用来寻找有效的TCP/UDP端口号
//			SocketUtils.findAvailableTcpPort();
			
			//spring的StringUtils可以删除头尾空格，根据路径获取文件名等
//			StringUtils.countOccurrencesOf(str, sub);
//			StringUtils.capitalize(str);
//			StringUtils.uncapitalize(str);
//			StringUtils.getFilename(path);
//			StringUtils.trimAllWhitespace(str);
//			StringUtils.trimLeadingWhitespace(str);
//			StringUtils.trimTrailingWhitespace(str);
			
			//TypeUtils为类型转换提供了方便的封装！
//			TypeUtils.castToInt(value);
			
			
	}
	
	public void lang3ArrayUtilsTest(){
		
		ArrayUtils.add(array, element);
		ArrayUtils.add(array, index, element);
		
		ArrayUtils.clone(array);
		
		ArrayUtils.contains(array, objectToFind);
		ArrayUtils.indexOf(array, objectToFind);
		
		//return ((array == null) || (array.length == 0));
		ArrayUtils.isEmpty(array);
		ArrayUtils.isNotEmpty(array);
		
		ArrayUtils.isSorted(array, comparator);
		
		ArrayUtils.remove(array, index);
		ArrayUtils.removeElement(array, element);
		
		ArrayUtils.reverse(array);
		ArrayUtils.subarray(array, startIndexInclusive, endIndexExclusive);
		
		ArrayUtils.toObject(array);
		ArrayUtils.toPrimitive(array);
		
		ArrayUtils.toArray(items);
		ArrayUtils.toMap(array);
	}
	
	public void lang3CharTest(){
		//charset编码以后从这取！
		System.out.println(CharEncoding.UTF_8);
		System.out.println(CharEncoding.ISO_8859_1);
		
		//获取Unicode值、判断字符是否是数字、（大小写）字母
		CharUtils.toIntValue(ch);
		CharUtils.isAsciiAlpha(ch);
		CharUtils.isAsciiAlphaLower(ch);
		CharUtils.isAsciiAlphaUpper(ch);
		CharUtils.isAsciiNumeric(ch);
		
	}
	
	public void langValidateSpringAssert(){
		//断言
		Validate.notBlank(chars);
		Assert.notNull(object);
	}
	
	
	public void langRandomStringUtils(){
		//字母
		System.out.println(RandomStringUtils.randomAlphabetic(4));
		//字母+数字（32位仿佛UUID）
		System.out.println(RandomStringUtils.randomAlphanumeric(32));
		//数字
		System.out.println(RandomStringUtils.randomNumeric(4));
		//随机unicode
		System.out.println(RandomStringUtils.random(4));
		//随机ascii
		System.out.println(RandomStringUtils.randomAscii(4));
		
		
		System.out.println(RandomUtils.nextInt(0, 100));
		System.out.println(RandomUtils.nextFloat(0, 100));
	
	}
	
	public void lang3ClassUtils(){
		//the same
		System.out.println(ClassUtils.getShortClassName(Enterprise.class));
		System.out.println(ClassUtils.getShortCanonicalName(Enterprise.class));
		System.out.println(ClassUtils.getSimpleName(Enterprise.class));
	}
	
	public void lang3Conversion(){
		//二进制、字节、各类型数字、十六进制之间的转换
	}
	public void StringEscape(){
		//escapeSth:将Sth当作字符串处理
		//unescapeSth:将字符串以Sth的方式去解析
		System.out.println(JSONObject.toJSONString(new Enterprise()));
		System.out.println(StringEscapeUtils.escapeJson(JSONObject.toJSONString(new Enterprise())));
		System.out.println(StringEscapeUtils.unescapeJson(JSONObject.toJSONString(new Enterprise())));
		
		System.out.println(StringEscapeUtils.escapeJava("\""));
		System.out.println(StringEscapeUtils.unescapeJava("\""));
	}
	
	@Test
	public void SystemUtils(){
		//项目根路径：E:\Git\myeclipseRepository\EnterpriseNetwork\EnterpriseNetwork
		System.out.println(org.apache.commons.lang3.SystemUtils.getUserDir());
		//当前编译类根目录：/E:/Git/myeclipseRepository/EnterpriseNetwork/EnterpriseNetwork/target/test-classes/
		File file = null;
		System.out.println(file = new File(RandomTest.class.getClassLoader().getResource("").getPath()));
		System.out.println(file.exists());
	}
}
