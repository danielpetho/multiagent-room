import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Weather {
	
	private HttpClient httpClient;
	public int temp;
	
	public Weather() {
		temp = fetchTemp();
	}
	
	static private final String URL = "http://api.openweathermap.org/data/2.5/weather?q=Budapest&appid=652d0573840664dd57ad1bfe19258bf6";
	
	private JSONObject doQuery (String subUrl) throws JSONException, IOException {
		String responseBody = null;
		HttpGet httpget = new HttpGet (URL);
		
		httpClient = new DefaultHttpClient();

		HttpResponse response = this.httpClient.execute (httpget);

		InputStream contentStream = null;
		
		try {
			StatusLine statusLine = response.getStatusLine ();
			if (statusLine == null) {
				throw new IOException (
						String.format ("Unable to get a response from OWM server"));
			}
			int statusCode = statusLine.getStatusCode ();
			if (statusCode < 200 && statusCode >= 300) {
				throw new IOException (
						String.format ("OWM server responded with status code %d: %s", statusCode, statusLine));
			}
			/* Read the response content */
			HttpEntity responseEntity = response.getEntity ();
			contentStream = responseEntity.getContent ();
			Reader isReader = new InputStreamReader (contentStream);
			int contentSize = (int) responseEntity.getContentLength ();
			if (contentSize < 0)
				contentSize = 8*1024;
			StringWriter strWriter = new StringWriter (contentSize);
			char[] buffer = new char[8*1024];
			int n = 0;
			while ((n = isReader.read(buffer)) != -1) {
					strWriter.write(buffer, 0, n);
			}
			responseBody = strWriter.toString ();
			contentStream.close ();
		} catch (IOException e) {
			throw e;
		} catch (RuntimeException re) {
			httpget.abort ();
			throw re;
		} finally {
			if (contentStream != null)
				contentStream.close ();
		}
		return new JSONObject (responseBody);
	}
	
	public int fetchTemp() {
		double temp = 0;
		try {
			JSONObject obj = this.doQuery(URL);
			temp = obj.getJSONObject("main").getDouble("temp");
			/*
			 for (int i = 0; i < arr.length(); i++) {
		            temp = arr.getJSONObject(i).getString("temp");
		            System.out.println(temp);
		            
		        }*/
		} catch (IOException e) {
			
		}
		
		int temperature = ((int) Math.round(temp - 273.15));
		return temperature;
		
		
		
	}
	
}