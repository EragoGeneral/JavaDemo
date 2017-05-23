package web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ZhcwHttpRequest {
    /**
     * 向指定URL发送GET方法的请求
     * 
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
               // System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }    
    
    public static void writeFile(String fileName, String content){
    	 FileWriter fw = null;
         try{ 
         	 File f = new File("G:\\Lottery\\"+fileName+".txt");	
         	 if(!f.exists()){
         		 f.createNewFile();
         	 }
             fw = new FileWriter(f,true);
             //String c = "abs"+"\r\n";
             fw.write(content+"\r\n");
             fw.close();
         } catch (IOException e1) {
             e1.printStackTrace();
             System.out.println("写入失败");
             System.exit(-1);
         }
    }
    
    public static void main(String[] args) {
        //发送 GET 请求
    	//int count = 2005;
    	for(int count = 2017; count <= 2017; count++){
	    	//String s=ZhcwHttpRequest.sendGet("http://tubiao.zhcw.com/tubiao/ssqNew/ssqInc/ssqZongHeFengBuTuAsckj_year="+ count +".html","");
    		String s=ZhcwHttpRequest.sendGet("http://tubiao.zhcw.com/tubiao/ssqNew/ssqInc/ssqZongHeFengBuTuAsckj_year="+ count +".html","");
    		//System.out.println(s);
	        System.out.println("===========截获信息===============");
	        String tablePre = "<table id=\"table_ssq\"  cellspacing=\"0\" cellpadding=\"0\" >";
	        String ss = s.substring(s.indexOf(tablePre));
	        String sss = ss.substring(0, ss.indexOf("</table")).replace(tablePre, "");
	        //System.out.println(sss);
	        String[] array = sss.split("</tr>");
	        for(int i = 0 ; i < array.length; i++){
	        	//System.out.println(array[i]);
	        	String regexPeriod = "<a \\S*>[0-9]{5}</a>";  
	            String input = array[i];  
	            input = input.replace("td_bb", "td_b");
	            Pattern patternPeriod = Pattern.compile(regexPeriod);  
	            Matcher matcherPeriod = patternPeriod.matcher(input); 
	            String year = "20";
	            while (matcherPeriod.find()) {  
	                String regex1="[0-9]{5}";
	                Pattern pattern1 = Pattern.compile(regex1);  
	                Matcher matcher1 = pattern1.matcher(matcherPeriod.group()); 
	                while (matcher1.find()) {  
	                	year+=matcher1.group();
	                }
	            }  
	            System.out.println("Preiod: " + year);
	            
	          String regexRed = "<td class='redqiu td_b td_b \\S*'>[0-9]{1,2}</td>";
	          Pattern patternRed = Pattern.compile(regexRed);  
	          Matcher matcherRed = patternRed.matcher(input); 
	          //System.out.println("Red ball------");
	          StringBuffer redBuffer = new StringBuffer("");
	          while (matcherRed.find()) {  
	              String regex1="[0-9]{1,2}";
	              Pattern pattern1 = Pattern.compile(regex1);  
	              Matcher matcher1 = pattern1.matcher(matcherRed.group()); 
	              while (matcher1.find()) {  
	            	  //System.out.println(matcher1.group());
	            	  redBuffer.append(",").append(matcher1.group());
	              }
	          } 
	          redBuffer.deleteCharAt(0);
	          String regexBlue = "<td class='blueqiu3 td_b td_b td_r'>[0-9]{1,2}</td>";
	          Pattern patternBlue = Pattern.compile(regexBlue);  
	          Matcher matcherBlue = patternBlue.matcher(input);
	          //System.out.println("Blue ball------");
	          StringBuffer blueBuffer = new StringBuffer("");
	          while (matcherBlue.find()) {  
	        	  String blueChange = matcherBlue.group().replace("blueqiu3", "blueqiu");
	              String regex2="[0-9]{1,2}";
	              Pattern pattern2 = Pattern.compile(regex2);  
	              Matcher matcher2 = pattern2.matcher(blueChange); 
	              while (matcher2.find()) {  
	            	  //System.out.println(matcher2.group());
	            	  blueBuffer.append(",").append(matcher2.group());
	              }
	          }    
	          blueBuffer.deleteCharAt(0);
	          String fileContent = "{\"year\":\""+ year +"\", \"red\":\""+ redBuffer.toString() +"\", \"blue\":\""+ blueBuffer.toString() +"\"}";
	          System.out.println(fileContent);
	          writeFile(String.valueOf(count), fileContent);
	        }
    	}
        
//        String ssss = sss.substring(0, sss.indexOf("</table>"));
//        System.out.println(ssss.replaceAll(" ", ""));
        
        //发送 POST 请求
        /*String sr=HttpRequest.sendPost("http://localhost:6144/Home/RequestPostString", "key=123&v=456");
        System.out.println(sr);*/
    }
}

