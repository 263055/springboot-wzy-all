package com.example.springbootredisson.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springbootredisson.dto.Result;
import com.example.springbootredisson.entity.ShopType;
import com.example.springbootredisson.mapper.ShopTypeMapper;
import com.example.springbootredisson.service.IShopTypeService;
import com.example.springbootredisson.utils.RedisConstants;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@Service
public class ShopTypeServiceImpl extends ServiceImpl<ShopTypeMapper, ShopType> implements IShopTypeService {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Result getTypeList() {
        String typeKey = RedisConstants.CACHE_TYPE_KEY;
        //从redis中查询
        Long typeListSize = stringRedisTemplate.opsForList().size(typeKey);
        //redis存在数据
        if (typeListSize != null && typeListSize != 0) {
            List<String> typeJsonList = stringRedisTemplate.opsForList().range(typeKey, 0, typeListSize - 1);
            List<ShopType> typeList = new ArrayList<>();
            assert typeJsonList != null;
            for (String typeJson : typeJsonList) {
                typeList.add(JSONUtil.toBean(typeJson, ShopType.class));
            }
            return Result.ok(typeList);
        }
        //redis不存在数据 查询数据库
        List<ShopType> typeList = query().orderByAsc("sort").list();
        if (typeList == null) {
            //数据库不存在数据
            return Result.fail("发生错误");
        }
        //转换
        List<String> typeJsonList = new ArrayList<>();
        for (ShopType shopType : typeList) {
            typeJsonList.add(JSONUtil.toJsonStr(shopType));
        }
        //数据库存在数据 写入redis
        stringRedisTemplate.opsForList().rightPushAll(typeKey, typeJsonList);
        //返回数据
        return Result.ok(typeList);
    }
}