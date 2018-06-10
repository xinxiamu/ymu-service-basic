package com.ymu.servicefileclient.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;
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
public interface FastDFSClientService {

    /**
     * 上传文件到fdfs服务器。
     * @param inputStream 文件流
     * @param fileName 文件名称,带后缀
     * @return
     */
    String uploadFile(InputStream inputStream, String fileName);

    String uploadFile(InputStream inputStream, String fileName,Map<String, String> metaList);

    /**
     * 上传文件到fdfs服务器。
     * @param fileBase64 文件base64字符串
     * @param fileName 文件名称
     * @return
     */
    String uploadFile(String fileBase64, String fileName);

    String uploadFile(String fileBase64, String fileName,Map<String, String> metaList);


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
