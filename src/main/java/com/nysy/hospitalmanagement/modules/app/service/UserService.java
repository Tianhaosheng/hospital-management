/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.nysy.hospitalmanagement.modules.app.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.nysy.hospitalmanagement.modules.app.entity.UserEntity;
import com.nysy.hospitalmanagement.modules.app.form.LoginForm;

/**
 * 用户
 *
 *
 */
public interface UserService extends IService<UserEntity> {

	UserEntity queryByMobile(String mobile);

	/**
	 * 用户登录
	 * @param form    登录表单
	 * @return        返回用户ID
	 */
	long login(LoginForm form);
}
