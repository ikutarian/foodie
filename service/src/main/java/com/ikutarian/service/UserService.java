package com.ikutarian.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ikutarian.pojo.User;
import com.ikutarian.pojo.bo.UserBo;

public interface UserService extends IService<User> {

    /**
     * 判断用户名是否存在
     */
    boolean queryUsernameIsExist(String username);

    /**
     * 创建用户
     */
    User createUser(UserBo userBo);
}
