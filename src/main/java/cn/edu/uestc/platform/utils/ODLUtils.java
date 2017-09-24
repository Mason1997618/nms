package cn.edu.uestc.platform.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class ODLUtils {

	public static void main(String[] args) {
		String str = "{\"flow\": [{\"id\": \"5\",\"match\": {\"in-port\": \"5\"},\"instructions\": {\"instruction\": [{\"order\": \"0\",\"go-to-table\": {\"table_id\": \"80\"}}]},\"barrier\": \"false\",\"priority\": \"60000\",\"idle-timeout\": \"0\",\"hard-timeout\": \"0\",\"table_id\": \"60\"}]}";
		String b = null;
		try {
			b = new String(str.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		UsernamePasswordCredentials creds = new UsernamePasswordCredentials("admin", "admin");
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// 用户名:密码
		String encoding = new String(Base64.encodeBase64(StringUtils.getBytesUtf8("admin:admin")));
		StringBuilder result = new StringBuilder();
		try {
			HttpPut putRequest = new HttpPut(
					"http://121.48.175.200:8080/restconf/config/opendaylight-inventory:nodes/node/openflow:171592210022736/flow-node-inventory:table/60/flow/5");
			putRequest.addHeader("Authorization", "Basic " + encoding);
			putRequest.addHeader("Content-Type", "application/json;charset=UTF-8");
			putRequest.addHeader("Accept", "application/json"); // JSONObject
			StringEntity input = null;
			try {
				input = new StringEntity(b);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			putRequest.setEntity(input);
			HttpResponse response = httpClient.execute(putRequest);
			System.out.println(response.getStatusLine().getStatusCode());
			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
			String output;
			while ((output = br.readLine()) != null) {
				result.append(output);
			}
			System.out.println(output);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
