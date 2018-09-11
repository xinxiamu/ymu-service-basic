package com.ymu.servicecommon.vo.resp;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@EqualsAndHashCode
public class VAreaResp implements Serializable,Cloneable {

    private Long       id;
    private String     code;
    private BigDecimal lat;
    private Short      level;
    private BigDecimal lng;
    private String     mergerName;
    private String     name;
    private String     pinyin;
    private String     shortName;
    private String     telCode;
    private String     zipCode;
    private Long       parentId;

    private VAreaResp parent;
    private List<VAreaResp> children;

}
