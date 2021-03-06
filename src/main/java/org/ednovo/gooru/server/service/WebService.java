package org.ednovo.gooru.server.service;


import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import com.google.gwt.json.client.JSONException;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;

/**
 * This class is mainly used for invoking the web services and return the
 * corresponding response
 * 
 * @author Gooru Module : all
 * 
 */

public class WebService {
	DefaultHttpClient httpClient;
	HttpContext localContext;
	 String ret;
	public static HttpResponse response = null;
	HttpPost httpPost = null;
	HttpDelete httpDelete = null;
	HttpGet httpGet = null;
	HttpPut httpPut = null;
	String webServiceUrl;

	// The serviceName should be the name of the Service you are going to be
	// using.
	public WebService(String serviceName) {
		HttpParams myParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(myParams, 10000);
		HttpConnectionParams.setSoTimeout(myParams, 10000);
		httpClient = new DefaultHttpClient(myParams);
		localContext = new BasicHttpContext();
		webServiceUrl = serviceName;
	} // Use this method to do a HttpPost\WebInvoke on a Web Service

	public String webInvoke(String methodName, Map<String, Object> params) {
		JSONObject jsonObject = new JSONObject();
		for (Map.Entry<String, Object> param : params.entrySet()) {
			try {
				jsonObject.put(param.getKey(), (JSONValue) param.getValue());
			} catch (JSONException e) {

			}
		}
		return webInvoke(methodName, jsonObject.toString(), "application/json");
	}

