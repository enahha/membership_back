/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *	  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.starinc.api.v1.admin.mapper;

import java.util.List;

import io.starinc.api.v1.common.vo.CommonVo;
import io.starinc.api.v1.user.vo.UserVo;
 
public interface AdminUserMapper {
	
	// 사용자 리스트 조회
	public int selectAdminUserListLastPageNum(CommonVo commonVo) throws Exception;
	public List<UserVo> selectAdminUserList(CommonVo commonVo) throws Exception;
	
	// 사용자 조회
	public UserVo selectAdminUser(UserVo userVo) throws Exception;
}
