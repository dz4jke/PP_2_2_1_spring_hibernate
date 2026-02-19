package hiber.service;

import hiber.model.User;

import java.util.List;

public interface UserService {
    void saveUser(User user);
    List<User> findAllUsers();
    User findUserByCarModelAndSeries(String model, int series);
}
