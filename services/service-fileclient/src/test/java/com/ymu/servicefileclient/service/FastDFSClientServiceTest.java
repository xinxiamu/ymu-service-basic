package com.ymu.servicefileclient.service;

import com.ymu.framework.utils.security.Base64Utils;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jooq.DSLContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FastDFSClientServiceTest  {

    private static final Logger logger = LogManager.getLogger(FastDFSClientServiceTest.class);

    @Autowired
    private FastDFSClientService fastDFSClientService;

    @Autowired
    private DSLContext jooqDsl;

    @Autowired
    @PersistenceContext(name = "ymuFile")
    protected EntityManager em;

    @Test
    public void uploadFileTest() throws IOException {
//        File file = new File("/home/mutian/Pictures/请求图片.png");
        File file = new File("/home/mutian/Desktop/hw-gys.xlsx");
        FileInputStream i = FileUtils.openInputStream(file);
//        String id = fastDFSClientService.uploadFile(i,"index.png");
        Map<String,String> meta = new HashMap<>();
        meta.put("fileName","请求图片");
        meta.put("w","test");
        String id = fastDFSClientService.uploadFile(i,"请求图片.xlsx",meta);
        logger.debug(">>>file-id:" + id);
    }

    @Test
    public void getFileMetadataTest() {
//        Map<String, String> meta = fastDFSClientService.getFileMetadata("group1/M00/00/00/wKgBpFshAxWAYucwAACiOz-c444220.png");
        Map<String, String> meta = fastDFSClientService.getFileMetadata("group1/M00/00/00/wKgBpFshBWKAMlsFAACiOz-c444416.png");
        logger.debug(">>>meta:" + meta);
    }

    @Test
    public void deleteFileTest() {
        int i = fastDFSClientService.deleteFile("group1/M00/00/00/wKgBpFshBWKAMlsFAACiOz-c444416.png");
        logger.debug(">>>delete i:" + i);
    }

    @Test
    public void downloadFileTest() {
        String filePath = "/home/mutian/dd.png";
        int i = fastDFSClientService.downloadFile("group1/M00/00/00/wKgBpFsiGciAcPSzAACiOz-c444104.png",filePath);
        logger.debug(">>>>file dowload:" + i);


    }

    @Test
    public void downloadFileAsInputStreamTest() throws IOException {
        String filePath = "/home/mutian/dd.png";
        InputStream d = fastDFSClientService.downloadFileAsInputStream("group1/M00/00/00/wKgBpFsiGciAcPSzAACiOz-c444104.png");
        FileUtils.copyToFile(d,new File(filePath));
    }

    @Test
    public void downloadFileAsBase64Test() {
        String filePath = "/home/mutian/Desktop/dd.xlsx";
        String base64 = fastDFSClientService.downloadFileAsBase64("group1/M00/00/00/wKgBpFsiJjeAIREwAAAx4MhtCAk74.xlsx");
        Base64Utils.generateFileByBase64Str(base64,filePath);
    }


}

