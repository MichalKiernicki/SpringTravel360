package pl.sda.travel360.statistics;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class StatisticsCalculate {

    private int numberOfUsers;
    private int numberOfPosts;
    private int numberOfComments;
    private double avargeOfPostsOnUser;
    private double avargeOfCommentsOnUser;
    private double avargeOfCommentsOnPost;

    public void calculateAdvStatistics(Statistics statistics) {
        this.numberOfUsers = statistics.usersNames().size();
        this.numberOfPosts = statistics.postsCount();
        this.numberOfComments = statistics.commentCount();

        if(numberOfPosts == 0) {
            avargeOfCommentsOnPost = 0.0;
        } else {
            avargeOfCommentsOnPost = numberOfComments / (double) numberOfPosts;
        }

        if (numberOfUsers == 0) {
            avargeOfPostsOnUser = 0.0;
            avargeOfCommentsOnUser = 0.0;
        } else {
            avargeOfCommentsOnUser = numberOfComments / (double) numberOfUsers;
            avargeOfPostsOnUser = numberOfPosts / (double) numberOfUsers;
        }

    }
    public void showStatistics() {
        System.out.println(avargeOfPostsOnUser);
        System.out.println(avargeOfCommentsOnUser);
        System.out.println(avargeOfCommentsOnPost);

    }


}