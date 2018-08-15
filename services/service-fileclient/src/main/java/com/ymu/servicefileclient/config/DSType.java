package com.ymu.servicefileclient.config;

/**
 * 数据源类型。
 *
 * @author zmt
 */
public enum DSType {

    YMU_FILE_MASTER("文件存储信息主库"),
    YMU_FILE_SLAVE1("文件存储信息从库1");

    private final String value;

    DSType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
