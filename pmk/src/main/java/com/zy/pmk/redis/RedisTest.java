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
