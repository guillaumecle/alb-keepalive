package bzh.guillaume.alb.keepalive;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class Main{
	private static final Log logger = LogFactory.getLog(Main.class);

	public static void main(String[] args) throws ClientProtocolException, IOException, InterruptedException{
		String url = args[0];
		try(CloseableHttpClient httpclient = HttpClients.createDefault()){
			makeRequest(httpclient, url);
			sleep(args[1]);
			makeRequest(httpclient, url);
		}
	}

	private static void sleep(String secondsString) throws InterruptedException{
		int seconds = Integer.parseInt(secondsString);
		logger.info("sleeping " + seconds  + "s");
		Thread.sleep(seconds * 1_000);
	}

	private static void makeRequest(CloseableHttpClient httpclient, String url) throws ClientProtocolException, IOException{
		HttpGet httpGet = new HttpGet(url);
		try(CloseableHttpResponse response1 = httpclient.execute(httpGet)){
			HttpEntity entity = response1.getEntity();
			String textResponse = EntityUtils.toString(entity);
			logger.info(textResponse);
		}
	}

}