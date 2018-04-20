package com.ymu.servicefileclient.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Map;

/**
 * 功能简述:<br>
 * FastDFS文件系统客户端接口使用类。<br>
 * 用在小文件存储。
 *
 * @author mutian
 * @create 2018-02-10 下午4:01
 * @since 1.0.0
 */
@Service
public interface IFastDFSClientService {

    /**
     * 功能描述: <br>
     * 上传文件
     *
     * @param file
     * @param fileName
     * @return: java.lang.String
     * @since: 1.0.0
     * @author: zmt
     * @date: 18-2-10 下午4:05
     */
    String uploadFile(File file, String fileName);

    /**
     * 功能描述: <br>
     * 上传文件。
     *
     * @param file
     * @param fileName
     * @param metaList
     * @return: java.lang.String
     * @since: 1.0.0
     * @author: zmt
     * @date: 18-2-10 下午4:06
     */
    String uploadFile(File file, String fileName, Map<String, String> metaList);

    /**
     * 功能描述: <br>
     * 获取文件元数据
     *
     * @param fileId 文件ID
     * @return: java.util.Map<java.lang.String , java.lang.String>
     * @since: 1.0.0
     * @author: zmt
     * @date: 18-2-10 下午4:17
     */
    Map<String, String> getFileMetadata(String fileId);

    /**
     * 功能描述: <br>
     * 删除文件
     *
     * @param fileId 文件ID
     * @return: int  删除失败返回-1，否则返回0
     * @since: 1.0.0
     * @author: zmt
     * @date: 18-2-10 下午4:18
     */
    int deleteFile(String fileId);

    /**
     * 功能描述: <br>
     * 下载文件
     *
     * @param fileId  文件ID（上传文件成功后返回的ID）
     * @param outFile 文件下载保存位置
     * @return: int
     * @since: 1.0.0
     * @author: zmt
     * @date: 18-2-10 下午4:18
     */
    int downloadFile(String fileId, File outFile);
}
