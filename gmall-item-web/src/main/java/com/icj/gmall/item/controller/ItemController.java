package com.icj.gmall.item.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.icj.gmall.bean.PmsProductSaleAttr;
import com.icj.gmall.bean.PmsProductSaleAttrValue;
import com.icj.gmall.bean.PmsSkuInfo;
import com.icj.gmall.bean.PmsSkuSaleAttrValue;
import com.icj.gmall.service.SkuService;
import com.icj.gmall.service.SpuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ItemController {

    @Reference
    SkuService skuService;

    @Reference
    SpuService spuService;

    @GetMapping("index")
    public String index(ModelMap modelMap) {

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add("循环数据" + i);
        }

        modelMap.put("hello", "hello thymeleaf");
        modelMap.put("list", list);
        modelMap.put("check", "1");
        return "index";
    }
    @GetMapping("{skuId}.html")
    public String item(@PathVariable String skuId,ModelMap map) {

        PmsSkuInfo pmsSkuInfo = skuService.getSkuById(skuId);

        //sku对象
        map.put("skuInfo", pmsSkuInfo);
        //销售属性列表
        List<PmsProductSaleAttr> pmsProductSaleAttrs
                = spuService.spuSaleAttrListCheckBySku(pmsSkuInfo.getProductId(),pmsSkuInfo.getId()
        );
        map.put("spuSaleAttrListCheckBySku", pmsProductSaleAttrs);

        //查询当前sku的其他aku集合的hash表
        Map<String, String> skuSaleAttrHash = new HashMap<>();
        List<PmsSkuInfo> pmsSkuInfos = skuService.getSkuSaleAttrValueListBySpu(pmsSkuInfo.getProductId());
        for (PmsSkuInfo skuInfo : pmsSkuInfos) {
            String k = "";
            String v = skuInfo.getId();
            List<PmsSkuSaleAttrValue> skuSaleAttrValueList = skuInfo.getSkuSaleAttrValueList();
            for (PmsSkuSaleAttrValue pmsSkuSaleAttrValue : skuSaleAttrValueList) {
                k += pmsSkuSaleAttrValue.getSaleAttrValueId() + "|";
            }
            skuSaleAttrHash.put(k, v);
        }
        //将sku的销售属性hash表放在页面
        String skuSaleAttrHashJsonStr = JSON.toJSONString(skuSaleAttrHash);
        map.put("skuSaleAttrHashJsonStr", skuSaleAttrHashJsonStr);
        return "item";
    }
}
