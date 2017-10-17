package com.webproject.sppj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import com.webproject.sppj.service.RedisUtil;
import com.webproject.sppj.utils.FunctionExec;

@Service
public class RedisUtilImpl implements RedisUtil {
	
	@Autowired
	private ShardedJedisPool shardedJedisPool;

	public <V> V execute(FunctionExec<ShardedJedis, V> fun) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			return fun.callBack(jedis);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		shardedJedisPool.close();
		return null;
	}

	@Override
	public String set(final String key, final String value) {
		return execute(new FunctionExec<ShardedJedis, String>() {

			@Override
			public String callBack(ShardedJedis k) {
				return k.set(key, value);
			}
		});
	}

	@Override
	public String set(final String key, final String value, final Integer time) {
		return execute(new FunctionExec<ShardedJedis, String>() {
			@Override
			public String callBack(ShardedJedis k) {
				return k.setex(key, time, value);
			}
		});
	}

	@Override
	public String get(final String key) {
		return execute(new FunctionExec<ShardedJedis, String>() {

			@Override
			public String callBack(ShardedJedis k) {
				return k.get(key);
			}
		});
	}

	@Override
	public Long incr(final String key) {
		return execute(new FunctionExec<ShardedJedis, Long>() {

			@Override
			public Long callBack(ShardedJedis k) {
				return k.incr(key);
			}
		});
	}

	@Override
	public Long del(final String key) {
		return execute(new FunctionExec<ShardedJedis, Long>() {

			@Override
			public Long callBack(ShardedJedis k) {
				return k.del(key);
			}
		});
	}

	@Override
	public Long expire(final String key, final Integer time) {
		return execute(new FunctionExec<ShardedJedis, Long>() {

			@Override
			public Long callBack(ShardedJedis k) {
				return k.expire(key, time);
			}
		});
	}

	@Override
	public List<String> lrange(final String key, final Long start, final Long end) {
		return execute(new FunctionExec<ShardedJedis, List<String>>() {

			@Override
			public List<String> callBack(ShardedJedis k) {
				return k.lrange(key, start, end);
			}
		});
	}

}
