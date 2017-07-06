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

import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

public class XhsProductDetailHttpRequest {
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
    		String s=XhsProductDetailHttpRequest.sendGet("https://www.xiaohongshu.com/goods/586f1203eed1881a28515358?xhs_g_s=0000","");
    		//System.out.println(s);
	        
    		String data = s.substring(s.indexOf("facade"));
    		data = data.substring(data.indexOf("{"),data.indexOf("</script>"));  
    		data = data.substring(0, data.lastIndexOf(")"));
    		System.out.println(data);
    		JSONObject obj = JSONObject.fromObject(data);
    		JSONObject dataObj = (JSONObject)obj.get("data");
    		JSONObject itemObj = (JSONObject)dataObj.get("item");
    		List<JSONObject> list = (List<JSONObject>)itemObj.get("images");
    		for(JSONObject json : list){
    			System.out.println(((String)json.get("fixed_width")).substring(2));
    		}
    		
	         
//	        String fileContent = "{\"year\":\""+ year +"\", \"red\":\""+ redBuffer.toString() +"\", \"blue\":\""+ blueBuffer.toString() +"\"}";
//	        System.out.println(fileContent);
//	        writeFile(String.valueOf(count), fileContent);
    	}
        
//        String ssss = sss.substring(0, sss.indexOf("</table>"));
//        System.out.println(ssss.replaceAll(" ", ""));
        
        //发送 POST 请求
        /*String sr=HttpRequest.sendPost("http://localhost:6144/Home/RequestPostString", "key=123&v=456");
        System.out.println(sr);*/
    }
}

