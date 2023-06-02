package com.puralsight.courseinfo.cli;

public class CourseRetriever {

    public static void main(String... args) {
        System.out.println("CourseRetriever Started!");
        if (args.length == 0) {
            System.out.println("Please provide an author name as first argument.");
            return;
        }

        try {
            retrieveCourses(args[0]);
        } catch (Exception e) {
            System.out.println("Unexpected Error");
            e.printStackTrace();
        }
    }

    private static void retrieveCourses(String authorId) {
        System.out.println("Revrieving courses for author " + authorId);
    }
}
