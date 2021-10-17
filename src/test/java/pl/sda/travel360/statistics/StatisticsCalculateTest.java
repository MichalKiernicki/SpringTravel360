package pl.sda.travel360.statistics;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StatisticsCalculateTest {

    @Mock
    private Statistics statistics;

    @Test
    void shouldCalculeteStatisticWhenNumberOfPostIsZero(){
        //given
        StatisticsCalculate statisticsCalculate = new StatisticsCalculate();
        when(statistics.commentCount()).thenReturn(100);
        when(statistics.postsCount()).thenReturn(0);
        when(statistics.usersNames()).thenReturn(generateUsers(5));
        //when
        statisticsCalculate.calculateAdvStatistics(statistics);
        //then
        assertEquals(100,statisticsCalculate.getNumberOfComments());
        assertEquals(0,statisticsCalculate.getNumberOfPosts());
        assertEquals(5,statisticsCalculate.getNumberOfUsers());
        assertEquals(0.0,statisticsCalculate.getAvargeOfPostsOnUser());
        assertEquals(20.0,statisticsCalculate.getAvargeOfCommentsOnUser());
        assertEquals(0.0,statisticsCalculate.getAvargeOfCommentsOnPost());

    }
    @Test
    void shouldCalculate1000Posts(){
        //given
        StatisticsCalculate statisticsCalculate = new StatisticsCalculate();
        when(statistics.commentCount()).thenReturn(20);
        when(statistics.postsCount()).thenReturn(1000);
        when(statistics.usersNames()).thenReturn(generateUsers(10));
        //when
        statisticsCalculate.calculateAdvStatistics(statistics);
        //then
        assertEquals(20,statisticsCalculate.getNumberOfComments());
        assertEquals(1000,statisticsCalculate.getNumberOfPosts());
        assertEquals(10,statisticsCalculate.getNumberOfUsers());
        assertEquals(100.0,statisticsCalculate.getAvargeOfPostsOnUser());
        assertEquals(2.0,statisticsCalculate.getAvargeOfCommentsOnUser());
        assertEquals(0.02,statisticsCalculate.getAvargeOfCommentsOnPost());
    }

    @Test
    void shouldCalculateZeroComments(){
        //given
        StatisticsCalculate statisticsCalculate = new StatisticsCalculate();
        when(statistics.commentCount()).thenReturn(0);
        when(statistics.postsCount()).thenReturn(10);
        when(statistics.usersNames()).thenReturn(generateUsers(5));
        //when
        statisticsCalculate.calculateAdvStatistics(statistics);
        //then
        assertEquals(0,statisticsCalculate.getNumberOfComments());
        assertEquals(10,statisticsCalculate.getNumberOfPosts());
        assertEquals(5,statisticsCalculate.getNumberOfUsers());
        assertEquals(2.0,statisticsCalculate.getAvargeOfPostsOnUser());
        assertEquals(0.0,statisticsCalculate.getAvargeOfCommentsOnUser());
        assertEquals(0.0,statisticsCalculate.getAvargeOfCommentsOnPost());
    }

    @Test
    void shouldCalculateWhenCommentBiggerPost() {
        //given
        StatisticsCalculate statisticsCalculate = new StatisticsCalculate();
        when(statistics.commentCount()).thenReturn(8000);
        when(statistics.postsCount()).thenReturn(1000);
        when(statistics.usersNames()).thenReturn(generateUsers(40));
        //when
        statisticsCalculate.calculateAdvStatistics(statistics);
        //then
        assertEquals(8000,statisticsCalculate.getNumberOfComments());
        assertEquals(1000,statisticsCalculate.getNumberOfPosts());
        assertEquals(40,statisticsCalculate.getNumberOfUsers());
        assertEquals(25.0,statisticsCalculate.getAvargeOfPostsOnUser());
        assertEquals(200.0,statisticsCalculate.getAvargeOfCommentsOnUser());
        assertEquals(8.0,statisticsCalculate.getAvargeOfCommentsOnPost());
    }

    @Test
    void shouldCalculateWhenCommentLowerPost() {
        //given
        StatisticsCalculate statisticsCalculate = new StatisticsCalculate();
        when(statistics.commentCount()).thenReturn(1000);
        when(statistics.postsCount()).thenReturn(8000);
        when(statistics.usersNames()).thenReturn(generateUsers(50));
        //when
        statisticsCalculate.calculateAdvStatistics(statistics);
        //then
        assertEquals(1000,statisticsCalculate.getNumberOfComments());
        assertEquals(8000,statisticsCalculate.getNumberOfPosts());
        assertEquals(50,statisticsCalculate.getNumberOfUsers());
        assertEquals(160.0,statisticsCalculate.getAvargeOfPostsOnUser());
        assertEquals(20.0,statisticsCalculate.getAvargeOfCommentsOnUser());
        assertEquals(0.125,statisticsCalculate.getAvargeOfCommentsOnPost());
    }

    @Test
    void shouldCalculateZeroUsers(){
        //given
        StatisticsCalculate statisticsCalculate = new StatisticsCalculate();
        when(statistics.commentCount()).thenReturn(100);
        when(statistics.postsCount()).thenReturn(10);
        when(statistics.usersNames()).thenReturn(generateUsers(0));
        //when
        statisticsCalculate.calculateAdvStatistics(statistics);
        //then
        assertEquals(100,statisticsCalculate.getNumberOfComments());
        assertEquals(10,statisticsCalculate.getNumberOfPosts());
        assertEquals(0,statisticsCalculate.getNumberOfUsers());
        assertEquals(0.0,statisticsCalculate.getAvargeOfPostsOnUser());
        assertEquals(0.0,statisticsCalculate.getAvargeOfCommentsOnUser());
        assertEquals(10.0,statisticsCalculate.getAvargeOfCommentsOnPost());
    }

    @Test
    void shouldCalculate100Users(){
        //given
        StatisticsCalculate statisticsCalculate = new StatisticsCalculate();
        when(statistics.commentCount()).thenReturn(10000);
        when(statistics.postsCount()).thenReturn(100);
        when(statistics.usersNames()).thenReturn(generateUsers(100));
        //when
        statisticsCalculate.calculateAdvStatistics(statistics);
        //then
        assertEquals(10000,statisticsCalculate.getNumberOfComments());
        assertEquals(100,statisticsCalculate.getNumberOfPosts());
        assertEquals(100,statisticsCalculate.getNumberOfUsers());
        assertEquals(1.0,statisticsCalculate.getAvargeOfPostsOnUser());
        assertEquals(100.0,statisticsCalculate.getAvargeOfCommentsOnUser());
        assertEquals(100.0,statisticsCalculate.getAvargeOfCommentsOnPost());
    }

    private List<String> generateUsers(int count){
        List<String> users = new ArrayList<>();
        for(int i=0; i < count; i++){
            users.add("user" + i);
        }
        return users;
    }

}