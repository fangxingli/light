package com.light.sina.bean;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSON2Java{
	private String mData;
	private JSONObject mRoot;
	
	public JSON2Java(String data)throws Exception{
		mData = data;
		mRoot = new JSONObject(mData);
	}
	
	public JSON2Java(JSONObject obj)throws Exception{
		mRoot = new JSONObject(mData);
	}
	
	public Status[] getStatus()throws Exception{
		Status[] ret = null;
		if( mRoot.has("statuses") ){
			JSONArray status_node = mRoot.getJSONArray("statuses");
			ret = new Status[status_node.length()];
			
			for(int i=0; i<status_node.length(); i++){
				ret[i] = new Status();
				ret[i].setCreatedAt(status_node.getJSONObject(i).getString("created_at"));
				ret[i].setText(status_node.getJSONObject(i).getString("text"));
				ret[i].setSource(status_node.getJSONObject(i).getString("source"));
				ret[i].setCommentsCount(status_node.getJSONObject(i).getInt("comments_count"));
				ret[i].setRepostsCount(status_node.getJSONObject(i).getInt("reposts_count"));
				ret[i].setAttitudesCount(status_node.getJSONObject(i).getInt("attitudes_count"));
				ret[i].setPicUrls(status_node.getJSONObject(i).getJSONArray("pic_urls"));
				// retweet处理
				if( status_node.getJSONObject(i).has("retweeted_status") ){
					JSONObject retweeted_json_obj = status_node.getJSONObject(i).getJSONObject("retweeted_status");
					JSONObject retweeted_user_json = retweeted_json_obj.getJSONObject("user");
					Status retweeted_status = new Status();
					User retweeted_u = new User();
					
					retweeted_status.setText(retweeted_json_obj.getString("text"));
					retweeted_status.setPicUrls(retweeted_json_obj.getJSONArray("pic_urls"));
					
					retweeted_u.setProfileImageUrl(retweeted_user_json.getString("profile_image_url"));
					retweeted_u.setName(retweeted_user_json.getString("name"));
					
					retweeted_status.setUser(retweeted_u);
					ret[i].setRetweetedStatus(retweeted_status);
				}else{
					ret[i].setRetweetedStatus(null);
				}
				
				JSONObject user_json = status_node.getJSONObject(i).getJSONObject("user");
				User u = new User();
				u.setProfileImageUrl(user_json.getString("profile_image_url"));
				u.setName(user_json.getString("name"));
				ret[i].setUser(u);
			}
		}
		return ret;
	}
}