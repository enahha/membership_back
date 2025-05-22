/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.starinc.api.v1.common.mapper;

import java.util.List;

import io.starinc.api.v1.common.vo.FileVo;

public interface FileMapper {
	
	public List<FileVo> selectFileList(String seqFileMst) throws Exception;
	
	public FileVo selectFile(FileVo fileVo) throws Exception;
	
	public int insertFile(FileVo fileVo) throws Exception;
	
	public int updateFile(FileVo fileVo) throws Exception;
	
	public int deleteFile(FileVo fileVo) throws Exception;
	
}
