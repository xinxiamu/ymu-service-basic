package com.ymu.servicefileclientdomain;

import com.ymu.framework.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Table(schema = "文件存储信息表")
@Entity
public class FileStorage extends BaseEntity {

    @Column(columnDefinition = "varchar(150) comment '文件原始名称'")
    private String orgName;

    @Column(columnDefinition = "varchar(255) comment '文件在服务器存储名称'")
    private String storeName;

    @Column(nullable = false, columnDefinition = "varchar(10) comment '文件格式（后缀,带点号）'")
    private String suffix;

    @Column(nullable = false, columnDefinition = "varchar(80) comment '访问路径域名'")
    private String host;

    /**
     * 文件访问全路径：host+path
     */
    @Column(nullable = false, columnDefinition = "varchar(200) comment '访问路径'")
    private String path;

    @Column(columnDefinition = "varchar(255) comment '文件标签，描述'")
    private String tag;
}
