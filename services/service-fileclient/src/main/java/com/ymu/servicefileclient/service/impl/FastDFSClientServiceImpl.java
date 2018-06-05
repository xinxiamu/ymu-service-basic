package com.ymu.servicefileclient.service.impl;

import com.ymu.servicefileclient.service.FastDFSClientService;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.StorageClient1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Service
public class FastDFSClientServiceImpl implements FastDFSClientService {

    private static final Logger logger = LogManager.getLogger(FastDFSClientServiceImpl.class);

    @Autowired
    private StorageClient1 fdfsStorageClient1;

    @Override
    public String uploadFile(File file, String fileName) {
        return uploadFile(file, fileName, null);
    }

    @Override
    public String uploadFile(File file, String fileName, Map<String, String> metaList) {
        try {
            byte[] buff = IOUtils.toByteArray(new FileInputStream(file));
            NameValuePair[] nameValuePairs = null;
            if (metaList != null) {
                nameValuePairs = new NameValuePair[metaList.size()];
                int index = 0;
                for (Iterator<Map.Entry<String, String>> iterator = metaList.entrySet().iterator(); iterator.hasNext(); ) {
                    Map.Entry<String, String> entry = iterator.next();
                    String name = entry.getKey();
                    String value = entry.getValue();
                    nameValuePairs[index++] = new NameValuePair(name, value);
                }
            }
            return fdfsStorageClient1.upload_file1(buff, FilenameUtils.getExtension(fileName), nameValuePairs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map<String, String> getFileMetadata(String fileId) {
        try {
            NameValuePair[] metaList = fdfsStorageClient1.get_metadata1(fileId);
            if (metaList != null) {
                HashMap<String, String> map = new HashMap<>();
                for (NameValuePair metaItem : metaList) {
                    map.put(metaItem.getName(), metaItem.getValue());
                }
                return map;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public int deleteFile(String fileId) {
        try {
            return fdfsStorageClient1.delete_file1(fileId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int downloadFile(String fileId, File outFile) {
        FileOutputStream fos = null;
        try {
            byte[] content = fdfsStorageClient1.download_file1(fileId);
            InputStream icontent = new ByteArrayInputStream(content);
            fos = new FileOutputStream(outFile);
            IOUtils.copy(icontent, fos);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return -1;
    }
}

