package basic.collections;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

public class MapTest {

	public static void main(String[] args) {
		
		String words = "Chinese Commerce Minister Gao Hucheng and his South Korean counterpart Yoon Sang-jick signed the bilateral Free Trade Agreement in Seoul on Monday after almost three years of negotiations.The agreement covers 22 areas, including trade in goods and services, investment and government procurement.It is also the first time for China to include finance, telecommunications and e-commerce industries in a free trade deal.The agreement aims remove tariffs on over 90 percent of goods traded between the two countries in the next two decades.";
		String newWords = words.replace(".", "_").replace(",", "_").replace(" ", "_");

		String[] array = newWords.split("_");
		int wordAmt = array.length;
		
		Map<String, Integer> wordMap = new HashMap<String, Integer>(); 
		for(String word:array){
			if("".equals(word)){
				continue;
			}
			if(wordMap.get(word) != null){
				int wordCnt = wordMap.get(word).intValue();
				wordMap.put(word, (wordCnt+1));
			}else{
				wordMap.put(word, 1);
			}
		}
		
		for(String key:wordMap.keySet()){
			int count = wordMap.get(key).intValue();
			double rate = count*1.0/wordAmt*100;
			NumberFormat nf = NumberFormat.getInstance();
			nf.setMaximumFractionDigits(2);
			String rateStr = nf.format(rate);
			System.out.println("word: "+key+",count: "+count+", rate: "+rateStr+"%.");
		}
	}
}
