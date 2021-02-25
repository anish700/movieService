package movieService1.service;

import movieService1.exceptions.UserExistsException;
import movieService1.models.movieModel;
import movieService1.models.userModel;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.List;


public class UserService {
    private static final Map<String, userModel> users = new HashMap<>();


    private void checkNewUser(String userName) throws UserExistsException {
        if (users.containsKey(userName)) {
            throw new UserExistsException("user already exists");
        }
    }
    // public userModel getProfile() {
    //     this.checkUserExists(userName);
    //     return users.get(userName);
	// }


    public void checkUserExists(String userName) throws UserExistsException{
        if (!users.containsKey(userName)){
            throw new UserExistsException("user does not exist");
        }
       }

       // -------- ADD USER ------------
    public userModel AddUser(String userName) throws UserExistsException {
        this.checkNewUser(userName);
        userModel user = new userModel(userName, "viewer"); // level=viewer by default
        users.put(userName, user);
        System.out.println(users);

        return user;
    }
    public userModel getUserFromName(String userName) throws UserExistsException {
        this.checkUserExists(userName);
        return users.get(userName);

       }
       public userModel getUser(String userName) throws Exception {
        this.checkUserExists(userName);
        return users.get(userName);
    }
       public void incrementReviews(String userName) throws Exception {
        this.checkUserExists(userName);
        userModel user = users.get(userName);
        user.add_review();
        users.put(userName, user);
    }


}
