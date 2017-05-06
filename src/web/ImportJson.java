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
			    //line = "{\"name\":\"JSON\",\"age\":\"24\",\"address\":\"������������\"}";
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
        // MySQL��JDBC URL��д��ʽ��jdbc:mysql://�������ƣ����Ӷ˿�/���ݿ������?����=ֵ
        // ������������Ҫָ��useUnicode��characterEncoding
        // ִ�����ݿ����֮ǰҪ�����ݿ����ϵͳ�ϴ���һ�����ݿ⣬�����Լ�����
        // �������֮ǰ��Ҫ�ȴ���javademo���ݿ�
        String url = "jdbc:mysql://localhost:3306/ebook?"
                + "user=root&password=root&useUnicode=true&characterEncoding=UTF8";
 
        try {
            // ֮����Ҫʹ������������䣬����ΪҪʹ��MySQL����������������Ҫ��������������
            // ����ͨ��Class.forName�������ؽ�ȥ��Ҳ����ͨ����ʼ������������������������ʽ������
            Class.forName("com.mysql.jdbc.Driver");// ��̬����mysql����
            // or:
            // com.mysql.jdbc.Driver driver = new com.mysql.jdbc.Driver();
            // or��
            // new com.mysql.jdbc.Driver();
 
            System.out.println("�ɹ�����MySQL��������");
            // һ��Connection����һ�����ݿ�����
            conn = (Connection) DriverManager.getConnection(url);
            // Statement������кܶ෽��������executeUpdate����ʵ�ֲ��룬���º�ɾ����
            Statement stmt = (Statement) conn.createStatement();
            for(LotteryInfo li : lotteries){
            	sql = "insert into tb_lottery(period, red_ball, blue_ball) values('"+ li.getPeriod() +"','"+ li.getRedBall() +"', '"+ li.getBlueBall() +"')";
                stmt.executeUpdate(sql);// executeUpdate���᷵��һ����Ӱ����������������-1��û�гɹ�
            }
        } catch (SQLException e) {
            System.out.println("MySQL��������");
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
