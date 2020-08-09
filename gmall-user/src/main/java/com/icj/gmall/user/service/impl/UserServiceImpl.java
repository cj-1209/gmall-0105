package com.icj.gmall.user.service.impl;


import com.icj.gmall.bean.UmsMember;
import com.icj.gmall.bean.UmsMemberReceiveAddress;
import com.icj.gmall.service.UserService;
import com.icj.gmall.user.mapper.UmsMemberReceiveAddressMapper;
import com.icj.gmall.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UmsMemberReceiveAddressMapper umsMemberReceiveAddressMapper;


    @Override
    public List<UmsMember> getAllUser() {
        List<UmsMember> umsMemberList = userMapper.selectAll();
        return umsMemberList;
    }

    @Override
    public List<UmsMemberReceiveAddress> getReceiveAddressByMemberId(String memberId) {
        //封装的参数对象
       /* UmsMemberReceiveAddress umsMemberReceiveAddress = new UmsMemberReceiveAddress();
        umsMemberReceiveAddress.setMemberId(memberId);
        List<UmsMemberReceiveAddress> umsMemberReceiveAddresses
         = umsMemberReceiveAddressMapper.select(umsMemberReceiveAddress);*/

        Example e = new Example(UmsMemberReceiveAddress.class);
        e.createCriteria().andEqualTo("memberId", memberId);
        List<UmsMemberReceiveAddress> umsMemberReceiveAddresses
                = umsMemberReceiveAddressMapper.selectByExample(e);

        return umsMemberReceiveAddresses;
    }
}