	public String webInvoke(String methodName, String data, String contentType) {
		ret = null;

		httpClient.getParams().setParameter(ClientPNames.COOKIE_POLICY,
				CookiePolicy.RFC_2109);

		httpPost = new HttpPost(webServiceUrl);
		response = null;
		StringEntity tmp = null;

		httpPost.setHeader(
				"Accept",
				"text/html,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5");
		if (contentType != null) {
			httpPost.setHeader("Content-Type", contentType);

		} else {
			httpPost.setHeader("Content-Type",
					"application/x-www-form-urlencoded");
		}
		try {
			tmp = new StringEntity(data, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();

		}
		httpPost.setEntity(tmp);

		try {
			response = httpClient.execute(httpPost, localContext);

			if (response != null) {
				ret = EntityUtils.toString(response.getEntity());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ret;
	}

	
	
	public String webInvokefordelete(String methodName, String data,
			String contentType) {
		ret = null;

		httpClient.getParams().setParameter(ClientPNames.COOKIE_POLICY,
				CookiePolicy.RFC_2109);

		httpDelete = new HttpDelete(webServiceUrl);
		response = null;
		StringEntity tmp = null;

		httpDelete
				.setHeader(
						"Accept",
						"text/html,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5");
		if (contentType != null) {
			httpDelete.setHeader("Content-Type", contentType);

		} else {
			httpDelete.setHeader("Content-Type",
					"application/x-www-form-urlencoded");
		}
		try {
			tmp = new StringEntity(data, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();

		}

		try {
			response = httpClient.execute(httpDelete, localContext);

			if (response != null) {
				ret = EntityUtils.toString(response.getEntity());

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ret;
	}

	public String webInvokeforput(String methodName, String data,
			String contentType) {
		ret = null;

		httpClient.getParams().setParameter(ClientPNames.COOKIE_POLICY,
				CookiePolicy.RFC_2109);

		httpPut = new HttpPut(webServiceUrl);

		response = null;
		StringEntity tmp = null;

		httpPut.setHeader(
				"Accept",
				"text/html,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5");

		if (contentType != null) {
			httpPut.setHeader("Content-Type", contentType);

		} else {
			httpPut.setHeader("Content-Type",
					"application/x-www-form-urlencoded; charset=UTF-8");
		}

		try {
			tmp = new StringEntity(data, "UTF-8");

		} catch (UnsupportedEncodingException e) {

		}
		httpPut.setEntity(tmp);

		try {
			response = httpClient.execute(httpPut, localContext);

			if (response != null) {
				ret = EntityUtils.toString(response.getEntity());

			}
		} catch (Exception e) {

		}

		return ret;
	}

	public String webInvokeforget(String methodName, String data,
			String contentType) {
		ret = null;

		httpClient.getParams().setParameter(ClientPNames.COOKIE_POLICY,
				CookiePolicy.RFC_2109);

		httpGet = new HttpGet(webServiceUrl);

		response = null;
		StringEntity tmp = null;

		httpGet.setHeader(
				"Accept",
				"text/html,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5");

		if (contentType != null) {
			httpGet.setHeader("Content-Type", contentType);
			httpGet.setHeader("Authorization","Bearer ya29.NQAKeW06Mp3VHhsAAAB1fiCsmMLXxXFnZOTqAhslkGyyFZynaglilec77lytbw");

		} else {
			httpGet.setHeader("Content-Type",
					"application/x-www-form-urlencoded; charset=UTF-8");
		}

		try {
			tmp = new StringEntity(data, "UTF-8");
		} catch (UnsupportedEncodingException e) {

		}
		try {
			response = httpClient.execute(httpGet, localContext);

			if (response != null) {
				ret = EntityUtils.toString(response.getEntity());

			}
		} catch (Exception e) {

		}

		return ret;
	}

	public String webInvoke(String methodName, String data, String contentType,
			String appsessionid, String userid, String sid, String appid,
			String jsessionid) {
		//
		ret = null;

		httpClient.getParams().setParameter(ClientPNames.COOKIE_POLICY,
				CookiePolicy.RFC_2965);
		httpPost = new HttpPost(webServiceUrl + methodName);
		response = null;
		StringEntity tmp = null;

		BasicCookieStore cookieStore = new BasicCookieStore();

		String[] cookie_parts = null;

		httpClient.setCookieStore(cookieStore);

		localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);

		httpPost.setHeader(
				"Accept",
				"text/html,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5");
		if (contentType != null) {
			httpPost.setHeader("Content-Type", contentType);
		} else {
			httpPost.setHeader("Content-Type",
					"application/x-www-form-urlencoded");
		}
		try {
			tmp = new StringEntity(data, "UTF-8");

		} catch (UnsupportedEncodingException e) {

		}
		httpPost.setEntity(tmp);

		try {
			response = httpClient.execute(httpPost, localContext);

			if (response != null) {
				ret = EntityUtils.toString(response.getEntity());

			}
		} catch (Exception e) {

		}
		return ret;
	}

	// Use this method to do a HttpGet/WebGet on the web service
	public String webGet(String methodName, Map<String, String> params) {
		String getUrl = webServiceUrl + methodName;
		int i = 0;
		for (Map.Entry<String, String> param : params.entrySet()) {
			if (i == 0) {
				getUrl += "?";
			} else {
				getUrl += "&";
			}
			try {
				getUrl += param.getKey() + "="
						+ URLEncoder.encode(param.getValue(), "UTF-8");
			} catch (UnsupportedEncodingException e) { // TODO Auto-generated

				e.printStackTrace();
			}
			i++;
		}
		httpGet = new HttpGet(getUrl);

		try {
			response = httpClient.execute(httpGet);
		} catch (Exception e) {

		}
		// we assume that the response body contains the error message
		try {
			ret = EntityUtils.toString(response.getEntity());
		} catch (IOException e) {

		}
		return ret;
	}

	public InputStream getHttpStream(String urlString) throws IOException {
		InputStream in = null;
		int response = -1;
		URL url = new URL(urlString);
		URLConnection conn = url.openConnection();
		if (!(conn instanceof HttpURLConnection))
			throw new IOException("Not an HTTP connection");

		try {
			HttpURLConnection httpConn = (HttpURLConnection) conn;
			httpConn.setAllowUserInteraction(false);
			httpConn.setInstanceFollowRedirects(true);
			httpConn.setRequestMethod("GET");
			httpConn.connect();
			response = httpConn.getResponseCode();

			if (response == HttpURLConnection.HTTP_OK) {
				in = httpConn.getInputStream();
			}
		} catch (Exception e) {
			throw new IOException("Error connecting");
		} // end try-catch
		return in;
	}

	public void clearCookies() {
		httpClient.getCookieStore().clear();
	}

	public void abort() {
		try {
			if (httpClient != null) {

				httpPost.abort();
				response = null;
				httpPost.abort();
				httpGet.abort();

			}
		} catch (Exception e) {

		}
	}

	public void close() {

	}
}