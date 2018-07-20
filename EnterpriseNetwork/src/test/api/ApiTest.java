package api;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.junit.Test;

/**
 * @author wyhong
 * @date 2018-5-30
 */
public class ApiTest {

	@Test
	public void testGet(){
		Connection connection = Jsoup.connect("http://localhost:8888/EnterpriseNetwork/enterprise/");
		try {
			Response response = connection.ignoreContentType(true).execute();
			System.out.println(response.body());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void testPost(){
		Connection connection = Jsoup.connect("http://localhost:8888/EnterpriseNetwork/enterprise/");
		Map<String, String> params = new HashMap<String, String>();
		params.put("name", "jsoup公司");
		params.put("address", "USA");
		params.put("description", "pachong");
		try {
			Response response = connection.data(params).method(Method.POST).ignoreContentType(true).execute();
			System.out.println(response.body());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
