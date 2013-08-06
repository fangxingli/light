package com.light.sina.bean;

public class User{

private long mId;//用户UID
private String mIdstr;//字符串型的用户UID
private String mScreenName;//用户昵称
private String mName;//友好显示名称
private int mProvince;//用户所在省级ID
private int mCity;//用户所在城市ID
private String mLocation;//用户所在地
private String mDescription;//用户个人描述
private String mUrl;//用户博客地址
private String mProfileImageUrl;//用户头像地址，50×50像素
private String mProfileUrl;//用户的微博统一URL地址
private String mDomain;//用户的个性化域名
private String mWeihao;//用户的微号
private String mGender;//性别，m：男、f：女、n：未知
private int mFollowersCount;//粉丝数
private int mFriendsCount;//关注数
private int mStatusesCount;//微博数
private int mFavouritesCount;//收藏数
private String mCreatedAt;//用户创建（注册）时间
private boolean mFollowing;//暂未支持
private boolean mAllowAllActMsg;//是否允许所有人给我发私信，true：是，false：否
private boolean mGeoEnabled;//是否允许标识用户的地理位置，true：是，false：否
private boolean mVerified;//是否是微博认证用户，即加V用户，true：是，false：否
private int mVerifiedType;//暂未支持
private String mRemark;//用户备注信息，只有在查询用户关系时才返回此字段
private Status mStatus;//Status用户的最近一条微博信息字段详细
private boolean mAllowAllComment;//是否允许所有人对我的微博进行评论，true：是，false：否
private String mAvatarLarge;//用户大头像地址
private String mVerifiedReason;//认证原因
private boolean mFollowMe;//该用户是否关注当前登录用户，true：是，false：否
private int mOnlineStatus;//用户的在线状态，0：不在线、1：在线
private int mBiFollowersCount;//用户的互粉数
private String mLang;//用户当前的语言版本，zh-cn：简体中文，zh-tw：繁体中文，en：英语

public void setId(long v){
		  		this.mId = v;
		  }
	
		  public long getId(){
				return this.mId;
		  }
public void setIdstr(String v){
		  		this.mIdstr = v;
		  }
	
		  public String getIdstr(){
				return this.mIdstr;
		  }
public void setScreenName(String v){
		  		this.mScreenName = v;
		  }
	
		  public String getScreenName(){
				return this.mScreenName;
		  }
public void setName(String v){
		  		this.mName = v;
		  }
	
		  public String getName(){
				return this.mName;
		  }
public void setProvince(int v){
		  		this.mProvince = v;
		  }
	
		  public int getProvince(){
				return this.mProvince;
		  }
public void setCity(int v){
		  		this.mCity = v;
		  }
	
		  public int getCity(){
				return this.mCity;
		  }
public void setLocation(String v){
		  		this.mLocation = v;
		  }
	
		  public String getLocation(){
				return this.mLocation;
		  }
public void setDescription(String v){
		  		this.mDescription = v;
		  }
	
		  public String getDescription(){
				return this.mDescription;
		  }
public void setUrl(String v){
		  		this.mUrl = v;
		  }
	
		  public String getUrl(){
				return this.mUrl;
		  }
public void setProfileImageUrl(String v){
		  		this.mProfileImageUrl = v;
		  }
	
		  public String getProfileImageUrl(){
				return this.mProfileImageUrl;
		  }
public void setProfileUrl(String v){
		  		this.mProfileUrl = v;
		  }
	
		  public String getProfileUrl(){
				return this.mProfileUrl;
		  }
public void setDomain(String v){
		  		this.mDomain = v;
		  }
	
		  public String getDomain(){
				return this.mDomain;
		  }
public void setWeihao(String v){
		  		this.mWeihao = v;
		  }
	
		  public String getWeihao(){
				return this.mWeihao;
		  }
public void setGender(String v){
		  		this.mGender = v;
		  }
	
		  public String getGender(){
				return this.mGender;
		  }
public void setFollowersCount(int v){
		  		this.mFollowersCount = v;
		  }
	
		  public int getFollowersCount(){
				return this.mFollowersCount;
		  }
public void setFriendsCount(int v){
		  		this.mFriendsCount = v;
		  }
	
		  public int getFriendsCount(){
				return this.mFriendsCount;
		  }
public void setStatusesCount(int v){
		  		this.mStatusesCount = v;
		  }
	
		  public int getStatusesCount(){
				return this.mStatusesCount;
		  }
public void setFavouritesCount(int v){
		  		this.mFavouritesCount = v;
		  }
	
		  public int getFavouritesCount(){
				return this.mFavouritesCount;
		  }
public void setCreatedAt(String v){
		  		this.mCreatedAt = v;
		  }
	
		  public String getCreatedAt(){
				return this.mCreatedAt;
		  }
public void setFollowing(boolean v){
		  		this.mFollowing = v;
		  }
	
		  public boolean getFollowing(){
				return this.mFollowing;
		  }
public void setAllowAllActMsg(boolean v){
		  		this.mAllowAllActMsg = v;
		  }
	
		  public boolean getAllowAllActMsg(){
				return this.mAllowAllActMsg;
		  }
public void setGeoEnabled(boolean v){
		  		this.mGeoEnabled = v;
		  }
	
		  public boolean getGeoEnabled(){
				return this.mGeoEnabled;
		  }
public void setVerified(boolean v){
		  		this.mVerified = v;
		  }
	
		  public boolean getVerified(){
				return this.mVerified;
		  }
public void setVerifiedType(int v){
		  		this.mVerifiedType = v;
		  }
	
		  public int getVerifiedType(){
				return this.mVerifiedType;
		  }
public void setRemark(String v){
		  		this.mRemark = v;
		  }
	
		  public String getRemark(){
				return this.mRemark;
		  }
public void setStatus(Status v){
		  		this.mStatus = v;
		  }
	
		  public Status getStatus(){
				return this.mStatus;
		  }
public void setAllowAllComment(boolean v){
		  		this.mAllowAllComment = v;
		  }
	
		  public boolean getAllowAllComment(){
				return this.mAllowAllComment;
		  }
public void setAvatarLarge(String v){
		  		this.mAvatarLarge = v;
		  }
	
		  public String getAvatarLarge(){
				return this.mAvatarLarge;
		  }
public void setVerifiedReason(String v){
		  		this.mVerifiedReason = v;
		  }
	
		  public String getVerifiedReason(){
				return this.mVerifiedReason;
		  }
public void setFollowMe(boolean v){
		  		this.mFollowMe = v;
		  }
	
		  public boolean getFollowMe(){
				return this.mFollowMe;
		  }
public void setOnlineStatus(int v){
		  		this.mOnlineStatus = v;
		  }
	
		  public int getOnlineStatus(){
				return this.mOnlineStatus;
		  }
public void setBiFollowersCount(int v){
		  		this.mBiFollowersCount = v;
		  }
	
		  public int getBiFollowersCount(){
				return this.mBiFollowersCount;
		  }
public void setLang(String v){
		  		this.mLang = v;
		  }
	
		  public String getLang(){
				return this.mLang;
		  }

}