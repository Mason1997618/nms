package cn.edu.uestc.platform.utils;

/**
 * 常量池，放置IP地址/Openstack网络地址/登陆的账户及其密码
 */
public class Constants {

	public static final String NOVA_FLAVOR = "2";// id=2, name=m1.small
	public static final String GLANCE_IMAGE = "cbeb615d-fae5-4df7-9fb6-5913fa4c63e9";// 虚拟机模板Router_v1.6
	
	public static final String DOCKER_FLAVOR = "1";// id=2, name=m1.small
	public static final String DOCKER_IMAGE = "f953a662-aa94-40a6-8761-bcb49c1497e0";//docker镜像模板zph/new:14.11

	public static final String LOGIN_URL = "http://10.0.0.11:5000/v3";
	public static final String ZPH_PROJECT_ID = "7e0ba2f4b7e74f0eb21fec7642d42544";// zph项目id
	public static final String ADMIN_PROJECT_ID = "7fbd6b38b12d4a0da7ec8214ddaa3b63";// admin项目id
	
	public static final String CONTROLLER_IP_ADDRESS = "10.0.0.11";// controller节点的IP
	
	public static final String DOCKER_NODE_IP_ADDRESS = "10.0.0.31";// docker节点的IP
	public static final String DOCKER_NODE_USERNAME = "compute2";// docker节点的IP
	
	public static final String VM_ZONE = "vm";
	public static final String DOCKER_ZONE = "docker";
	
	public static final String CONTROLLER_NODE_NAME = "controller";
}
