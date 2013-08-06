	
	
}
class Status{

	private String mCreatedAt;//微博创建时间
	private long mId;//微博ID
	private long mMid;//微博MID
	private String mIdstr;//字符串型的微博ID
	private String mText;//微博信息内容
	private String mSource;//微博来源
	private boolean mFavorited;//是否已收藏，true：是，false：否
	private boolean mTruncated;//是否被截断，true：是，false：否
	private String mInReplyToStatusId;//（暂未支持）回复ID
	private String mInReplyToUserId;//（暂未支持）回复人UID
	private String mInReplyToScreenName;//（暂未支持）回复人昵称
	private String mThumbnailPic;//缩略图片地址，没有时不返回此字段
	private String mBmiddlePic;//中等尺寸图片地址，没有时不返回此字段
	private String mOriginalPic;//原始图片地址，没有时不返回此字段
	private Geo mGeo;//Geo地理信息字段详细
	private User mUser;//User微博作者的用户信息字段详细
	private Status mRetweetedStatus;//Status被转发的原微博信息字段，当该微博为转发微博时返回详细
	private int mRepostsCount;//转发数
	private int mCommentsCount;//评论数
	private int mAttitudesCount;//表态数
	private int mMlevel;//暂未支持
	private Visible mVisible;//Visible微博的可见性及指定可见分组信息。该object中type取值，0：普通微博，1：私密微博，3：指定分组微博，4：密友微博；list_id为分组的组号
	private String[] mPicUrls;//微博配图地址。多图时返回多图链接。无配图返回“[]”

	public void setCreatedAt(String v){
			  		this.mCreatedAt = v;
			  }
		
			  public String getCreatedAt(){
					return this.mCreatedAt;
			  }
	public void setId(long v){
			  		this.mId = v;
			  }
		
			  public long getId(){
					return this.mId;
			  }
	public void setMid(long v){
			  		this.mMid = v;
			  }
		
			  public long getMid(){
					return this.mMid;
			  }
	public void setIdstr(String v){
			  		this.mIdstr = v;
			  }
		
			  public String getIdstr(){
					return this.mIdstr;
			  }
	public void setText(String v){
			  		this.mText = v;
			  }
		
			  public String getText(){
					return this.mText;
			  }
	public void setSource(String v){
			  		this.mSource = v;
			  }
		
			  public String getSource(){
					return this.mSource;
			  }
	public void setFavorited(boolean v){
			  		this.mFavorited = v;
			  }
		
			  public boolean getFavorited(){
					return this.mFavorited;
			  }
	public void setTruncated(boolean v){
			  		this.mTruncated = v;
			  }
		
			  public boolean getTruncated(){
					return this.mTruncated;
			  }
	public void setInReplyToStatusId(String v){
			  		this.mInReplyToStatusId = v;
			  }
		
			  public String getInReplyToStatusId(){
					return this.mInReplyToStatusId;
			  }
	public void setInReplyToUserId(String v){
			  		this.mInReplyToUserId = v;
			  }
		
			  public String getInReplyToUserId(){
					return this.mInReplyToUserId;
			  }
	public void setInReplyToScreenName(String v){
			  		this.mInReplyToScreenName = v;
			  }
		
			  public String getInReplyToScreenName(){
					return this.mInReplyToScreenName;
			  }
	public void setThumbnailPic(String v){
			  		this.mThumbnailPic = v;
			  }
		
			  public String getThumbnailPic(){
					return this.mThumbnailPic;
			  }
	public void setBmiddlePic(String v){
			  		this.mBmiddlePic = v;
			  }
		
			  public String getBmiddlePic(){
					return this.mBmiddlePic;
			  }
	public void setOriginalPic(String v){
			  		this.mOriginalPic = v;
			  }
		
			  public String getOriginalPic(){
					return this.mOriginalPic;
			  }
	public void setGeo(Geo v){
			  		this.mGeo = v;
			  }
		
			  public Geo getGeo(){
					return this.mGeo;
			  }
	public void setUser(User v){
			  		this.mUser = v;
			  }
		
			  public User getUser(){
					return this.mUser;
			  }
	public void setRetweetedStatus(Status v){
			  		this.mRetweetedStatus = v;
			  }
		
			  public Status getRetweetedStatus(){
					return this.mRetweetedStatus;
			  }
	public void setRepostsCount(int v){
			  		this.mRepostsCount = v;
			  }
		
			  public int getRepostsCount(){
					return this.mRepostsCount;
			  }
	public void setCommentsCount(int v){
			  		this.mCommentsCount = v;
			  }
		
