package com.stocktunes.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;

import com.stocktunes.tools.URLConnectionReader;

public class StockReader {
	public static void main(String[] args) throws Exception {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX"); // Format ISO 8601
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		String url = "http://finance.yahoo.com/d/quotes.csv?s=TSLA&f=sl1d1t1c1ohgv";
		String content = URLConnectionReader.getText(url);
		List<String> stockList = new ArrayList<String>(Arrays.asList(content.split(",")));
		stockList.add(0, sdf.format(cal.getTime()));
		System.out.println(stockList.toString());
		
		BufferedWriter bw = null;
		try {
			File file = new File("quotes/"+stockList.get(1).toString().substring(1, stockList.get(1).toString().length()-1)+".csv");
			if (!file.exists())
				file.createNewFile();
			FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);
			bw.write(stockList.toString().replace("[", "").replace("]", "").replace(", ", ",")+"\r\n");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				bw.close();
			} catch (Exception e) {
			}
		}
	}
}