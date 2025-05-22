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

import io.starinc.api.v1.admin.vo.AdminAirdropTotalVo;
 
public interface AdminAirdropTotalMapper {
	// 에어드랍 신청 총 건수 및 데일리 건수 조회
	public List<AdminAirdropTotalVo> selectAdminAirdropTotalList() throws Exception;
}
