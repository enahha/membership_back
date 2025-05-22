package io.starinc.api.v1.airdroplog.vo;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class AirdropLogRequestVo {
    private List<AirdropLogVo> airdropLogList;
    private String message;
    private String signature;
    private String userAddress;
    private String uuid;
}
