package com.icj.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.icj.gmall.bean.PmsProductImage;
import com.icj.gmall.bean.PmsProductInfo;
import com.icj.gmall.bean.PmsProductSaleAttr;
import com.icj.gmall.manage.util.PmsUploadUtil;
import com.icj.gmall.service.SpuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@CrossOrigin
public class SpuController {

    @Reference
    SpuService spuService;

    @RequestMapping("spuImageList")
    @ResponseBody
    public List<PmsProductImage> spuImageList(String spuId) {

        List<PmsProductImage> pmsProductImages = spuService.spuImageList(spuId);
        return pmsProductImages;
    }


    @RequestMapping("spuSaleAttrList")
    @ResponseBody
    public List<PmsProductSaleAttr> spuSaleAttrList(String spuId) {

        List<PmsProductSaleAttr> pmsProductSaleAttrs = spuService.spuSaleAttrList(spuId);
        return pmsProductSaleAttrs;
    }


    @RequestMapping("fileUpload")
    @ResponseBody
    public String fileUpload(@RequestParam("file") MultipartFile multipartFile) {

        //将图片或者音视频上传到分布式文件存储系统

        //将图片的存储路径返回给页面
        String imgUrl = PmsUploadUtil.uploadImage(multipartFile);
        System.out.println(imgUrl);
        return imgUrl;
    }

    @RequestMapping("saveSpuInfo")
    @ResponseBody
    public String saveSpuInfo(@RequestBody PmsProductInfo pmsProductInfo) {

        spuService.saveSpuInfo(pmsProductInfo);
        return "success";
    }


    @RequestMapping("spuList")
    @ResponseBody
    public List<PmsProductInfo> spuList(String cataLog3Id) {

        List<PmsProductInfo> pmsProductInfos = spuService.spuList(cataLog3Id);

        return pmsProductInfos;
    }
}
