package com.ymu.servicefileclient.service.impl;

import com.ymu.servicefileclient.service.Html2pdfService;

import java.util.ArrayList;
import java.util.List;

public class Html2pdfServiceImpl implements Html2pdfService {

    @Override
    public String ftl2pdf(String ftlBase64, Object ftlData) {
        //转换ftl模板文件放到临时文件夹

        //渲染ftl模板，保存为html放到临时文件夹

        //利用wkhtmltopdf把渲染后模板转换成pdf文件保存到临时文件夹

        //上传pdf文件到文件服务器

        //在file表记录文件信息

        //删除相关临时文件

        return null;
    }

    @Override
    public String html2pdf(String... fileBase64) {
        return null;
    }

    @Override
    public List<String> html2pdf(ArrayList<String> filesBase64) {
        return null;
    }
}
