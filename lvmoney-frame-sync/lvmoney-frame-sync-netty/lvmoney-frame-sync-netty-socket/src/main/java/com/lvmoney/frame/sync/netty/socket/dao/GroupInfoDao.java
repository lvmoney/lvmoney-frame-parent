package com.lvmoney.frame.sync.netty.socket.dao;


import com.lvmoney.frame.sync.netty.socket.model.po.GroupInfo;

public interface GroupInfoDao {

    void loadGroupInfo();

    GroupInfo getByGroupId(String groupId);
}
