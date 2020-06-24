package com.cbr.study.batisplusdemo.service.impl;

import com.cbr.study.batisplusdemo.entity.User;
import com.cbr.study.batisplusdemo.dao.UserDao;
import com.cbr.study.batisplusdemo.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author bingo
 * @since 2020-04-06
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {


}
