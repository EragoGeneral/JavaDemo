package basic.calendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TestCalender {
	public static void main(String[] args) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");
		Date date = null;
		try {
			int year = 2015;
			date = df.parse(year + "-01-01"); // 开始时间
			int day = date.getDay();// 获取当前时间是星期几(0为星期天，6为星期六)
			System.out.println("这年的第一天是：" + day);
			int startSatOffset = 6 - day;// 判断一年的第一天隔最近的星期六有几天
			// 结束时间
			Calendar calender = Calendar.getInstance();
			calender.setTime(date);
			calender.add(Calendar.YEAR, 1);
			System.out.println(calender.getTime());
			
			Date endtime = calender.getTime(); // 结束时间
			int j = 1;
			for (int i = 0; i <= 365 / 7; i++) {

				Date satday = df.parse(year + "-" + j + "-"
						+ (1 + startSatOffset + i * 7));// 星期六日期
				Date sunday = df.parse(year + "-" + j + "-"
						+ (1 + startSatOffset + (i * 7 + 1))); // 星期天日期
				System.out.println(satday);
				System.out.println(sunday);
				// 时间不能超过一年 （最后一个星期六不能超过结束时间）
				// 如果satday比endtime时间早
				if (satday.before(endtime)) {
					// 获取该年该月的最后一天的日期
					Date da = df.parse(year + "-" + j + "-1");
					Calendar calendar2 = Calendar.getInstance();
					calendar2.setTime(da);
					calendar2.add(Calendar.MONTH, j);
					calendar2.add(Calendar.DAY_OF_YEAR, -1);
					//System.out.println(calendar2.getTime());
					Date zui = calendar2.getTime();// 当前月的最后一天
					// 当星期六星期天超过了这个月的最后一天
					if (zui.before(satday) || zui.before(sunday)) {
						j = j + 1;
						satday = df.parse(year + "-" + j + "-"
								+ (1 + startSatOffset + i * 7));// 星期六日期
						sunday = df.parse(year + "-" + j + "-"
								+ (1 + startSatOffset + (i * 7 + 1))); // 星期天日期'
						System.out.println("当月最后一个周六"+satday);
						System.out.println("当月最后一个周天"+satday);
					}
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
