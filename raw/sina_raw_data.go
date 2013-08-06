-status
created_at	string	微博创建时间
id	int64	微博ID
mid	int64	微博MID
idstr	string	字符串型的微博ID
text	string	微博信息内容
source	string	微博来源
favorited	boolean	是否已收藏，true：是，false：否
truncated	boolean	是否被截断，true：是，false：否
in_reply_to_status_id	string	（暂未支持）回复ID
in_reply_to_user_id	string	（暂未支持）回复人UID
in_reply_to_screen_name	string	（暂未支持）回复人昵称
thumbnail_pic	string	缩略图片地址，没有时不返回此字段
bmiddle_pic	string	中等尺寸图片地址，没有时不返回此字段
original_pic	string	原始图片地址，没有时不返回此字段
geo	class Geo 地理信息字段 详细
user	class User 微博作者的用户信息字段 详细
retweeted_status	class Status 被转发的原微博信息字段，当该微博为转发微博时返回 详细
reposts_count	int	转发数
comments_count	int	评论数
attitudes_count	int	表态数
mlevel	int	暂未支持
visible	class Visible 微博的可见性及指定可见分组信息。该object中type取值，0：普通微博，1：私密微博，3：指定分组微博，4：密友微博；list_id为分组的组号
pic_urls	string[] 微博配图地址。多图时返回多图链接。无配图返回“[]”
-comment
created_at	string	评论创建时间
id	int64	评论的ID
text	string	评论的内容
source	string	评论的来源
user	class User 评论作者的用户信息字段 详细
mid	string	评论的MID
idstr	string	字符串型的评论ID
status	class Status 评论的微博信息字段 详细
reply_comment	class Comment 评论来源评论，当本评论属于对另一评论的回复时返回此字段
-user
id	int64	用户UID
idstr	string	字符串型的用户UID
screen_name	string	用户昵称
name	string	友好显示名称
province	int	用户所在省级ID
city	int	用户所在城市ID
location	string	用户所在地
description	string	用户个人描述
url	string	用户博客地址
profile_image_url	string	用户头像地址，50×50像素
profile_url	string	用户的微博统一URL地址
domain	string	用户的个性化域名
weihao	string	用户的微号
gender	string	性别，m：男、f：女、n：未知
followers_count	int	粉丝数
friends_count	int	关注数
statuses_count	int	微博数
favourites_count	int	收藏数
created_at	string	用户创建（注册）时间
following	boolean	暂未支持
allow_all_act_msg	boolean	是否允许所有人给我发私信，true：是，false：否
geo_enabled	boolean	是否允许标识用户的地理位置，true：是，false：否
verified	boolean	是否是微博认证用户，即加V用户，true：是，false：否
verified_type	int	暂未支持
remark	string	用户备注信息，只有在查询用户关系时才返回此字段
status	class Status 用户的最近一条微博信息字段 详细
allow_all_comment	boolean	是否允许所有人对我的微博进行评论，true：是，false：否
avatar_large	string	用户大头像地址
verified_reason	string	认证原因
follow_me	boolean	该用户是否关注当前登录用户，true：是，false：否
online_status	int	用户的在线状态，0：不在线、1：在线
bi_followers_count	int	用户的互粉数
lang	string	用户当前的语言版本，zh-cn：简体中文，zh-tw：繁体中文，en：英语
-privacy
comment	int	是否可以评论我的微博，0：所有人、1：关注的人、2：可信用户
geo	int	是否开启地理信息，0：不开启、1：开启
message	int	是否可以给我发私信，0：所有人、1：我关注的人、2：可信用户
realname	int	是否可以通过真名搜索到我，0：不可以、1：可以
badge	int	勋章是否可见，0：不可见、1：可见
mobile	int	是否可以通过手机号码搜索到我，0：不可以、1：可以
webim	int	是否开启webim， 0：不开启、1：开启
-remind(消息未读数)
status	int	新微博未读数
follower	int	新粉丝数
cmt	int	新评论数
dm	int	新私信数
mention_status	int	新提及我的微博数
mention_cmt	int	新提及我的评论数
group	int	微群消息未读数
private_group	int	私有微群消息未读数
notice	int	新通知未读数
invite	int	新邀请未读数
badge	int	新勋章数
photo	int	相册消息未读数
msgbox	int	{{{3}}}
-url_short
url_short	string	短链接
url_long	string	原始长链接
type	int	链接的类型，0：普通网页、1：视频、2：音乐、3：活动、5、投票
result	boolean	短链的可用状态，true：可用、false：不可用。
-geo
longitude	string	经度坐标
latitude	string	维度坐标
city	string	所在城市的城市代码
province	string	所在省份的省份代码
city_name	string	所在城市的城市名称
province_name	string	所在省份的省份名称
address	string	所在的实际地址，可以为空
pinyin	string	地址的汉语拼音，不是所有情况都会返回该字段
more	string	更多信息，不是所有情况都会返回该字段
