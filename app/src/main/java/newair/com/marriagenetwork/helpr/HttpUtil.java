//package newair.com.marriagenetwork.helpr;
//
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.HttpStatus;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.protocol.HTTP;
//import org.apache.http.util.EntityUtils;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.UnsupportedEncodingException;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.net.URLConnection;
//import java.util.List;
//import java.util.Map;
//
//public class HttpUtil {
//
//	public static String getResponseWithGET(String url) {
//		String result = "";
//		HttpClient httpClient = new DefaultHttpClient();
//		HttpGet httpRequest = new HttpGet(url);
//
//		try {
//
//			HttpResponse httpResponse = httpClient.execute(httpRequest);
//			int statusCode = httpResponse.getStatusLine().getStatusCode();
//
//			if (HttpStatus.SC_OK == statusCode) {
//				HttpEntity entity = httpResponse.getEntity();
//				result = EntityUtils.toString(entity, HTTP.UTF_8);
//			} else {
//				result = "failed!";
//			}
//		} catch (ClientProtocolException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		return result;
//	}
//
//	/**
//	 *
//	 * @param url
//	 * @return
//	 */
//	@SuppressWarnings("null")
//	public static String getResponseWithPOST(String url,
//			List<NameValuePair> paramList) {
//		String result = "";
//
//		HttpClient httpClient = new DefaultHttpClient();
//		HttpPost httpRequest = new HttpPost(url);
//		try {
//			if (paramList != null || !paramList.equals("")) {
//
//				UrlEncodedFormEntity encodeEntity = new UrlEncodedFormEntity(
//						paramList, HTTP.UTF_8);
//
//				httpRequest.setEntity(encodeEntity);
//			}
//
//			HttpResponse httpResponse = httpClient.execute(httpRequest);
//			int statusCode = httpResponse.getStatusLine().getStatusCode();
//			if (statusCode == HttpStatus.SC_OK) {
//				HttpEntity entity = httpResponse.getEntity();
//				result = EntityUtils.toString(entity, HTTP.UTF_8);
//			}
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		} catch (ClientProtocolException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		return result;
//	}
//
//	public static boolean requestByGet() {
//		String path = "https://www.baidu.com";
//		Boolean isConnect = false;
//		HttpURLConnection urlConn = null;
//		URL url;
//		try {
//			url = new URL(path);
//			urlConn = (HttpURLConnection) url.openConnection();
//			urlConn.setConnectTimeout(5 * 1000);
//			urlConn.connect();
//			if (urlConn.getResponseCode() == 200) {
//				isConnect = true;
//			} else {
//				isConnect = false;
//			}
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		urlConn.disconnect();
//		return isConnect;
//	}
//
//	private static String textHttp() {
//		// TODO Auto-generated method stub
//		String result = null;
//		BufferedReader in = null;
//		try {
//			String url = "http://182.254.148.75/demo.php?key=dsgcapp_1qazWSX23edc4RFV5tgb6YHN7ujm8IK";
//
//			URL realUrl = new URL(url);
//			URLConnection connection = realUrl.openConnection();
//			connection.setRequestProperty("accept", "*/*");
//			connection.setRequestProperty("connection", "Keep-Alive");
//			connection
//					.setRequestProperty("user-agent",
//							"1111Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
//			connection.setRequestProperty("Content-Type", "utf-8");
//			connection.setRequestProperty("Cookie",
//					"JSESSIONID=1DD23A0D5D3CED6266312A40D2FCDE11");
//			connection.connect();
//			Map<String, List<String>> map = connection.getHeaderFields();
//			for (String key : map.keySet()) {
//				System.out.println(key + "--->" + map.get(key));
//			}
//			in = new BufferedReader(new InputStreamReader(
//					connection.getInputStream()));
//			String line;
//			while ((line = in.readLine()) != null) {
//				result += line;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		finally {
//			try {
//				if (in != null) {
//					in.close();
//				}
//			} catch (Exception e2) {
//				e2.printStackTrace();
//			}
//		}
//		return result;
//
//	}
//
//}
