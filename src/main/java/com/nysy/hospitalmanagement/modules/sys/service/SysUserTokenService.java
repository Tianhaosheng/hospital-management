/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.nysy.hospitalmanagement.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nysy.hospitalmanagement.common.utils.R;
import com.nysy.hospitalmanagement.modules.sys.entity.SysUserTokenEntity;

/**
 * 用户Token
 *
 *
 */
public interface SysUserTokenService extends IService<SysUserTokenEntity> {

	/**
	 * 生成token
	 * @param userId  用户ID
	 */
	R createToken(long userId);

	/**
	 * 退出，修改token值
	 * @param userId  用户ID
	 */
	void logout(long userId);

}
