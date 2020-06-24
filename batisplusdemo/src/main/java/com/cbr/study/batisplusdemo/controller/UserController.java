package com.cbr.study.batisplusdemo.controller;


import com.cbr.study.batisplusdemo.dao.UserDao;
import com.cbr.study.batisplusdemo.entity.User;
import com.cbr.study.batisplusdemo.service.UserService;
import com.cbr.study.batisplusdemo.util.R;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author bingo
 * @since 2020-04-06
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private UserDao userDao;

    /**
     * 用户信息
     */
    @RequestMapping("/info/{userId}")
    @ResponseBody
    public R info(@PathVariable("userId") Long userId) {


        List<User> user = userService.getBaseMapper().selectList(null);

        user.forEach(System.out::println);

        return R.ok().put("userInfo", user);

    }
}

