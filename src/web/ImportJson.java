package web;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

public class ImportJson {
	
	public static List<LotteryInfo> readTxtFile(String filePath){
		List<LotteryInfo> ret = new ArrayList<LotteryInfo>();
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return ret;
	}
	
	public static void saveDataToDB(List<LotteryInfo> lotteries) throws SQLException{
		Connection conn = null;
        String sql;
        // MySQL的JDBC URL编写方式：jdbc:mysql://主机名称：连接端口/数据库的名称?参数=值
        // 避免中文乱码要指定useUnicode和characterEncoding
        // 执行数据库操作之前要在数据库管理系统上创建一个数据库，名字自己定，
        // 下面语句之前就要先创建javademo数据库
        String url = "jdbc:mysql://localhost:3306/ebook?"
                + "user=root&password=root&useUnicode=true&characterEncoding=UTF8";
 
        try {
            // 之所以要使用下面这条语句，是因为要使用MySQL的驱动，所以我们要把它驱动起来，
            // 可以通过Class.forName把它加载进去，也可以通过初始化来驱动起来，下面三种形式都可以
            Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
            // or:
            // com.mysql.jdbc.Driver driver = new com.mysql.jdbc.Driver();
            // or：
            // new com.mysql.jdbc.Driver();
 
            System.out.println("成功加载MySQL驱动程序");
            // 一个Connection代表一个数据库连接
            conn = (Connection) DriverManager.getConnection(url);
            // Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
            Statement stmt = (Statement) conn.createStatement();
            for(LotteryInfo li : lotteries){
            	sql = "insert into tb_lottery(period, red_ball, blue_ball) values('"+ li.getPeriod() +"','"+ li.getRedBall() +"', '"+ li.getBlueBall() +"')";
                stmt.executeUpdate(sql);// executeUpdate语句会返回一个受影响的行数，如果返回-1就没有成功
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
	
	public static void main(String[] args) {
		for(int year = 2017; year <= 2017; year++){
			List<LotteryInfo> lotteries = readTxtFile("G:\\Lottery\\"+ year +".txt");
	//		for(LotteryInfo li : lotteries){
	//			System.out.println(li.getPeriod()+ ", " +li.getRedBall() + ", " + li.getBlueBall());
	//		}
			try {
				saveDataToDB(lotteries);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
