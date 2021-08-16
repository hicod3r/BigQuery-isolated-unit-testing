import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.Md5Crypt;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.google.cloud.bigquery.FieldValueList;
import com.google.cloud.bigquery.TableResult;
import com.hicoder.samples.BigQueryUtils;

//import utilities.BqUtils;

public class BigqueryTesting {
	
	
	@Mock private BigQueryUtils utils;
	
	Properties prop = new Properties();
	
	String propFileName = "query.properties";
	

	@Before
	public void setUp() throws Exception {
		
		
		InputStream is = getClass().getClassLoader().getResourceAsStream(propFileName);

		if (is != null) {
			prop.load(is);
		} else {
			throw new FileNotFoundException("Query Property file not in classpath");
		}
		
		
		MockitoAnnotations.initMocks(this);
		
		
		when(utils.runQuery(anyString())).thenAnswer(new Answer<TableResult>() {
		    public TableResult answer(InvocationOnMock invocation) throws Throwable {
		      
		    	Object[] args = invocation.getArguments();
		      
		    	return getResponse((String) args[0]);
		    }
		  });

		 
	}

	
	@Test
	public void testBaseballCount() throws InterruptedException {
		
		TableResult xyz = utils.runQuery("SELECT count(*) FROM `bigquery-public-data.baseball.games_post_wide` LIMIT 1000");
		
		Iterable<FieldValueList> results = xyz.getValues();
		
		String resultCount = "";
		
		for (FieldValueList x : results){
			
			
			System.out.println(x.get(0).getStringValue());
			
			resultCount = x.get(0).getStringValue();
			
		}
		
		assert resultCount.equalsIgnoreCase("8676");
		
	}
	
	
	@Test
	public void terstAustinBikeCount() throws InterruptedException {
		
		TableResult xyz = utils.runQuery("SELECT count(*) FROM `bigquery-public-data.austin_crime.crime` LIMIT 1000");
		
		Iterable<FieldValueList> results = xyz.getValues();
		
		String resultCount = "";
		
		for (FieldValueList x : results){
			
			
			System.out.println(x.get(0).getStringValue());
			
			resultCount = x.get(0).getStringValue();
			
		}
		
		assert resultCount.equalsIgnoreCase("116675");
		
	}
	
	
	
	public TableResult getResponse(String query) throws ClassNotFoundException, IOException, NoSuchAlgorithmException {
		
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(query.getBytes(Charset.forName("UTF8")));
		
		byte[] resultByte = md.digest();
		
		String key = new String(Hex.encodeHex(resultByte));
		
		String result = (String) prop.get(key);
		
		System.out.println(result);
		
		if(query != null)
			return (TableResult) BigQueryUtils.getInstance().fromString(result);
		
		return null;
		
	}

}
