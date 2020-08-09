package com.icj.gmall.service;


import com.icj.gmall.bean.PmsBaseAttrInfo;
import com.icj.gmall.bean.PmsBaseAttrValue;
import com.icj.gmall.bean.PmsBaseSaleAttr;

import java.util.List;


public interface AttrService {
    List<PmsBaseAttrInfo> attrInfoList(String catalog3Id);

    String saveAttrInfo(PmsBaseAttrInfo pmsBaseAttrInfo);

    List<PmsBaseAttrValue> getAttrValueList(String attrId);

    List<PmsBaseSaleAttr> baseSaleAttrList();
}
