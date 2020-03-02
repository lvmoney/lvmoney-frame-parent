package com.zhy.frame.authentication.oauth2.center.vo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
public class UserInfo extends User {
    private static final long serialVersionUID = -1682227070901462452L;
    private long userId;
    private String nickname;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    private String gender;

    public UserInfo(long userId, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this(userId, username, password, true, true, true, true, authorities);
    }

    public UserInfo(long userId, String username, String password, boolean enabled,
                    boolean accountNonExpired, boolean credentialsNonExpired,
                    boolean accountNonLocked,
                    Collection<? extends GrantedAuthority> authorities)
            throws IllegalArgumentException {
        super(username, password, enabled, accountNonExpired,
                credentialsNonExpired, accountNonLocked, authorities);
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

}
