

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
		ArrayList<String> answer=getArchiverMonths();
	 	for(String a:answer)
	 		System.out.println(a);
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
			String htmlSource=stringifyHTMLSource(url);
			//System.out.println(htmlSource);
			int startYear=1988;
			final Calendar thisYear=Calendar.getInstance();
			int endYear=thisYear.get(Calendar.YEAR);
			int year=startYear;
			ArrayList<String>result=new ArrayList<String>();
			int count=0;
			for(;year>=startYear && year<=endYear;year++){
				for(Object Month:MONTHS.values()){
					String month=(String)Month.toString();
					String target=year+"-"+month;
					result.add(target);
//					System.out.println(++count+")adding "+target);
				}
			}
			System.out.println(htmlSource);
			boolean found=false;
			//System.out.println(result.get(result.size()-1));
			ArrayList<String> result2 = new ArrayList<String>();
			for(String a:result)
				result2.add(new String(a));
			
			for(int i=0;i<result.size();i++){
				String word = result.get(i);
//				System.out.println("getting up to:"+word);
				if(!htmlSource.contains(word)){
					result2.remove(word);
//					System.out.println(result);
//					System.out.println(word);
				}
			}
			//System.out.println(result.size());
			return result2;
	}
	public String stringifyHTMLSource(String url){
		URL url2;
		HttpURLConnection http;
		InputStreamReader is;
		String htmlSource="";
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
		}
		 catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return htmlSource;
	}
	public static void main(String[] args){
		new Computater(null);
	}
}