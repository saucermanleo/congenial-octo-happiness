package com.zy.pmk.redis;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.zy.pmk.pojo.User;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisTest {

	public static void main(String[] args) {
		
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(1024); // 可用连接实例的最大数目,如果赋值为-1,表示不限制.
		config.setMaxIdle(5); // 控制一个Pool最多有多少个状态为idle(空闲的)jedis实例,默认值也是8
		config.setMaxWaitMillis(1000 * 100); // 等待可用连接的最大时间,单位毫秒,默认值为-1,表示永不超时/如果超过等待时间,则直接抛出异常
		config.setTestOnBorrow(true); // 在borrow一个jedis实例时,是否提前进行validate操作,如果为true,则得到的jedis实例均是可用的
		JedisPool jedisPool= new JedisPool(config, "localhost",6379);
		Jedis jedis = jedisPool.getResource();
		
		RuntimeSchema<User> schema = RuntimeSchema.createFrom(User.class);
		User u = new User();
		u.setPassword("123456");
		u.setUsername("zy");
		byte[] bytes = ProtostuffIOUtil.toByteArray(u, schema,
                LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
		
		jedis.set("user".getBytes(), bytes);
		jedis.set("hello", "zy");
		
		User returnU = schema.newMessage();
		ProtostuffIOUtil.mergeFrom(bytes, returnU, schema);
		
		System.out.println(returnU);
		jedis.close();
		jedisPool.close();
	}	

}
