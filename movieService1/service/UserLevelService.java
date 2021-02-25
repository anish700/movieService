package movieService1.service;

import movieService1.exceptions.AlreadyReviewedException;
import movieService1.exceptions.MovieExistsException;
import movieService1.exceptions.UserExistsException;
import movieService1.exceptions.UserLevelNotFoundException;
import movieService1.models.movieModel;
import movieService1.models.reviewModel;
import movieService1.models.userModel;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.List;

public class UserLevelService {
    private UserService userService;
    private static final Map<String, Integer> UserLevelMap;
    
    public UserLevelService(UserService userService) {
        this.userService = userService;
       
    }
    static {
    UserLevelMap = new HashMap<>();
    UserLevelMap.put("viewer", 1);
    UserLevelMap.put("critic", 2);
    UserLevelMap.put("expert", 3);
    UserLevelMap.put("admin", 4);
    }

    private static final Map<String, String> levelUpgradation;
    static  {
        levelUpgradation = new HashMap<>();
        levelUpgradation.put("viewer", "critic");
        levelUpgradation.put("critic", "expert");
        levelUpgradation.put("expert", "admin");
        levelUpgradation.put("admin", "admin");
    }
    private static final Map<String, Integer> levelLimit;
    static {
        levelLimit = new HashMap<>();
        levelLimit.put("viewer", 0);
        levelLimit.put("critic", 3);
        levelLimit.put("expert", 6);
        levelLimit.put("admin", 12);
    }
    public void userLevelUpdate(String userName) throws Exception {
        this.userService.incrementReviews(userName);
        userModel user = this.userService.getUser(userName);

        String nextLevel = levelUpgradation.get(user.getUserLevel());
        Integer currentReviews = user.getReviewsCount();
        Integer nextProfileReviews = levelLimit.get(nextLevel);
        if (currentReviews >= nextProfileReviews) {
            user.setUserLevel(nextLevel);
        }
    }

    public Integer getWeightage(String userLevel) throws Exception {
        if (!UserLevelMap.containsKey(userLevel)) {
            throw new UserLevelNotFoundException(" no such user level  ");
        }
        return UserLevelMap.get(userLevel);
    }
}
