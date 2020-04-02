package com.ikutarian.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ikutarian.mapper.UserMapper;
import com.ikutarian.pojo.User;
import com.ikutarian.pojo.bo.UserBo;
import com.ikutarian.service.UserService;
import com.ikutarian.util.Md5Utils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean queryUsernameIsExist(String username) {
        User user = this.getOne(new LambdaQueryWrapper<User>()
                .eq(User::getUsername, username));
        return user != null;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public User createUser(UserBo userBo) {
        User user = new User();
        user.setUsername(userBo.getUsername());
        user.setPassword(Md5Utils.getMd5Str(userBo.getPassword()));
        this.save(user);

        return user;
    }
}
