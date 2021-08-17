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
import com.hicoder.samples.Toolbox;

//import utilities.BqUtils;

public class BigqueryTesting {
	
	
	@Mock private Toolbox utils;
	
	Properties queryReponse = new Properties();
	
	String queryReponseRef = "query.properties";
	

	@Before
	public void setUp() throws Exception {
		
		
		InputStream is = getClass().getClassLoader().getResourceAsStream(queryReponseRef);

		if (is != null) {
			queryReponse.load(is);
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
		
		TableResult tableResults = utils.runQuery("SELECT count(*) FROM `bigquery-public-data.baseball.games_post_wide` LIMIT 1000");
		
		Iterable<FieldValueList> results = tableResults.getValues();
		
		String resultCount = "";
		
		for (FieldValueList x : results){
			
			resultCount = x.get(0).getStringValue();
			
		}
		
		assert resultCount.equalsIgnoreCase("8676");
		
	}
	
	
	@Test
	public void testAustinBikeCount() throws InterruptedException {
		
		TableResult tableResults = utils.runQuery("SELECT count(*) FROM `bigquery-public-data.austin_crime.crime` LIMIT 1000");
		
		Iterable<FieldValueList> results = tableResults.getValues();
		
		String resultCount = "";
		
		for (FieldValueList x : results){
			
			resultCount = x.get(0).getStringValue();
			
		}
		
		assert resultCount.equalsIgnoreCase("116675");
		
	}
	
	
	
	public TableResult getResponse(String query) throws ClassNotFoundException, IOException, NoSuchAlgorithmException {
		
		
		String key = Toolbox.getInstance().convertToMd5(query);
		
		String result = (String) queryReponse.get(key);
		
		System.out.println(result);
		
		if(query != null)
			return (TableResult) Toolbox.getInstance().deserializeFromBase64(result);
		
		return null;
		
	}

}
