package com.webproject.sppj.service;

import java.util.List;

public interface RedisUtil {
	
	/**
	 * 设置键值
	 * @param key
	 * @param value
	 * @return
	 */
	public String set(String key, String value);
	
	/**
	 * 设置键值和过期时间
	 * @param key
	 * @param value
	 * @param time
	 * @return
	 */
	public String set(String key, String value, Integer time);
	
	/**
	 * 根据键获取值
	 * @param key
	 * @return
	 */
	public String get(String key);
	
	/**
	 * 获取缓存数据库中的list集合
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public List<String> lrange(String key, Long start, Long end); 
	
	/**
	 * 键对应的值增加1,只对integer对象有效
	 * @param key
	 * @return
	 */
	public Long incr(String key);
	
	/**
	 * 根据键删除数据
	 * @param key
	 * @return
	 */
	public Long del(String key);
	
	/**
	 * 根据键设定过期时间
	 * @param key
	 * @param time
	 * @return
	 */
	public Long expire(String key, Integer time);

}
