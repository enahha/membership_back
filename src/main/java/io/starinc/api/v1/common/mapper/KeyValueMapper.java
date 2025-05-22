package io.starinc.api.v1.common.mapper;

import io.starinc.api.v1.common.vo.KeyValueVo;

public interface KeyValueMapper {
	// select keyValue
	public String selectKeyValue(String key) throws Exception;
	
	// select last_price_wtec
	public String selectKeyValueLastPriceWtec(String intervalSecond) throws Exception;
	
	// insert or update keyValue
	public int mergeKeyValue(KeyValueVo keyValueVo) throws Exception;
}
