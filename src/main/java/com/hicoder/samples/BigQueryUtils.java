package com.hicoder.samples;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Base64;
import java.util.Properties;
import java.util.UUID;

import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryOptions;
import com.google.cloud.bigquery.Job;
import com.google.cloud.bigquery.JobId;
import com.google.cloud.bigquery.JobInfo;
import com.google.cloud.bigquery.QueryJobConfiguration;
import com.google.cloud.bigquery.TableResult;

public class BigQueryUtils {
	
		
	private static BigQuery bigquery = BigQueryOptions.getDefaultInstance().getService();
	
	private static BigQueryUtils _instance = new BigQueryUtils();
	
	private void BigQueryUtils() {
		
		
		
	}
	
	public static BigQueryUtils getInstance() {
		
		return _instance;
	}
	
	 /**
	   * The method runBqQuery is a generic method to call any BigQuery Sql. It will return the result
	   * of BigQuery Sql as an object of TableResult.
	   *
	   * @param query : sql query to execute in bigquery
	   * @return
	   * @throws InterruptedException
	   */
	  public TableResult runQuery(String query) throws InterruptedException {
	    QueryJobConfiguration queryConfig =
	        QueryJobConfiguration.newBuilder(query).setUseLegacySql(false).build();

	    JobId jobId = JobId.of(UUID.randomUUID().toString());
	    
	    Job queryJob = bigquery.create(JobInfo.newBuilder(queryConfig).setJobId(jobId).build());
	    
	    TableResult result = queryJob.getQueryResults();
	    
	    return result;
	  }
	  
	  
	  
	  public Object fromString( String s ) throws IOException ,ClassNotFoundException {

			byte [] data = Base64.getDecoder().decode( s );
			ObjectInputStream ois = new ObjectInputStream( 
					new ByteArrayInputStream(  data ) );
			
			Object o  = ois.readObject();
			ois.close();
			return o;
		}
		
		public String toString( Serializable o ) throws IOException {
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        ObjectOutputStream oos = new ObjectOutputStream( baos );
	        oos.writeObject( o );
	        oos.close();
	        return Base64.getEncoder().encodeToString(baos.toByteArray()); 
	    }
		
		
		public Properties getPropertyFile() throws IOException {
			
			Properties prop = new Properties();
			InputStream in = getClass().getClassLoader().getResourceAsStream("query.properties");
			
			prop.load(in);
			
			return prop;

		}


}
