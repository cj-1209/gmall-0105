package com.icj.gmall.user.service;

import com.icj.gmall.user.bean.UmsMember;
import com.icj.gmall.user.bean.UmsMemberReceiveAddress;

import java.util.List;

public interface UserService {

    List<UmsMember> getAllUser();

    List<UmsMemberReceiveAddress> getReceiveAddressByMemberId(String memberId);
}
