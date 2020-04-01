package com.yinuo.common.sms;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yinuo.common.common.CommonConstants;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.message.BufferedHeader;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;

/**
 * @Title 通过HttpClient组件，进行Http请求
 */
@SuppressWarnings("deprecation")
public class HttpClientUtils {

	private static final Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);

	private static final Integer CONNECT_TIMEOUT = 30000; // 连接超时时间

	private static final Integer READ_TIMEOUT = 120000; // 读取超时时间
	public static final String APPLICATION_JSON_VALUE = "application/json";
	/**
	 * 获取HttpClient对象
	 *
	 * @param isSupportSSL 是否支持SSL true 支持 false 不支持
	 * @return
	 */
	private static CloseableHttpClient getCloseableHttpClient(boolean isSupportSSL) {
		HttpClientBuilder clientBuilder = HttpClients.custom();
		// 请求超时设置
		clientBuilder.setDefaultRequestConfig(getRequestConfig());
		if (isSupportSSL) { // 是否支持SSL
			// 创建SSL安全连接
			SSLConnectionSocketFactory sslsf = createSSLConnSocketFactory();
			if (sslsf != null) {
				clientBuilder.setSSLSocketFactory(sslsf);
			}
		}
		return clientBuilder.build();
	}

	/**
	 * 连接超时设置
	 *
	 * @return
	 */
	private static RequestConfig getRequestConfig() {
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(CONNECT_TIMEOUT) // 设置连接超时时间，单位毫秒。
				// 设置从connect Manager获取Connection 超时时间，单位毫秒。这个属性是新加的属性，因为目前版本是可以共享连接池的。
				.setConnectionRequestTimeout(1000)
				// 在提交请求之前 测试连接是否可用
				.setStaleConnectionCheckEnabled(true)
				// 请求获取数据的超时时间，单位毫秒。
				.setSocketTimeout(READ_TIMEOUT).build();
		return requestConfig;
	}

	/**
	 * 创建SSL安全连接
	 *
	 * @return SSLConnectionSocketFactory
	 */
	private static SSLConnectionSocketFactory createSSLConnSocketFactory() {
		SSLConnectionSocketFactory sslsf = null;
		try {
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
				@Override
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					return true;
				}
			}).build();
			sslsf = new SSLConnectionSocketFactory(sslContext, new X509HostnameVerifier() {
				@Override
                public boolean verify(String arg0, SSLSession arg1) { // 授信
					return true;
				}

				@Override
                public void verify(String host, SSLSocket ssl) throws IOException {
				}

				@Override
                public void verify(String host, X509Certificate cert) throws SSLException {
				}

				@Override
                public void verify(String host, String[] cns, String[] subjectAlts) throws SSLException {
				}
			});
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		}
		return sslsf;
	}

	/**
	 * 判断是否为 https请求
	 *
	 * @return boolean true 是 false 否
	 */
	public static boolean isSupportSSL(String url) {
		if (StringUtils.isBlank(url)) {
			return false;
		}
		String temp = url.toLowerCase();
		if (temp.contains("https://")) {
			return true;
		}
		return false;
	}

	/**
	 * post 请求无参数
	 *
	 * @param url 请求url
	 * @return
	 */
	public static String post(String url) {
		return post(url, null);
	}

	/**
	 * post 请求数据格式application/x-www-form-urlencoded
	 *
	 * @param url       请求url
	 * @param paramsMap 请求参数
	 * @return
	 */
	public static String post(String url, Map<String, String> paramsMap) {
		boolean supportSSL = isSupportSSL(url);
		CloseableHttpClient client = getCloseableHttpClient(supportSSL);
		String result = "";
		try {
			HttpPost httpPost = new HttpPost(url);
			if (MapUtils.isNotEmpty(paramsMap)) {
				List<BasicNameValuePair> params = new ArrayList<>();
				for (Iterator<String> it = paramsMap.keySet().iterator(); it.hasNext();) {
					String name = (String) it.next();
					params.add(new BasicNameValuePair(name, paramsMap.get(name)));
				}
				httpPost.setEntity(new UrlEncodedFormEntity(params, CommonConstants.DEFAULT_CHARACTER));
			}
			CloseableHttpResponse response = client.execute(httpPost);
			// response.getAllHeaders();
			HeaderIterator iterator = response.headerIterator();
			while (iterator.hasNext()) {
				System.out.println("\t" + iterator.next());
			}

			if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
				HttpEntity entity = response.getEntity();
				result = EntityUtils.toString(entity);
			}
		} catch (ClientProtocolException e) {
			logger.error("请求url = {} 协议错误", url, e);
			result = errorResponseToJSON("-1", "请求协议错误" + e.getMessage());
		} catch (IOException e) {
			logger.error("url = {}接口访问异常！", url, e);
			result = errorResponseToJSON("-1", "接口访问异常" + e.getMessage());
		} finally {
			try {
				client.close();
			} catch (IOException e) {
				logger.error("url = {} 接口访问关闭连接异常！", url, e);
			}
		}
		return result;
	}

	/**
	 * 
	 * 
	 * @param url
	 * @param paramsMap
	 * @return
	 */
	public static JSONObject postAndReturnCookies(String url, Map<String, String> paramsMap) {
		boolean supportSSL = isSupportSSL(url);
		CloseableHttpClient client = getCloseableHttpClient(supportSSL);
		JSONObject result = null;
		try {
			HttpPost httpPost = new HttpPost(url);
			if (MapUtils.isNotEmpty(paramsMap)) {
				List<BasicNameValuePair> params = new ArrayList<>();
				for (Iterator<String> it = paramsMap.keySet().iterator(); it.hasNext();) {
					String name = (String) it.next();
					params.add(new BasicNameValuePair(name, paramsMap.get(name)));
				}
				httpPost.setEntity(new UrlEncodedFormEntity(params, CommonConstants.DEFAULT_CHARACTER));
			}
			CloseableHttpResponse response = client.execute(httpPost);
			HeaderIterator iterator = response.headerIterator();
			BufferedHeader bufferedHeader = null;
			String Cookie_Set = null;
			while (iterator.hasNext()) {
				bufferedHeader = (BufferedHeader) iterator.next();
				if (bufferedHeader.getName().equals("Set-Cookie")) {
					Cookie_Set = bufferedHeader.getValue();
					break;
				}
			}

			if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
				HttpEntity entity = response.getEntity();
				if (logger.isDebugEnabled()) {
					Header header = entity.getContentType();
					logger.debug("响应类型 name = {}  value = {}", header.getName(), header.getValue());
				}
				result = JSON.parseObject(EntityUtils.toString(entity));
			}
			// set cookies
			result.put("Set-Cookie", Cookie_Set);
		} catch (ClientProtocolException e) {
			logger.error("请求url = {} 协议错误", url, e);
			result = errorResponseToJSONObject("-1", "请求协议错误" + e.getMessage());
		} catch (IOException e) {
			logger.error("url = {}接口访问异常！", url, e);
			result = errorResponseToJSONObject("-1", "接口访问异常" + e.getMessage());
		} catch (Exception e) {
			logger.error("url = {}接口访问异常2！", url, e);
			result = errorResponseToJSONObject("-1", "接口访问异常" + e.getMessage());
		} finally {
			try {
				client.close();
			} catch (IOException e) {
				logger.error("url = {} 接口访问关闭连接异常！", url, e);
			}
		}
		return result;
	}

	/**
	 * get请求
	 *
	 * @param url
	 * @return
	 */
	public static String get(String url) {
		return get(url, null);
	}

	/**
	 * get请求
	 *
	 * @param url
	 * @param params 请求参数
	 * @return
	 */
	public static String get(String url, Map<String, String> params) {
		boolean supportSSL = isSupportSSL(url);
		CloseableHttpClient client = getCloseableHttpClient(supportSSL);
		if (MapUtils.isNotEmpty(params)) {
			StringBuffer param = new StringBuffer();
			param.append(url.contains("?") ? "" : "?");
			int i = 0;
			for (String key : params.keySet()) { // 请求参数拼接
				if (i > 0) {
					param.append("&");
				}
				param.append(key).append("=").append(params.get(key));
				i++;
			}
			url = url + param.toString();
		}
		String result = "";
		try {
			HttpGet httpget = new HttpGet(url);
			CloseableHttpResponse response = client.execute(httpget);
			if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
				HttpEntity entity = response.getEntity();
				result = EntityUtils.toString(entity);
			}
		} catch (ClientProtocolException e) {
			logger.error("请求url = {} 协议错误", url, e);
			result = errorResponseToJSON("-1", "请求协议错误" + e.getMessage());
		} catch (IOException e) {
			logger.error("url = {}接口访问异常！", url, e);
			result = errorResponseToJSON("-1", "接口访问异常" + e.getMessage());
		} finally {
			try {
				client.close();
			} catch (IOException e) {
				logger.error("url = {} 接口访问关闭连接异常！", url, e);
			}
		}
		return result;
	}

	/**
	 * 发送json数据
	 *
	 * @param url
	 * @param json
	 * @return 返回json
	 */
	public static JSONObject postToJSON(String url, JSONObject json) {
		return postToJSON(url, null, json);
	}

	/**
	 * 发送post请求(不带参数)
	 * 
	 * @param url 请求url
	 * @return 返回json
	 */
	public static JSONObject postToJSON(String url) {
		boolean supportSSL = isSupportSSL(url);
		CloseableHttpClient client = getCloseableHttpClient(supportSSL);
		HttpPost post = new HttpPost(url);
		JSONObject response = null;
		try {
			HttpResponse res = client.execute(post);
			if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = res.getEntity();
				response = JSONObject.parseObject(EntityUtils.toString(entity));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return response;
	}

	/**
	 * 发送json数据
	 *
	 * @param url
	 * @param json
	 * @return 返回json
	 */
	public static JSONObject postToJSON(String url, String contentType, JSONObject json) {
		boolean supportSSL = isSupportSSL(url);
		CloseableHttpClient client = getCloseableHttpClient(supportSSL);
		HttpPost post = new HttpPost(url);
		if (StringUtils.isNotBlank(contentType)) { // 设置请求头内容格式
			post.setHeader("Content-Type", contentType);
		}
		JSONObject response = null;
		try {
			StringEntity s = new StringEntity(json.toString(), "UTF-8");
			s.setContentEncoding(CommonConstants.DEFAULT_CHARACTER);
			s.setContentType(APPLICATION_JSON_VALUE);
			post.setEntity(s);

			HttpResponse res = client.execute(post);
			if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = res.getEntity();
				response = JSONObject.parseObject(EntityUtils.toString(entity));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return response;
	}

	public static String postGeneralUrl(String generalUrl, String contentType, String params, String encoding)
			throws Exception {
		URL url = new URL(generalUrl);
		// 打开和URL之间的连接
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		// 设置通用的请求属性
		connection.setRequestProperty("Content-Type", contentType);
		connection.setRequestProperty("Connection", "Keep-Alive");
		connection.setUseCaches(false);
		connection.setDoOutput(true);
		connection.setDoInput(true);

		// 得到请求的输出流对象
		DataOutputStream out = new DataOutputStream(connection.getOutputStream());
		out.write(params.getBytes(encoding));
		out.flush();
		out.close();

		// 建立实际的连接
		connection.connect();
		// 获取所有响应头字段
		Map<String, List<String>> headers = connection.getHeaderFields();
		// 遍历所有的响应头字段
		for (String key : headers.keySet()) {
			System.err.println(key + "--->" + headers.get(key));
		}
		// 定义 BufferedReader输入流来读取URL的响应
		BufferedReader in = null;
		in = new BufferedReader(new InputStreamReader(connection.getInputStream(), encoding));
		String result = "";
		String getLine;
		while ((getLine = in.readLine()) != null) {
			result += getLine;
		}
		in.close();
		System.err.println("result:" + result);
		return result;
	}

	/**
	 * 发送加密后的json数据
	 *
	 * @param url
	 * @param json
	 * @return 返回json
	 */
	public static String postToJSONWithEncrypt(String url, String json) {
		boolean supportSSL = isSupportSSL(url);
		CloseableHttpClient client = getCloseableHttpClient(supportSSL);
		HttpPost post = new HttpPost(url);
		String response = null;
		try {
			StringEntity s = new StringEntity(json, "UTF-8");
			s.setContentEncoding(CommonConstants.DEFAULT_CHARACTER);
			s.setContentType(APPLICATION_JSON_VALUE);
			post.setEntity(s);

			HttpResponse res = client.execute(post);
			if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = res.getEntity();
				response = EntityUtils.toString(entity);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return response;
	}

	/**
	 * 返回错误信息
	 *
	 * @param errorCode
	 * @param errorMsg
	 * @return
	 */
	public static String errorResponseToJSON(String errorCode, String errorMsg) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("errorCode", errorCode);
		jsonObject.put("errorMsg", errorMsg);
		return jsonObject.toJSONString();
	}

	public static JSONObject errorResponseToJSONObject(String errorCode, String errorMsg) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("errorCode", errorCode);
		jsonObject.put("errorMsg", errorMsg);
		return jsonObject;
	}

	public static String post(String requestUrl, String accessToken, String params) throws Exception {
		String contentType = "application/x-www-form-urlencoded";
		return HttpClientUtils.post(requestUrl, accessToken, contentType, params);
	}

	public static String post(String requestUrl, String accessToken, String contentType, String params)
			throws Exception {
		String encoding = "UTF-8";
		if (requestUrl.contains("nlp")) {
			encoding = "GBK";
		}
		return HttpClientUtils.post(requestUrl, accessToken, contentType, params, encoding);
	}

	public static String post(String requestUrl, String accessToken, String contentType, String params, String encoding)
			throws Exception {
		String url = requestUrl + "?access_token=" + accessToken;
		return HttpClientUtils.postGeneralUrl(url, contentType, params, encoding);
	}

	public static void main(String[] args) {
//        String str = "{\"certNo\":\"440822197610220277\",\"data\":{\"data\":{\"hFundDetailInfo\":{\"cityCode\":\"0004\",\"cityCodeDesc\":\"湛江\",\"currency\":\"CNY\",\"hFundDepositDetails\":[{\"amount\":1726.0,\"balance\":3462.74,\"date\":1511366400000,\"memo\":\"缴存\"},{\"amount\":1726.0,\"balance\":1736.74,\"date\":1508428800000,\"memo\":\"缴存\"},{\"amount\":1726.0,\"balance\":5210.74,\"date\":1505836800000,\"memo\":\"缴存\"},{\"amount\":1726.0,\"balance\":3484.74,\"date\":1503244800000,\"memo\":\"缴存\"},{\"amount\":1726.0,\"balance\":1758.74,\"date\":1500912000000,\"memo\":\"缴存\"},{\"amount\":75.87,\"balance\":6632.74,\"date\":1498838400000,\"memo\":\"结息\"},{\"amount\":1624.0,\"balance\":6556.87,\"date\":1497888000000,\"memo\":\"缴存\"},{\"amount\":1624.0,\"balance\":4932.87,\"date\":1494864000000,\"memo\":\"缴存\"},{\"amount\":1624.0,\"balance\":3308.87,\"date\":1492358400000,\"memo\":\"缴存\"},{\"amount\":1624.0,\"balance\":1684.87,\"date\":1489593600000,\"memo\":\"缴存\"},{\"amount\":1624.0,\"balance\":6860.87,\"date\":1487260800000,\"memo\":\"缴存\"},{\"amount\":1624.0,\"balance\":5236.87,\"date\":1484668800000,\"memo\":\"缴存\"},{\"amount\":650.0,\"balance\":55265.42,\"date\":1293465600000,\"memo\":\"缴存\"},{\"amount\":650.0,\"balance\":54615.42,\"date\":1291219200000,\"memo\":\"缴存\"},{\"amount\":650.0,\"balance\":53965.42,\"date\":1288195200000,\"memo\":\"缴存\"},{\"amount\":650.0,\"balance\":53315.42,\"date\":1284998400000,\"memo\":\"缴存\"},{\"amount\":650.0,\"balance\":52665.42,\"date\":1282579200000,\"memo\":\"缴存\"},{\"amount\":650.0,\"balance\":52015.42,\"date\":1280332800000,\"memo\":\"缴存\"},{\"amount\":698.81,\"balance\":51365.42,\"date\":1277913600000,\"memo\":\"结息\"},{\"amount\":899.0,\"balance\":50666.61,\"date\":1276790400000,\"memo\":\"缴存\"},{\"amount\":899.0,\"balance\":49767.61,\"date\":1274371200000,\"memo\":\"缴存\"},{\"amount\":899.0,\"balance\":48868.61,\"date\":1271692800000,\"memo\":\"缴存\"},{\"amount\":899.0,\"balance\":47969.61,\"date\":1268928000000,\"memo\":\"缴存\"},{\"amount\":899.0,\"balance\":47070.61,\"date\":1266768000000,\"memo\":\"缴存\"},{\"amount\":899.0,\"balance\":46171.61,\"date\":1264521600000,\"memo\":\"缴存\"},{\"amount\":899.0,\"balance\":45272.61,\"date\":1262102400000,\"memo\":\"缴存\"},{\"amount\":899.0,\"balance\":44373.61,\"date\":1259251200000,\"memo\":\"缴存\"},{\"amount\":899.0,\"balance\":43474.61,\"date\":1256486400000,\"memo\":\"缴存\"},{\"amount\":899.0,\"balance\":42575.61,\"date\":1253721600000,\"memo\":\"缴存\"},{\"amount\":899.0,\"balance\":41676.61,\"date\":1251043200000,\"memo\":\"缴存\"},{\"amount\":899.0,\"balance\":40777.61,\"date\":1249228800000,\"memo\":\"缴存\"},{\"amount\":824.0,\"balance\":39878.61,\"date\":1246809600000,\"memo\":\"缴存\"},{\"amount\":527.63,\"balance\":39054.61,\"date\":1246377600000,\"memo\":\"结息\"},{\"amount\":824.0,\"balance\":38526.98,\"date\":1243699200000,\"memo\":\"缴存\"},{\"amount\":824.0,\"balance\":37702.98,\"date\":1240848000000,\"memo\":\"缴存\"},{\"amount\":824.0,\"balance\":36878.98,\"date\":1237824000000,\"memo\":\"缴存\"},{\"amount\":3296.0,\"balance\":36054.98,\"date\":1235923200000,\"memo\":\"缴存\"},{\"amount\":29462.98,\"balance\":32758.98,\"date\":1235923200000,\"memo\":\"缴存\"},{\"amount\":824.0,\"balance\":3296.0,\"date\":1235664000000,\"memo\":\"缴存\"},{\"amount\":824.0,\"balance\":2472.0,\"date\":1234195200000,\"memo\":\"缴存\"},{\"amount\":824.0,\"balance\":1648.0,\"date\":1230220800000,\"memo\":\"缴存\"},{\"amount\":824.0,\"balance\":824.0,\"date\":1227628800000,\"memo\":\"缴存\"}],\"hFundLoanInfos\":[],\"hFundPersonInfo\":{\"address\":\"\",\"balance\":3462.74,\"base\":8627.0,\"birthday\":\"1976-10\",\"certNo\":\"440822197610220277\",\"certType\":\"居民身份证\",\"companyAccount\":\"04400866\",\"companyAmount\":863.0,\"companyName\":\"中国电信股份有限公司廉江分公司\",\"companyPercent\":\"10%\",\"fiscalAmount\":0.0,\"fiscalPercent\":\"0%\",\"hfundNo\":\"0341048647\",\"lastDate\":1509465600000,\"moblieNo\":\"\",\"monthPayment\":1726.0,\"name\":\"钟日峻\",\"openDate\":631123200000,\"personalAmount\":863.0,\"personalPercent\":\"10%\",\"sex\":\"男\",\"status\":\"正常\",\"telephoneNo\":\"\"},\"hFundSupplementInfo\":{},\"storeTime\":1511765586255,\"withdraws\":[{\"amount\":5200.0,\"balance\":10.74,\"date\":1507564800000,\"reason\":\"支取\"},{\"amount\":6600.0,\"balance\":32.74,\"date\":1499097600000,\"reason\":\"支取\"},{\"amount\":6800.0,\"balance\":60.87,\"date\":1488211200000,\"reason\":\"支取\"}]}},\"resultCode\":\"CCOM1000\",\"resultDesc\":\"成功\"},\"dataType\":\"hfund\",\"mobile\":\"13377597685\",\"msgCode\":\"CCOM3074\",\"msgDesc\":\"成功\",\"name\":\"钟日峻\"}";
//        String str1 = "{\"certNo\":\"430381198912104138\",\"data\":{\"data\":{\"rhzxDetailInfo\":{\"rhzxLoanRecoredDetail\":{\"creditCardDetail\":{\"normalDetails\":[\"2017年10月17日华融湘江银行发放的贷记卡（人民币账户）。截至2017年11月，信用额度50,000，已使用额度0。\",\"2017年6月23日中国农业银行发放的贷记卡（人民币账户）。截至2017年11月，信用额度5,000，已使用额度63。\",\"2017年6月12日中国建设银行湖南省分行发放的贷记卡（人民币账户）。截至2017年11月，信用额度5,000，已使用额度0。\",\"2017年3月23日中国银行湖南省分行发放的贷记卡（人民币账户）。截至2017年11月，信用额度3,000，已使用额度0。\",\"2017年4月24日中国建设银行湖南省分行发放的贷记卡（人民币账户），截至2017年8月已销户。\"],\"overdueDetails\":[]},\"houseLoanDetail\":{\"normalDetails\":[\"2017年9月28日湖南湘乡农村商业银行股份有限公司发放的470,000元（人民币）个人商用房（包括商住两用）贷款，2037年9月28日到期。截至2017年10月，余额468,042。\"],\"overdueDetails\":[]},\"otherLoanDetail\":{\"normalDetails\":[\"2017年3月30日华融湘江银行股份有限公司湘潭分行发放的18,000元（人民币）个人经营性贷款，2018年3月29日到期。截至2017年10月，余额18,000。\"],\"overdueDetails\":[\"2012年6月12日奇瑞徽银汽车金融有限公司发放的42,240元（人民币）个人汽车贷款，2015年6月已结清。最近5年内有24个月处于逾期状态，没有发生过90天以上逾期。\"]}},\"rhzxLoanSum\":{\"creditCardSum\":{\"assureCountNo\":0,\"moreThan90CountNo\":0,\"overdueCountNo\":0,\"sumCountNo\":5,\"unclearedCountNo\":4},\"houseLoanSum\":{\"assureCountNo\":0,\"moreThan90CountNo\":0,\"overdueCountNo\":0,\"sumCountNo\":1,\"unclearedCountNo\":1},\"otherLoanSum\":{\"assureCountNo\":0,\"moreThan90CountNo\":0,\"overdueCountNo\":1,\"sumCountNo\":2,\"unclearedCountNo\":1}},\"rhzxPersonInfo\":{\"credentialsNo\":\"**************4138\",\"credentialsType\":\"身份证\",\"marryStatu\":\"已婚\",\"name\":\"许鑫\",\"referTime\":1513061121000,\"reportNo\":\"2017121300004841808656\",\"reportTime\":1513102648000},\"rhzxReferRecords\":{\"rhzxReferRecordsOrg\":[{\"number\":1,\"operator\":\"重庆市阿里巴巴小额贷款有限公司/a*i-*izao\",\"referDate\":1511107200000,\"referReason\":\"贷款审批\"},{\"number\":2,\"operator\":\"平安银行信用卡中心/S*XT\",\"referDate\":1509292800000,\"referReason\":\"信用卡审批\"},{\"number\":3,\"operator\":\"华融湘江银行/zhangjingYHKB2*151*16\",\"referDate\":1508169600000,\"referReason\":\"信用卡审批\"},{\"number\":4,\"operator\":\"中国银行/2*002_003*9_4900650\",\"referDate\":1506960000000,\"referReason\":\"贷后管理\"},{\"number\":5,\"operator\":\"招商银行/*MBUSER*03\",\"referDate\":1501776000000,\"referReason\":\"信用卡审批\"},{\"number\":6,\"operator\":\"中国建设银行湖南省分行/*92313*6\",\"referDate\":1497196800000,\"referReason\":\"信用卡审批\"},{\"number\":7,\"operator\":\"中国农业银行/*y*temer\",\"referDate\":1496851200000,\"referReason\":\"信用卡审批\"},{\"number\":8,\"operator\":\"华融湘江银行股份有限公司湘潭分行/yanghongyuXTFH20*704*4\",\"referDate\":1495382400000,\"referReason\":\"贷后管理\"},{\"number\":9,\"operator\":\"浦发银行信用卡中心/*app*id\",\"referDate\":1494777600000,\"referReason\":\"信用卡审批\"},{\"number\":10,\"operator\":\"中国建设银行湖南省分行/*923*306\",\"referDate\":1492358400000,\"referReason\":\"信用卡审批\"},{\"number\":11,\"operator\":\"华融湘江银行股份有限公司湘潭分行/yangyueco*gXTfh2*160110\",\"referDate\":1490803200000,\"referReason\":\"贷后管理\"},{\"number\":12,\"operator\":\"中国银行湖南省分行/20002_1265*_591*260\",\"referDate\":1489766400000,\"referReason\":\"信用卡审批\"}],\"rhzxReferRecordsPer\":[{\"number\":1,\"operator\":\"中国人民银行湘乡市支行/PBC430381_*se*004\",\"referDate\":1503849600000,\"referReason\":\"本人查询（临柜）\"},{\"number\":2,\"operator\":\"中国人民银行湘乡市支行/PBC430381_u*er*04\",\"referDate\":1494259200000,\"referReason\":\"本人查询（临柜）\"},{\"number\":3,\"operator\":\"本人\",\"referDate\":1452441600000,\"referReason\":\"本人查询（互联网个人信用信息服务平台）\"}]}}}},\"dataType\":\"rhzx\",\"mobile\":\"13627322846\",\"msgCode\":\"CCOM3074\",\"msgDesc\":\"成功\",\"name\":\"许鑫\"}";
//        //postToJSON("http://192.168.52.105:8083/yn/service/bqsCallbackTest",JSON.parseObject(str1));
//
//        String str2 = "{\n" +
//                "    \"header\": {\n" +
//                "        \"orgName\": \"appLocal\",\n" +
//                "        \"serviceCode\": \"search_account_xuexin\"\n" +
//                "    },\n" +
//                "    \"body\": \"iC7Ph7t9QcaIoplZ2LbbYFW5YG1XYY9GQMQysRtfpuJDypdt+s6tPoorFY6d4eXIGaAAuBYcEM0QMs3SgvzFgcPlKnBF5hzL4NI8gqcVZDHp309lnbi8MaN4xpYNGnGkuqcdopywP7pAAsNjZf5MwEXSMaC0bNXAw2TyS5Yxwo/s29m5G5mhwW59P4DszOFKeQ2LeYBij0vQvmyv0BGujB9Jmd/6QC+DkpI5g2O4HBca/+cwxDuK3MJWja4IYJziRO9R4AgymYkIDjPfOsYih4yrpQ9ZbRhkutH/F3iuE1RfuM77k96gAfYZNHTWbyD0s0/lR9BiWMctMQnAON22Dm8Ksvtl9Uhb8Kl7hWi2RaCcfeYa1VEiuvlDW5crStzSU4IXilyKZroEa9n5rwh+WqOAS17XXaBjjMZd0ixAgqH28JZQURZ8pwRQ9SMhc0SngowHq3q+HZQ9IqraSqGFb5LGc4y+oJ/w7y/yehg7tqrH1FeaIOQk2BH3Pd1UT4qGojiC6iQcixNnekHbHesIwZu9XhGT2jHqTXxGdtTeT7nPsQA4EhgJG3tP6n52Pxi1QcjNTG77kl6MoSTSJVgjsXNIzrGsN3qTFpYbdCiKkXW0B1XlXDhGv5KHRKOkyCPPnO4yGlGIeChqwYeQJyxk8K+mxrZXM1tSAEr/ysOOFfdprJEsd3ZmrjChSJ82vhn0cXVTroHKu7TkFr3+CyvcVTgt+f0IUpe+nP2lI9pQtQ2SbIxvTxtv4oJkoWXt6pHGEg5+c0QwQkt1zr1T8unI53Dh3YQUo/oZ1E8b0uJbRhX3IRT8MoApcz9W5zKnHMqEFFQ+cW7RTjRJyhwyol04FVZjJ4wTO3CBNXktqZ7lDwXqaeSmx2PK7nHh7OKWUAJ/Rw9ZJL7ixZaluV3vKJZ050SxSrxuIid5UXd49zx5BNX6IseqYS8/Z0I1wavdF+6ReKE5QmRX5gZnL0KM7abyJv2q9xYfJN1Xax8UIaJ+X8cADZakAb2KqD07/EZXfQZk\"\n" +
//                "}\n";
//        postToJSON("http://192.168.52.105:8083/yn/doCredit/chsi",JSON.parseObject(str2));
	}

	/**
	 * 附带头部信息
	 * 
	 * @param url
	 * @param json
	 * @param headers
	 * @return
	 */
	public static JSONObject postToJSONWithHeaders(String url, JSONObject json, Map<String, String> headers) {
		boolean supportSSL = isSupportSSL(url);
		CloseableHttpClient client = getCloseableHttpClient(supportSSL);
		HttpPost post = new HttpPost(url);
		if (headers != null) {
			for (Map.Entry<String, String> entry : headers.entrySet()) {
				post.addHeader(entry.getKey(), entry.getValue());
			}
		}
		JSONObject response = null;
		try {
			StringEntity s = new StringEntity(json.toString(), "UTF-8");
			s.setContentEncoding(CommonConstants.DEFAULT_CHARACTER);
			s.setContentType(APPLICATION_JSON_VALUE);
			post.setEntity(s);

			HttpResponse res = client.execute(post);
			if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = res.getEntity();
				response = JSONObject.parseObject(EntityUtils.toString(entity));
				Header[] allHeaders = res.getAllHeaders();
				if (allHeaders != null && allHeaders.length > 0) {
					Map<String, String> tempHeaders = new HashMap<>();
					for (Header header : allHeaders) {
						tempHeaders.put(header.getName(), header.getValue());
					}
					response.put("allHeaders", tempHeaders);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return response;
	}
}