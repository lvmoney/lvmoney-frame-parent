package com.zhy.frame.demo.seckill.controller;/**
 * 描述:
 * 包名:com.zhy.demo.provider.controller
 * 版本信息: 版本1.0
 * 日期:2020/3/8
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.base.core.api.ApiResult;
import com.zhy.frame.cache.lock.service.ProdLockService;
import com.zhy.frame.cache.lock.vo.req.ProdLockInitReqVo;
import com.zhy.frame.cache.lock.vo.req.ProdLockStockReqVo;
import com.zhy.frame.cache.lock.vo.req.ProdLockUpdateReqVo;
import com.zhy.frame.cache.redis.service.SeckillService;
import com.zhy.frame.cache.redis.vo.SeckillProductVo;
import com.zhy.frame.cache.redis.vo.SeckillVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/3/8 17:40
 */
@RestController
public class DemoController {

    @Autowired
    SeckillService seckillService;
    @Autowired
    ProdLockService prodLockService;

    @GetMapping(value = "frame/seckill/save")
    public ApiResult<String> test() {
        SeckillProductVo seckillProductReqVo = new SeckillProductVo();
        seckillProductReqVo.setCode("10000");
        seckillProductReqVo.setCount(500);
        seckillService.save(seckillProductReqVo);
        return ApiResult.success("seckill save");
    }

    @GetMapping(value = "frame/seckill/reduce")
    public ApiResult<String> reduce() {
        SeckillVo seckillReqVo = new SeckillVo();
        seckillReqVo.setCode("10000");
        seckillReqVo.setNum(1);
        return ApiResult.success(seckillService.reduce(seckillReqVo));
    }


    @GetMapping(value = "frame/lock/save")
    public ApiResult<String> lockSave() {
        ProdLockInitReqVo prodLockInitReqVo = new ProdLockInitReqVo();
        prodLockInitReqVo.setExpire(18000L);
        prodLockInitReqVo.setStock(new HashMap() {{
            put("10000", 500);
        }});
        prodLockService.initLockStock(prodLockInitReqVo);
        return ApiResult.success("seckill save");
    }

    @GetMapping(value = "frame/lock/reduce")
    public ApiResult<String> lockReduce() {
        ProdLockStockReqVo prodLockStockReqVo = new ProdLockStockReqVo();
        prodLockStockReqVo.setExpire(18000L);
        prodLockStockReqVo.setNum(1);
        prodLockStockReqVo.setProdId("10000");
        return ApiResult.success(prodLockService.getStock(prodLockStockReqVo));
    }


    @GetMapping(value = "frame/seckill/add")
    public ApiResult<String> add() {
        SeckillVo seckillReqVo = new SeckillVo();
        seckillReqVo.setCode("10000");
        seckillReqVo.setNum(1);
        return ApiResult.success(seckillService.add(seckillReqVo));
    }


    @GetMapping(value = "frame/lock/add")
    public ApiResult<String> lockAdd() {
        ProdLockStockReqVo prodLockStockReqVo = new ProdLockStockReqVo();
        prodLockStockReqVo.setExpire(18000L);
        prodLockStockReqVo.setNum(-1);
        prodLockStockReqVo.setProdId("10000");
        return ApiResult.success(prodLockService.getStock(prodLockStockReqVo));
    }

}
