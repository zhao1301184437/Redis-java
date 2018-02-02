package com.qf.redis;

import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class Jedis_Test02 {


	public static void main(String[] args) {
		//strRedis();
		//listRedis();
		//setRedis();
		//zsetRedis();
		hashRedis();
		
	}
	
	private static String url="39.106.76.200";
	private static int port=6379;
	private static String pass="root";
	
	public static void strRedis() {
		Jedis jedis = new Jedis(url,port);
		jedis.auth(pass);
		jedis.set("student", "{'id':1,'name':'李四'}");
		
		String json = jedis.get("student");
		jedis.append("student2", "123");
		jedis.strlen("student");
		jedis.del("student2");
		jedis.expire("student",24*60*60);
		
		jedis.mset("stu2","222","stu3","333");
		
		jedis.mget("student");
		jedis.keys("*");
		jedis.close();	
		
	}
	
	//操作List
		private static void listRedis() {
			Jedis jedis=new Jedis(url,port);
			jedis.auth(pass);
			
			//从顶部添加
			jedis.lpush("list1", "bbb","cccc");
			//插入到指定索引处
			jedis.lset("list1", 0, "aaa");
			//从底部添加
			jedis.rpush("list1", "wwww","qqq");
			//从顶部移除
			System.out.println("从顶部移除："+jedis.lpop("list1"));
			//从底部移除
			System.out.println("从底部移除："+jedis.rpop("list1"));
			//获取长度
			jedis.llen("list1");
			//获取指定索引范围的内容
			List<String> ls=jedis.lrange("list1", 0,jedis.llen("list1"));
			System.out.println("查询全部："+ls);
			
			jedis.close();
		}
		//操作set
		private static void setRedis() {
			Jedis jedis=new Jedis(url,port);
			jedis.auth(pass);
			//新增
			jedis.sadd("set1", "aa","b","c");
			//获取全部内容
			Set<String> set1=jedis.smembers("set1");
			System.out.println("全部："+set1);
			//验证是否存在
			System.out.println("获取元素个数"+jedis.sismember("set1", "12"));
			//获取元素个数
			System.out.println("获取元素个数"+jedis.scard("set1"));
			
			
			jedis.close();
		}
		//操作Zset
		private static void zsetRedis() {
			Jedis jedis=new Jedis(url,port);
			jedis.auth(pass);
			//新增
			jedis.zadd("zset1", 10, "aaa");
			//元素个数
			jedis.zcard("zset1");
			//返回指定分数范围内的元素个数
			jedis.zcount("zset1", 0, 10);
			//指定元素的分数自增
			jedis.zincrby("zset1", 2, "aaa");
			//获取元素的索引，按照分数从小到大
			jedis.zrank("zset1", "aaa");
			//获取元素的索引，按照分数从大到小
			jedis.zrevrank("zset1", "aaa");
			
			
			jedis.close();
		}
		//操作Hash
		private static void hashRedis() {
			Jedis jedis=new Jedis(url,port);
			jedis.auth(pass);
		System.out.println(jedis.hset("hash1", "students", "200"));
			System.out.println(jedis.hexists("hash1", "students"));
			System.out.println(jedis.hgetAll("hash1"));
			
			
			jedis.close();
		}
}
