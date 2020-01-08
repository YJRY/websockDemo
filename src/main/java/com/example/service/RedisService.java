package com.example.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RedisService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    private ValueOperations<String, Object> valueOperations;
    private ListOperations<String, Object> listOperations;
    private SetOperations<String, Object> setOperations;
    private ZSetOperations<String, Object> zSetOperations;
    private HashOperations<String, Object, Object> hashOperations;

    @PostConstruct
    public void init() {
        valueOperations = redisTemplate.opsForValue();
        listOperations = redisTemplate.opsForList();
        setOperations = redisTemplate.opsForSet();
        hashOperations = redisTemplate.opsForHash();
        zSetOperations = redisTemplate.opsForZSet();
    }

    public <T> T get(String k, Class<T> clazz) {
        Object value = valueOperations.get(k);
        return JSONObject.parseObject(JSON.toJSONString(value), clazz);
    }

    public void set(String k, Object v) {
        valueOperations.set(k, v);
    }

    public void lPush(String k, Object v) {
        listOperations.leftPush(k, v);
    }

    public void rPush(String k, Object v) {
        listOperations.rightPush(k, v);
    }

    public <T> T lPop(String k, Class<T> clazz) {
        Object value = listOperations.leftPop(k);
        return JSONObject.parseObject(JSON.toJSONString(value), clazz);
    }

    public <T> T rPop(String k, Class<T> clazz) {
        Object value = listOperations.rightPop(k);
        return JSONObject.parseObject(JSON.toJSONString(value), clazz);
    }

    public<T>List<T> range(String k, int begin, int end, Class<T> clazz){
        List<Object> list = listOperations.range(k, begin, end);
        if (list != null && list.size() > 0){
            return list.stream().map(o -> JSONObject.parseObject(JSON.toJSONString(o), clazz))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public void trim(String k, int begin, int end){
        listOperations.trim(k, begin, end);
    }




}
