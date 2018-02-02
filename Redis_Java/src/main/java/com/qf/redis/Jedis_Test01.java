package com.qf.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;

public class Jedis_Test01 {

	public static void main(String[] args) {
		
		JedisPoolConfig jpc;
		String url="10.0.127.235";
		int port=6379;
		//1、创建Redis的连接对象
		Jedis jedis=new Jedis(url,port);
		//2、密码认证
		jedis.auth("1715");
		//3、操作redis
		//String类型
		//新增或修改
		System.out.println("新增或设置："+jedis.set("dog", "狗"));
		//获取
		System.out.println("获取："+jedis.get("dog"));
		//长度
		System.out.println("长度："+jedis.strlen("dog"));
		
		
		
		
		//4、关闭
		jedis.close();
		
	}
}
