package dsk.tutorials.spring_examples_ioc;

public class UserServiceIoC implements IUserService {
    private IUserDAO dao;

    public UserServiceIoC(IUserDAO dao) {
        this.dao = dao;
    }
}
