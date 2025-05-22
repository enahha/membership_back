package io.starinc.api.v1.verifyedaddress.vo;

import io.starinc.api.v1.common.vo.CommonVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class VerifyedAddressVo extends CommonVo {
    private int seq;
    private String uuid;
    private String wallet_address;
    private String del_yn;
    private String reg_id;
    private String reg_time;
    private String mod_id;
    private String mod_time;
    private String del_id;
    private String del_time;

}
