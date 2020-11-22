package io.gen;

import java.util.ArrayList;
import java.util.List;

public class CourseSchdule {

    public boolean canFinish(int numCourses, int[][] prerequisites){
        if (numCourses == 0 || prerequisites == null || prerequisites.length == 0){
            return true;
        }

        List<List<Integer>> courses = new ArrayList<List<Integer>>();
        for (int i = 0; i < numCourses; i++) {
            courses.add(new ArrayList<>());
        }

        for (int i = 0; i < prerequisites.length; i++) {
            courses.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }

        int[] visited = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (!dfs(i, courses, visited)){
                return false;
            }
        }
        return true;

    }

    private boolean dfs(int course, List<List<Integer>> courses, int[] visited){
        visited[course] = 1;
        List<Integer> eligibleCourses = courses.get(course);

        for (int i = 0; i < eligibleCourses.size(); i++) {
            int eligibleCourse = eligibleCourses.get(i).intValue();
            if (visited[eligibleCourse] == 1) return false;
            if (visited[eligibleCourse] == 0){
                if (!dfs(eligibleCourse, courses, visited)){
                    return false;
                }
            }
        }
        visited[course] = 2;
        return true;
    }
}
