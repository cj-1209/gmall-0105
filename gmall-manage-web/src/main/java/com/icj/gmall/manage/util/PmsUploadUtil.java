package com.icj.gmall.manage.util;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class PmsUploadUtil {

    public static String uploadImage(MultipartFile multipartFile) {

        String imgUrl = "http://192.168.0.143/";
        //配置fdfs全局链接地址
        String tracker = PmsUploadUtil.class.getResource("/tracker.conf").getPath();//获得配置文件路径

        try {
            ClientGlobal.init(tracker);
        } catch (Exception e) {
            e.printStackTrace();
        }

        TrackerClient trackerClient = new TrackerClient();

        //获得一个trackerServer实例
        TrackerServer trackerServer = null;
        try {
            trackerServer = trackerClient.getTrackerServer();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //通过tracker获得一个Storage链接客户端
        StorageClient storageClient = new StorageClient(trackerServer, null);

        try {
            byte[] bytes = multipartFile.getBytes();//获得上传二进制对象

            //获得文件后缀名
            String originalFilename = multipartFile.getOriginalFilename();//a.jpg
            System.out.println(originalFilename);
            int i = originalFilename.lastIndexOf(".");
            String extName = originalFilename.substring(i + 1);

            String[] uploadInfos = storageClient.upload_file(bytes, extName, null);

            for (String uploadInfo : uploadInfos) {
                imgUrl += "/" + uploadInfo;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imgUrl;
    }
}
