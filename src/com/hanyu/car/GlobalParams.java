package com.hanyu.car;

/**
 * 全局变量 用于判断 登录 网络状态等 如有创建虚注释清楚
 * 
 * @author yang
 * 
 */
public class GlobalParams {

	/**
	 * 用户ID
	 */
	public static String memberId = "-1";
	/**
	 * 是否登陆
	 */
	public static boolean load = false;
	/**
	 * 是否是wap连接
	 */
	public static boolean isWap = false;
	/**
	 * 屏幕宽
	 */
	public static int screenWidth;
	/**
	 * 屏幕高
	 */
	public static int screenHeight;

	/**
	 * sessionId
	 */
	public static String sessionId = "";
	/**
	 * 手机号
	 */
	public static String mobileNo = "";
	/**
	 * 纬度
	 */
	public static Double latitude = 31.1806063083;
	/**
	 * 经度
	 */
	public static Double longitude = 121.1852003612;
	/**
	 * 时间差
	 */
	public static long timeDelay = 0;
	
	public static int shakeRing=0;

}
