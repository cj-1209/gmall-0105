package com.icj.gmall.manage.controller;

public class TestFdfs {

    public static void main(String[] args) {

        String originalFilename = "123.125.12.s.jpg";
        int i1 = originalFilename.lastIndexOf(".");
        String extName = originalFilename.substring(i1 + 1);
        System.out.println(extName);
    }
}