			  public int getCommentsCount(){
					return this.mCommentsCount;
			  }
	public void setAttitudesCount(int v){
			  		this.mAttitudesCount = v;
			  }
		
			  public int getAttitudesCount(){
					return this.mAttitudesCount;
			  }
	public void setMlevel(int v){
			  		this.mMlevel = v;
			  }
		
			  public int getMlevel(){
					return this.mMlevel;
			  }
	public void setVisible(Visible v){
			  		this.mVisible = v;
			  }
		
			  public Visible getVisible(){
					return this.mVisible;
			  }
	public void setPicUrls(String[] v){
			  		this.mPicUrls = v;
			  }
		
			  public String[] getPicUrls(){
					return this.mPicUrls;
			  }

}
class Comment{

	private String mCreatedAt;//评论创建时间
	private long mId;//评论的ID
	private String mText;//评论的内容
	private String mSource;//评论的来源
	private User mUser;//User评论作者的用户信息字段详细
	private String mMid;//评论的MID
	private String mIdstr;//字符串型的评论ID
	private Status mStatus;//Status评论的微博信息字段详细
	private Comment mReplyComment;//Comment评论来源评论，当本评论属于对另一评论的回复时返回此字段

	public void setCreatedAt(String v){
			  		this.mCreatedAt = v;
			  }
		
			  public String getCreatedAt(){
					return this.mCreatedAt;
			  }
	public void setId(long v){
			  		this.mId = v;
			  }
		
			  public long getId(){
					return this.mId;
			  }
	public void setText(String v){
			  		this.mText = v;
			  }
		
			  public String getText(){
					return this.mText;
			  }
	public void setSource(String v){
			  		this.mSource = v;
			  }
		
			  public String getSource(){
					return this.mSource;
			  }
	public void setUser(User v){
			  		this.mUser = v;
			  }
		
			  public User getUser(){
					return this.mUser;
			  }
	public void setMid(String v){
			  		this.mMid = v;
			  }
		
			  public String getMid(){
					return this.mMid;
			  }
	public void setIdstr(String v){
			  		this.mIdstr = v;
			  }
		
			  public String getIdstr(){
					return this.mIdstr;
			  }
	public void setStatus(Status v){
			  		this.mStatus = v;
			  }
		
			  public Status getStatus(){
					return this.mStatus;
			  }
	public void setReplyComment(Comment v){
			  		this.mReplyComment = v;
			  }
		
			  public Comment getReplyComment(){
					return this.mReplyComment;
			  }

}
class User{

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
class Privacy{

	private int mComment;//是否可以评论我的微博，0：所有人、1：关注的人、2：可信用户
	private int mGeo;//是否开启地理信息，0：不开启、1：开启
	private int mMessage;//是否可以给我发私信，0：所有人、1：我关注的人、2：可信用户
	private int mRealname;//是否可以通过真名搜索到我，0：不可以、1：可以
	private int mBadge;//勋章是否可见，0：不可见、1：可见
	private int mMobile;//是否可以通过手机号码搜索到我，0：不可以、1：可以
	private int mWebim;//是否开启webim，0：不开启、1：开启

	public void setComment(int v){
			  		this.mComment = v;
			  }
		
			  public int getComment(){
					return this.mComment;
			  }
	public void setGeo(int v){
			  		this.mGeo = v;
			  }
		
			  public int getGeo(){
					return this.mGeo;
			  }
	public void setMessage(int v){
			  		this.mMessage = v;
			  }
		
			  public int getMessage(){
					return this.mMessage;
			  }
	public void setRealname(int v){
			  		this.mRealname = v;
			  }
		
			  public int getRealname(){
					return this.mRealname;
			  }
	public void setBadge(int v){
			  		this.mBadge = v;
			  }
		
			  public int getBadge(){
					return this.mBadge;
			  }
	public void setMobile(int v){
			  		this.mMobile = v;
			  }
		
			  public int getMobile(){
					return this.mMobile;
			  }
	public void setWebim(int v){
			  		this.mWebim = v;
			  }
		
			  public int getWebim(){
					return this.mWebim;
			  }

}
class Remind(消息未读数){

	private int mStatus;//新微博未读数
	private int mFollower;//新粉丝数
	private int mCmt;//新评论数
	private int mDm;//新私信数
	private int mMentionStatus;//新提及我的微博数
	private int mMentionCmt;//新提及我的评论数
	private int mGroup;//微群消息未读数
	private int mPrivateGroup;//私有微群消息未读数
	private int mNotice;//新通知未读数
	private int mInvite;//新邀请未读数
	private int mBadge;//新勋章数
	private int mPhoto;//相册消息未读数
	private int mMsgbox;//{{{3}}}

