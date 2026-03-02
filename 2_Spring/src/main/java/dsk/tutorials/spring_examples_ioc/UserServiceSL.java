package dsk.tutorials.spring_examples_ioc;

public class UserServiceSL implements IUserService {
    private IUserDAO dao;

    public UserServiceSL() {
        this.dao = ServiceLocator.getUserDAO();
    }
}
