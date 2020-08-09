package com.icj.gmall.manage;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@SpringBootTest
class GmallManageWebApplicationTests {


    @Test
    void contextLoads() throws IOException, MyException {

        //配置fdfs全局链接地址
        String tracker = GmallManageWebApplicationTests.class.getResource("/tracker.conf").getPath();//获得配置文件路径

        ClientGlobal.init(tracker);

        TrackerClient trackerClient = new TrackerClient();

        //获得一个trackerServer实例
        TrackerServer trackerServer = trackerClient.getTrackerServer();

        //通过tracker获得一个Storage链接客户端
        StorageClient storageClient = new StorageClient(trackerServer, null);

//        String orginalFilename="e://victor.jpg";
        String orginalFilename = "d:/Download/图片/yc.jpg";
        String[] upload_file = storageClient.upload_file(orginalFilename, "jpg", null);

        String url = "http://192.168.0.143/";
        for (String s : upload_file) {
            url += "/" + s;
        }
        /*for (int i = 0; i < upload_file.length; i++) {
            String s = upload_file[i];
            url +="/"+ s;
            System.out.println("s = " + s);
        }*/
        System.out.println(url);

    }

}
