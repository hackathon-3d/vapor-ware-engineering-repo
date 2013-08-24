package com.example.testhack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
/**  
  *	A class for determining the URL's of a MM2.0 list to download
  * based on the kittystore/scripts.py file on the hyperkitty github account.
  * @Author Chris Cargile
  **/
public class Computater{
	
	static enum MONTHS {January , February, March, April, May, June, July,
          August, September, October, November, December};
	
	/**
	* get a url-specified list and return a list
	* of all the URL's for the Monthly files
	* associated with it.
	* @param args either a single arg or optionally 3 args, 
	* where the starting year and ending year are provided
	* if desired
	**/
	public Computater(String[] args){
		
		}
	public ArrayList<String> getArchiverMonths(){
		/*
		if(args!=null){
			if(args[0]!=null){
				String url= args[0];
				
			}
		if(args[1]!=null){
				String fromStart= args[1];
			}
		if(args[2]!=null){
				String stopDate= args[2];
			}	
			*/
			String url="http://lists.csclug.org/pipermail/csclug";
			URL url2;
			HttpURLConnection http;
			InputStreamReader is;
			String htmlSource="";
			ArrayList<String> result=new ArrayList<String>();
			try {
				url2 = new URL(url);
				http= (HttpURLConnection) url2.openConnection();
				http.setRequestMethod("GET");
				is = new InputStreamReader(http.getInputStream());
				BufferedReader rd = new BufferedReader(is);
				String line=rd.readLine();
				while(line!=null){
					//System.out.println(line);
					htmlSource+=line;
					line=rd.readLine();
					}
				int startYear=1988;
				final Calendar thisYear=Calendar.getInstance();
				int endYear=thisYear.get(Calendar.YEAR);
				int year=startYear;
				
				for(;year>=startYear && year<=endYear;year++){
					for(Object Month:MONTHS.values()){
						String month=(String)Month.toString();
						String target=month+"-"+year;
						result.add(target);
					}
				}
				System.out.println(result.get(result.size()-1));
				for(int i=0;i<result.size();i++){
					if(!htmlSource.contains(result.get(i))){
						result.remove(result.get(i));
					}
				}
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
	}
	public static void main(String[] args){
		new Computater(null);
	}
}