package com.ymu.servicefileclient.service;

import java.util.ArrayList;
import java.util.List;

public interface Html2pdfService {

    /**
     * freemarker模板渲染数据并转换成pdf。
     * @param ftlBase64 freemarker 模板
     * @param ftlData  模板对应数据
     * @return pdf文件所在url
     */
    String ftl2pdf(String ftlBase64,Object ftlData);

    /**
     * 单个或者多个html文件转成一个pdf。合并html生成pdf。
     * @param fileBase64 最终要生产pdf的html文件base64字符串，可多个
     * @return 返回文件可请求的url
     */
    String html2pdf(String...fileBase64);

    /**
     * 多个html文件转成多个pdf。
     * @param filesBase64 多个html文件的base64表示
     * @return 返回转成pdf的多个url。按顺序
     */
    List<String> html2pdf(ArrayList<String> filesBase64);
}
