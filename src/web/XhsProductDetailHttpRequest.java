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
     * ��ָ��URL����GET����������
     * 
     * @param url
     *            ���������URL
     * @param param
     *            ����������������Ӧ���� name1=value1&name2=value2 ����ʽ��
     * @return URL ������Զ����Դ����Ӧ���
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // �򿪺�URL֮�������
            URLConnection connection = realUrl.openConnection();
            // ����ͨ�õ���������
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // ����ʵ�ʵ�����
            connection.connect();
            // ��ȡ������Ӧͷ�ֶ�
            Map<String, List<String>> map = connection.getHeaderFields();
            // �������е���Ӧͷ�ֶ�
            for (String key : map.keySet()) {
               // System.out.println(key + "--->" + map.get(key));
            }
            // ���� BufferedReader����������ȡURL����Ӧ
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("����GET��������쳣��" + e);
            e.printStackTrace();
        }
        // ʹ��finally�����ر�������
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
     * ��ָ�� URL ����POST����������
     * 
     * @param url
     *            ��������� URL
     * @param param
     *            ����������������Ӧ���� name1=value1&name2=value2 ����ʽ��
     * @return ������Զ����Դ����Ӧ���
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // �򿪺�URL֮�������
            URLConnection conn = realUrl.openConnection();
            // ����ͨ�õ���������
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // ����POST�������������������
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // ��ȡURLConnection�����Ӧ�������
            out = new PrintWriter(conn.getOutputStream());
            // �����������
            out.print(param);
            // flush������Ļ���
            out.flush();
            // ����BufferedReader����������ȡURL����Ӧ
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("���� POST ��������쳣��"+e);
            e.printStackTrace();
        }
        //ʹ��finally�����ر��������������
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
             System.out.println("д��ʧ��");
             System.exit(-1);
         }
    }
    
    public static void main(String[] args) {
        //���� GET ����
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
        
        //���� POST ����
        /*String sr=HttpRequest.sendPost("http://localhost:6144/Home/RequestPostString", "key=123&v=456");
        System.out.println(sr);*/
    }
}

