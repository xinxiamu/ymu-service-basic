package com.ymu.servicefileclient.controller;

import com.ymu.framework.base.BaseController;
import com.ymu.framework.utils.CmdExecUtils;
import com.ymu.framework.utils.PrintUtil;
import com.ymu.servicefileclient.api.FastDFSClientApi;
import com.ymu.servicefileclient.client.service.common.TestClient;
import com.ymu.servicefileclient.service.FastDFSClientService;
import com.ymu.servicefileclient.vo.resp.VFileStorageResp;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestController
public class FastDFSClientController extends BaseController implements FastDFSClientApi {

    @Autowired
    private FastDFSClientService fastDFSClientService;

    @javax.annotation.Resource
    private TestClient testClient;

    @Autowired
    private MessageSource messageSource;

    @Override
    public List<VFileStorageResp> getAllFile() throws Exception {
        //调用common服务
        String result = testClient.test2("abc");
        logger.debug(">>>消费common组件：" + result);

        VFileStorageResp vFileResp = new VFileStorageResp();
        vFileResp.setOrgName(result + messageSource.getMessage("server.inner.error",null,Locale.getDefault()));
        vFileResp.setUrl("http://baidu.com/a.png");
        vFileResp.add(new Link("google.com").withSelfRel());
        vFileResp.add(new Link("ymu.com").withRel("ym"));

        List<VFileStorageResp> list = new ArrayList<>();
        list.add(vFileResp);
        list.add(vFileResp);

        return list;
    }

    @Value("${user.dir}")
    private String userDir;

    @Override
    public String getFileName(@PathVariable long id) throws Exception {
        URI uri = URI.create(userDir);
        PrintUtil.println(">>>uri:" + uri.getPath());
        File file = new File(uri + "/abc");
        if (!file.exists()) {
            file.mkdir();
        }
//        File file2 = new File("./" + userDir + "/abc");
//        PrintUtil.println(file2.getPath());
        return "a" + id + "==user_dir:" + userDir;
    }

    @Override
    public String getName() throws Exception {
        String classPath = getClass().getClassLoader().getResource("").getPath();

        //创建临时文件夹
        URI uri = URI.create(userDir);
        String tempDirPath = uri.getPath().concat(File.separator).concat("temp");
        File file = new File(tempDirPath);
        if (!file.exists()) {
            file.mkdir();
            logger.info(">>>成功创建临时文件夹temp：" + tempDirPath);
        }

        //拷贝wkhtmltopdf可执行文件到临时目录temp中
        String wkhtmltopdfPath = uri.getPath().concat(File.separator).concat("temp").concat(File.separator).concat("wkhtmltopdf");
        File wkhtmltopdf = new File(wkhtmltopdfPath);
        if (!wkhtmltopdf.exists()) {
            wkhtmltopdf.createNewFile();
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("bin/wkhtmltopdf");
            FileUtils.copyToFile(inputStream,wkhtmltopdf);
            CmdExecUtils.enableExe(wkhtmltopdfPath);
        }

        //拷贝html文件
        String htmlFile = tempDirPath.concat(File.separator).concat("abc.html");
        InputStream i = this.getClass().getClassLoader().getResourceAsStream("html2pdf/Purchase-Order.html");
        FileUtils.copyToFile(i,new File(htmlFile));

        //执行并输出pdf
        String pdfFilePath = tempDirPath.concat(File.separator).concat("导出.pdf");
        boolean flg = CmdExecUtils.execCommond(wkhtmltopdfPath,htmlFile,htmlFile,htmlFile,htmlFile,pdfFilePath);

        return "flg=" + flg + "==classpath:" + classPath + "==uri=" + uri;
    }

    @Override
    public String copyDirFromJar() throws Exception {
        return "abc";
    }



}
