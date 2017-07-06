package web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import net.sf.json.JSONObject;

public class LHCHttpRequest {
	
	public static final String url = "jdbc:mysql://localhost:3306/ebook?user=root&password=root&useUnicode=true&characterEncoding=UTF8";
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
       
    public static void writeFile(String fileName, String content){
    	 FileWriter fw = null;
         try{ 
         	 File f = new File("G:\\Lottery\\DLT\\"+fileName+".txt");	
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
    
    /**
     * 请求网络，生成历史数据
     * @param fromYear
     * @param toYear
     */
    public static void createData(int fromYear, int toYear){
    	for(int i = fromYear; i <= toYear; i++){
    		int count = i;
	    	String paramStart = String.valueOf(count).substring(2);
	    	String paramEnd = String.valueOf(count+1).substring(2);
	    	//发送 GET 请求
    		String s=LHCHttpRequest.sendGet("http://trend.caipiao.163.com/dlt/","beginPeriod="+paramStart+"001&endPeriod="+paramEnd+"001");
    		int end = s.indexOf("statisticsTitle");
    		int start = s.indexOf("cpdata");
    		
    		String result = s.substring(start, end);
    		String[] rets = result.split("<tr");
    		
    		for(String item : rets){
    			int pos = item.indexOf("data-period=\"");
    			if(pos == -1){
    				continue;
    			}
    			pos =  item.indexOf("\"");
    			item = item.substring(pos+1);
    			String period = item.substring(0, item.indexOf("\""));
    			
    			String[] ballArray = item.split("<td");
    			StringBuffer blueBuffer = new StringBuffer();
    			StringBuffer redBuffer = new StringBuffer();
    			
    			for(String ballItem:ballArray){
    				if(ballItem.indexOf("ball_blue") != -1){
    					int endPos = ballItem.indexOf("</td>");
    					String blueBall = ballItem.substring(endPos-2, endPos);
    					blueBuffer.append(",").append(blueBall);
    				}
    				if(ballItem.indexOf("ball_brown") != -1 || ballItem.indexOf("ball_red") != -1){
    					int endPos = ballItem.indexOf("</td>");
    					String redBall = ballItem.substring(endPos-2, endPos);
    					redBuffer.append(",").append(redBall);
    				}
    			}
    			/*Map<String, String> ballMap = new HashMap<String, String>();
    			ballMap.put("blue", blueBuffer.deleteCharAt(0).toString());
    			ballMap.put("red", redBuffer.deleteCharAt(0).toString());
    			resultMap.put(period, ballMap);*/
    			blueBuffer.deleteCharAt(0).toString();
    			redBuffer.deleteCharAt(0).toString();
    			if(period.indexOf(paramEnd) == 0){
    				continue;
    			}
    			String fileContent = "{\"year\":\""+ period +"\", \"red\":\""+ redBuffer.toString() +"\", \"blue\":\""+ blueBuffer.toString() +"\"}";
	  	        System.out.println(fileContent);
	  	        writeFile(String.valueOf(count), fileContent);
    		}
    	}
    		
		/*for(String key : resultMap.keySet()){
			System.out.println(key);
			Map<String, String> ballMap = resultMap.get(key);
			for(String ballKey : ballMap.keySet()){
				System.out.println(ballMap.get(ballKey));
			}
		}*/
    }
    
    public static List<LotteryInfo> readTxtFile(String filePath){
    	List<LotteryInfo> ret = new ArrayList<LotteryInfo>();
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        try {
			for (String line = br.readLine(); line != null; line = br.readLine()) {
			    System.out.println(line);      
			    //line = "{\"name\":\"JSON\",\"age\":\"24\",\"address\":\"北京市西城区\"}";
			    JSONObject jsonObject = JSONObject.fromObject(line);  
			    LotteryInfo li = new LotteryInfo();
			    li.setPeriod(jsonObject.getString("year"));
			    li.setRedBall(jsonObject.getString("red"));
			    li.setBlueBall(jsonObject.getString("blue"));
			    ret.add(li);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
        try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        return ret;
    }
    
    public static void splitDataToDB(List<LotteryInfo> lotteries) throws SQLException{
    	Connection conn = null;
        String sql;
        try {
            Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
 
            System.out.println("成功加载MySQL驱动程序");
            conn = (Connection) DriverManager.getConnection(url);
            Statement stmt = (Statement) conn.createStatement();
            for(LotteryInfo li : lotteries){
            	String[] blueBallArray = li.getBlueBall().split(",");
            	String[] redBallArray = li.getRedBall().split(",");
            	
            	sql = "insert into dlt_lottery_ext(period, red_ball1, red_ball2, red_ball3, " +
            			"red_ball4, red_ball5, blue_ball1, blue_ball2) " +
            			"values('"+ li.getPeriod() +"',"+ redBallArray[0] +", "+redBallArray[1]
            			+", "+redBallArray[2]+", "+redBallArray[3]+", "+redBallArray[4]+", "+blueBallArray[0]
            			+", "+ blueBallArray[1] +")";
                stmt.executeUpdate(sql);
            }
        } catch (SQLException e) {
            System.out.println("MySQL操作错误");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
    }
    
    public static void saveData(int from, int to){
    	for(int year = 2007; year <= 2017; year++){
			List<LotteryInfo> lotteries = readTxtFile("G:\\Lottery\\DLT\\"+ year +".txt");
			try {
				splitDataToDB(lotteries);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
    }
    
    public static String GetResultFromWebsite(){
    	String s=LHCHttpRequest.sendGet("http://www.898kj.com/html/indexb_2016.html","");
    	
    	return s;
    }
    
    public static void main(String[] args) {
    	//createData(2007, 2017);
    	//saveData(2007, 2017);
    	
    	String result = GetResultFromWebsite();
    	System.out.println(result);
    }
}
