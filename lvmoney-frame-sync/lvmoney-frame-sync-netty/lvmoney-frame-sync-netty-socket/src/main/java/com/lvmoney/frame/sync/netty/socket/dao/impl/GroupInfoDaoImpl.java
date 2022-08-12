package com.lvmoney.frame.sync.netty.socket.dao.impl;

import com.lvmoney.frame.sync.netty.socket.dao.GroupInfoDao;
import com.lvmoney.frame.sync.netty.socket.model.po.GroupInfo;
import com.lvmoney.frame.sync.netty.socket.model.po.UserInfo;
import com.lvmoney.frame.sync.netty.socket.util.Constant;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class GroupInfoDaoImpl implements GroupInfoDao {

    @Override
    public void loadGroupInfo() {
        UserInfo userInfo = new UserInfo("001", "Member001", "001", "/img/avatar/Member001.jpg");
        UserInfo userInfo2 = new UserInfo("002", "Member002", "002", "/img/avatar/Member002.jpg");
        UserInfo userInfo3 = new UserInfo("003", "Member003", "003", "/img/avatar/Member003.jpg");
        UserInfo userInfo4 = new UserInfo("004", "Member004", "004", "/img/avatar/Member004.jpg");
        UserInfo userInfo5 = new UserInfo("005", "Member005", "005", "/img/avatar/Member005.jpg");
        UserInfo userInfo6 = new UserInfo("006", "Member006", "006", "/img/avatar/Member006.jpg");
        UserInfo userInfo7 = new UserInfo("007", "Member007", "007", "/img/avatar/Member007.jpg");
        UserInfo userInfo8 = new UserInfo("008", "Member008", "008", "/img/avatar/Member008.jpg");
        UserInfo userInfo9 = new UserInfo("009", "Member009", "009", "/img/avatar/Member009.jpg");
        List<UserInfo> members = new ArrayList<UserInfo>();
        members.add(userInfo);
        members.add(userInfo2);
        members.add(userInfo3);
        members.add(userInfo4);
        members.add(userInfo5);
        members.add(userInfo6);
        members.add(userInfo7);
        members.add(userInfo8);
        members.add(userInfo9);
        GroupInfo groupInfo = new GroupInfo("01", "Group01", "/img/avatar/Group01.jpg", members);
        Constant.groupInfoMap.put(groupInfo.getGroupId(), groupInfo);
    }

    @Override
    public GroupInfo getByGroupId(String groupId) {
        return Constant.groupInfoMap.get(groupId);
    }

}
