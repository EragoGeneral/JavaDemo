package web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.product.OurProductInfo;
import net.sf.json.JSONObject;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

public class XhsProductDetailHttpRequest {
	
	public static final String url = "jdbc:mysql://139.199.191.210:3306/mybatis?user=root&password=root&useUnicode=true&characterEncoding=UTF8";
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
         	 File f = new File("G:\\XHS\\"+fileName+".txt");	
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
    
    public static List<OurProductInfo> queryProductDetail(int start, int pageSize) throws SQLException{
    	Connection conn = null;
        String sql;
        List<OurProductInfo> products = new ArrayList<OurProductInfo>();
        try {
            Class.forName("com.mysql.jdbc.Driver");// ��̬����mysql����
 
            System.out.println("�ɹ�����MySQL��������");
            conn = (Connection) DriverManager.getConnection(url);
            Statement stmt = (Statement) conn.createStatement();
        	sql = "select * from xhs_product_new limit "+ start +", " + pageSize;
        	ResultSet result = (ResultSet) stmt.executeQuery(sql);
            while(result.next()){ 
            	OurProductInfo p = new OurProductInfo();
    			p.setId(result.getString("id"));
    			p.setLink(result.getString("link"));
    			products.add(p);
        	}     
        } catch (SQLException e) {
            System.out.println("MySQL��������");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
        
        return products;
    }
    
    public static void parseProductImage(List<OurProductInfo> products, int i){
		
    	for(OurProductInfo p : products){
    		String id = p.getId();
    		String link = p.getLink();
    		String s=XhsProductDetailHttpRequest.sendGet(link,"");
            
    		String data = s.substring(s.indexOf("facade"));
    		data = data.substring(data.indexOf("{"),data.indexOf("</script>"));  
    		data = data.substring(0, data.lastIndexOf(")"));
    		//System.out.println(data);
    		JSONObject obj = JSONObject.fromObject(data);
    		JSONObject dataObj = (JSONObject)obj.get("data");
    		JSONObject itemObj = (JSONObject)dataObj.get("item");
    		List<JSONObject> list = (List<JSONObject>)itemObj.get("images");
    		StringBuffer buf = new StringBuffer("");
    		for(JSONObject json : list){    			
    			buf.append(";").append(((String)json.get("fixed_width")).substring(2));
    		}    				
    		buf.deleteCharAt(0);
    		String fileContent = "{\"id\":\""+ id +"\", \"images\":\""+ buf.toString() +"\"}";
    		System.out.println(fileContent);
    		writeFile(String.valueOf(i), fileContent);
    	}
    }
    
    public static void main(String[] args) throws SQLException {
    	int start = 1545;
		for(int i = start; i < start+100; i++){
			int startPage = i*10;
    		List<OurProductInfo> products = queryProductDetail(startPage, 10);
    		parseProductImage(products, i);
		}
	}
}