package com.hanyu.car.http;

public interface HttpUrl {
	String SERVER = "http://www.hualip.com/web/zuche/api";
	String IMAGE_URL = "http://www.hualip.com/web/zuche/";
	String LOGIN = SERVER + "/login.php";// 登陆
	String REGISTER = SERVER + "/register.php";// 注册
	String LOST_PASSWORD = SERVER + "/forget.php";// 找回密码
	String CAR_BRAND = SERVER + "/brand.php";// 品牌列表
	String FAST_FIND = SERVER + "/fastfind.php";// 快速寻车提交
	String USER_MINE = SERVER + "/user.php";// 会员首页
	String UPDATE_USERNAME = SERVER + "/user_edit.php";// 会员昵称修改
	String USER_ORDER = SERVER + "/user_app.php";// 个人中心我的预约
	String UPDATE_PHONE = SERVER + "/edit_phone.php";// 个人中心修改手机号
	String UPDATE_PWD = SERVER + "/edit_pwd.php";// 个人中心修改密码
	String TRAVEL_LIST = SERVER + "/travel.php";// 旅行租车列表
	
	/*商务租车*/
	String BUSINESS_LIST = SERVER + "/business.php";// 商务租车列表
	String BUSINESS_CONTENT_INFO  = SERVER + "/ business_info.php";// 商务租车详情
	
	/*婚礼租车*/
	//1.婚车
	String PACKAGE_LIST = SERVER + "/packpage.php";// 婚车套餐列表
	String PACKAGE_CONTENT_INFO = SERVER + "/packpage_info.php";// 婚车套餐详情
	String PACKAGE_ORDER = SERVER + "/packpageapp.php";// 婚车套餐预约
	//2.豪车
	String LUXURY_PACKAGE_LIST = SERVER + "/mcar_luxury.php";// 豪华轿车列表
	String LUXURY_CONTENT_INFO = SERVER + "/mcar_luxury_info.php";// 豪华轿车详情
	String LUXURY_ORDER = SERVER + "/mcar_luxuryapp.php";// 豪华轿车预约
	//3.高端轿车
	String PREMIUM_PACKAGE_LIST = SERVER + "/mcar_grade.php";// 高端轿车列表
	String PREMIUM_CONTENT_INFO = SERVER + "/mcar_grade_info.php";// 高端轿车详情
	String PREMIUM_ORDER = SERVER + "/mcar_gradeapp.php";// 高端轿车详情
	//4.自定义预约
	String CUSTOM_ORDER = SERVER + "/mcarcustom.php";// 自定义预约
	
	/*长期租车*/
	//1.车辆列表
	String LONGTERM_CARLIST = SERVER + "/long.php";// 长期租车列表
	String LONGTERM_CONTENT_INFO = SERVER + "/long_info.php";// 长期租车详情
	String LONGTERM_ORDER = SERVER + "/mcar_gradeapp.php";// 长期租车预约
	//2.融资方案
	//3.我要预约
	
	String TRAVEL_SUBJECT = SERVER + "/travel_tuijian.php";// 旅游推荐
	String TRAVEL_INFO = SERVER + "/travel_info.php";// 旅游信息详情
	
}
