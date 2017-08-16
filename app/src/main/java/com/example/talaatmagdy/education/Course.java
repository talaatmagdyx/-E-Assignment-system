package com.example.talaatmagdy.education;

/**
 * Created by talaatmagdy on 5/22/17.
 */

public class Course {
    String course_id;
    String course_name;
    String course_number;
    String course_point;
    String course_semester;

    public Course()
    {

    }

    public Course(String course_id,
                  String course_name, String course_number, String course_point, String course_semester) {
        this.course_id = course_id;
        this.course_name = course_name;
        this.course_number = course_number;
        this.course_point = course_point;
        this.course_semester = course_semester;
    }

  public String getCourse_id() {
        return course_id;
    }


    public String getCourse_name() {
        return course_name;
    }

    public String getCourse_number() {
        return course_number;
    }

    public String getCourse_point() {
        return course_point;
    }

    public String getCourse_semester() {
        return course_semester;
    }
}
