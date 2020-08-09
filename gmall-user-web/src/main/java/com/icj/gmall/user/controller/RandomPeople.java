package com.icj.gmall.user.controller;

import java.util.Random;

public class RandomPeople {

    public static void main(String[] args) {
        Random col = new Random();
        Random row = new Random(); //行

        int lie = col.nextInt(4) + 1; //从0开始

        int hang = row.nextInt(8) + 1;
        System.out.println("有请第" + lie + "列" + hang + "行");

    }
}
