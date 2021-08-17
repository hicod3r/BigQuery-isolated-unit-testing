package com.hicoder.samples;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import org.apache.commons.codec.binary.Hex;

import com.google.cloud.bigquery.TableResult;

public class RunSampleQuery {

	public static void main(String[] args) throws InterruptedException, IOException, NoSuchAlgorithmException {

		
		String[] queries = {"SELECT count(*) FROM `bigquery-public-data.baseball.games_post_wide` LIMIT 1000",
				"SELECT count(*) FROM `bigquery-public-data.austin_crime.crime` LIMIT 1000"};
		
		Properties prop = Toolbox.getInstance().getPropertyFile();
		
		
		for( String query : queries) {
			
			TableResult tableResults = Toolbox.getInstance().runQuery(query);
			
			String key = Toolbox.getInstance().convertToMd5(query);
			
			prop.setProperty(key, Toolbox.getInstance().serializeToBase64((tableResults)));
		}
		
		
		FileOutputStream fr = new FileOutputStream("./src/main/resources/query.properties");
		prop.store(fr, null);
		
		fr.close();

	}

	
}
