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