package com.example.yitongshao.ad340v2;

import java.util.ArrayList;
public class additem {
    public static ArrayList<String> insert(String[][] bigList, int index) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < bigList.length; i++) {
            list.add(bigList[i][index]);
        }
        return list;
    }
}