	public void setStatus(int v){
			  		this.mStatus = v;
			  }
		
			  public int getStatus(){
					return this.mStatus;
			  }
	public void setFollower(int v){
			  		this.mFollower = v;
			  }
		
			  public int getFollower(){
					return this.mFollower;
			  }
	public void setCmt(int v){
			  		this.mCmt = v;
			  }
		
			  public int getCmt(){
					return this.mCmt;
			  }
	public void setDm(int v){
			  		this.mDm = v;
			  }
		
			  public int getDm(){
					return this.mDm;
			  }
	public void setMentionStatus(int v){
			  		this.mMentionStatus = v;
			  }
		
			  public int getMentionStatus(){
					return this.mMentionStatus;
			  }
	public void setMentionCmt(int v){
			  		this.mMentionCmt = v;
			  }
		
			  public int getMentionCmt(){
					return this.mMentionCmt;
			  }
	public void setGroup(int v){
			  		this.mGroup = v;
			  }
		
			  public int getGroup(){
					return this.mGroup;
			  }
	public void setPrivateGroup(int v){
			  		this.mPrivateGroup = v;
			  }
		
			  public int getPrivateGroup(){
					return this.mPrivateGroup;
			  }
	public void setNotice(int v){
			  		this.mNotice = v;
			  }
		
			  public int getNotice(){
					return this.mNotice;
			  }
	public void setInvite(int v){
			  		this.mInvite = v;
			  }
		
			  public int getInvite(){
					return this.mInvite;
			  }
	public void setBadge(int v){
			  		this.mBadge = v;
			  }
		
			  public int getBadge(){
					return this.mBadge;
			  }
	public void setPhoto(int v){
			  		this.mPhoto = v;
			  }
		
			  public int getPhoto(){
					return this.mPhoto;
			  }
	public void setMsgbox(int v){
			  		this.mMsgbox = v;
			  }
		
			  public int getMsgbox(){
					return this.mMsgbox;
			  }

}
class UrlShort{

	private String mUrlShort;//短链接
	private String mUrlLong;//原始长链接
	private int mType;//链接的类型，0：普通网页、1：视频、2：音乐、3：活动、5、投票
	private boolean mResult;//短链的可用状态，true：可用、false：不可用。

	public void setUrlShort(String v){
			  		this.mUrlShort = v;
			  }
		
			  public String getUrlShort(){
					return this.mUrlShort;
			  }
	public void setUrlLong(String v){
			  		this.mUrlLong = v;
			  }
		
			  public String getUrlLong(){
					return this.mUrlLong;
			  }
	public void setType(int v){
			  		this.mType = v;
			  }
		
			  public int getType(){
					return this.mType;
			  }
	public void setResult(boolean v){
			  		this.mResult = v;
			  }
		
			  public boolean getResult(){
					return this.mResult;
			  }

}
class Geo{

	private String mLongitude;//经度坐标
	private String mLatitude;//维度坐标
	private String mCity;//所在城市的城市代码
	private String mProvince;//所在省份的省份代码
	private String mCityName;//所在城市的城市名称
	private String mProvinceName;//所在省份的省份名称
	private String mAddress;//所在的实际地址，可以为空
	private String mPinyin;//地址的汉语拼音，不是所有情况都会返回该字段
	private String mMore;//更多信息，不是所有情况都会返回该字段

	public void setLongitude(String v){
			  		this.mLongitude = v;
			  }
		
			  public String getLongitude(){
					return this.mLongitude;
			  }
	public void setLatitude(String v){
			  		this.mLatitude = v;
			  }
		
			  public String getLatitude(){
					return this.mLatitude;
			  }
	public void setCity(String v){
			  		this.mCity = v;
			  }
		
			  public String getCity(){
					return this.mCity;
			  }
	public void setProvince(String v){
			  		this.mProvince = v;
			  }
		
			  public String getProvince(){
					return this.mProvince;
			  }
	public void setCityName(String v){
			  		this.mCityName = v;
			  }
		
			  public String getCityName(){
					return this.mCityName;
			  }
	public void setProvinceName(String v){
			  		this.mProvinceName = v;
			  }
		
			  public String getProvinceName(){
					return this.mProvinceName;
			  }
	public void setAddress(String v){
			  		this.mAddress = v;
			  }
		
			  public String getAddress(){
					return this.mAddress;
			  }
	public void setPinyin(String v){
			  		this.mPinyin = v;
			  }
		
			  public String getPinyin(){
					return this.mPinyin;
			  }
	public void setMore(String v){
			  		this.mMore = v;
			  }
		
			  public String getMore(){
					return this.mMore;
			  }

