package com.ikutarian.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ikutarian.pojo.User;

public interface UserService extends IService<User> {

    /**
     * 判断用户名是否存在
     */
    boolean queryUsernameIsExist(String username);
}
