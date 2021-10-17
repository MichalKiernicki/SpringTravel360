package pl.sda.travel360.statistics;

import java.util.List;

public interface Statistics {

    List<String> usersNames();
    int postsCount();
    int commentCount();
}
