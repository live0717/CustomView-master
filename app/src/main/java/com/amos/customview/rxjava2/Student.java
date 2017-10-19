package com.amos.customview.rxjava2;

import java.util.List;

/**
 * Project CustomView-master
 * Created by Amos
 * Created on 2017-10-19
 * Desc
 */

public class Student {
    public String name;//学生名字
    public int id;
    public List<Source> mSources;//每个学生的所有课程

    public Student(String name, int id, List<Source> sources) {
        this.name = name;
        this.id = id;
        mSources = sources;
    }
}


