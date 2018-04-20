package com.ymu.servicefileclient.config;

import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class MainConfig {

    @Value("${fdfs.tracker_servers}")
    private String trackerServers;
    @Value("${fdfs.connect_timeout}")
    private int connectTimeout;
    @Value("${fdfs.network_timeout}")
    private int networkTimeout;
    @Value("${fdfs.charset}")
    private String charset;
    @Value("${fdfs.http.tracker_http_port}")
    private int trackerHttpPort;
    @Value("${fdfs.http.anti_steal_token}")
    private boolean antiStealToken;
    @Value("${fdfs.http.secret_key}")
    private String secretKey;

    @Bean
    public StorageClient1 fdfsStorageClient1() throws IOException, MyException {
        ClientGlobal.initByTrackers(trackerServers);
        ClientGlobal.setG_connect_timeout(connectTimeout);
        ClientGlobal.setG_network_timeout(networkTimeout);
        ClientGlobal.setG_charset(charset);
        ClientGlobal.setG_tracker_http_port(trackerHttpPort);
        ClientGlobal.setG_anti_steal_token(antiStealToken);
        ClientGlobal.setG_secret_key(secretKey);
        System.out.println("ClientGlobal.configInfo() : " + ClientGlobal.configInfo());

        TrackerClient tc = new TrackerClient();

        TrackerServer ts = tc.getConnection();
        if (ts == null) {
            throw new NullPointerException("FastDFS获取TrackerServer连接失败!");
        }

        StorageServer ss = tc.getStoreStorage(ts);
        if (ss == null) {
            throw new NullPointerException("FastDFS获取StorageServer连接失败!");
        }

        StorageClient1 sc1 = new StorageClient1(ts, ss);
        return sc1;
    }

}
