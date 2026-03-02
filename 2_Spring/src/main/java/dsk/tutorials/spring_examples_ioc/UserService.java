package dsk.tutorials.spring_examples_ioc;

public class UserService implements IUserService {
    private IUserDAO dao;

    public UserService() {
        this.dao = new UserDAO("127.0.0.1:80");
    }
}
