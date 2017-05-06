package basic.buildstring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestBuildSql {
	
	
	public static void main(String[] args) {
		
		String[] fields = {"id","mch_id","trade_type","trade_name","enabled","bill_rate", "pre_max_limit","created_at","updated_at","updated_version"};
		List<String> list = Arrays.asList("id","mch_id","trade_type","trade_name","enabled","bill_rate", "pre_max_limit","created_at","updated_at","updated_version");
		Map<String, List> tbFls = new HashMap<String, List>();
		tbFls.put("pay_ladder_rate", list);
		
		list = Arrays.asList("id",
							"organ_id",
							"channel_id",
							"name",
							"bank_id",
							"parent_id",
							"agentno",
							"treelevel",
							"subnode_num",
							"examine_status",
							"examine_time",
							"examine_remark",
							"parent_invite_code",
							"invite_code",
							"channel_type",
							"status",
							"create_time",
							"created_by",
							"status_remark",
							"is_delete",
							"p_cloud_key",
							"update_version");
		
		StringBuffer tBuf = new StringBuffer("select '\\\"' ||");
		StringBuffer buf = new StringBuffer("select '\\\"' ||");
		StringBuffer hBuf = new StringBuffer("select '\\\"' ||");
		for(int index = 0; index < list.size(); index++){
			if(index==list.size() - 1){
				buf.append("plr.").append(list.get(index)).append("||'	\\\"'");
				hBuf.append("'").append(list.get(index)).append("'||'\\\"'").append(" from dual union all ");
			}else{
				buf.append("plr.").append(list.get(index)).append("||'\\\",\\\"'||");
				hBuf.append("'").append(list.get(index)).append("'||'\\\",\\\"'||");
			}
			if(index == 0){
				tBuf.append("v3.0").append("||'\\\",\\\"'||");;
			}else if(index == 1){
				tBuf.append("Count: ").append("||'\\\",\\\"'||");;
			}else if(index == 2){
				tBuf.append("6").append("||'\\\",\\\"'||");
			}else if(index == list.size()-1){
				tBuf.append("''").append("||'\\\"'").append(" from dual union all ");
			}else{
				tBuf.append("''").append("||'\\\",\\\"'||");
			}
		}
//		buf.append(" from pay_ladder_rate plr join pay_mch mch on mch.id = plr.mch_id");
//		hBuf.append(" from dual union all ");	
		System.out.println(tBuf.toString());
		System.out.println(hBuf.toString());
		System.out.println(buf.toString());		
		
	}

}
