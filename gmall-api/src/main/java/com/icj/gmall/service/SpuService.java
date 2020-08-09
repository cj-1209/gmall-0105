package com.icj.gmall.service;

import com.icj.gmall.bean.PmsProductImage;
import com.icj.gmall.bean.PmsProductInfo;
import com.icj.gmall.bean.PmsProductSaleAttr;

import java.util.List;

public interface SpuService {

    List<PmsProductInfo> spuList(String cataLog3Id);

    void saveSpuInfo(PmsProductInfo pmsProductInfo);

    List<PmsProductSaleAttr> spuSaleAttrList(String spuId);

    List<PmsProductImage> spuImageList(String spuId);

    List<PmsProductSaleAttr> spuSaleAttrListCheckBySku(String productId,String skuId);
}
