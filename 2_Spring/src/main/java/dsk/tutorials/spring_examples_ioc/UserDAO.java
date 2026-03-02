package dsk.tutorials.spring_examples_ioc;

public class UserDAO implements IUserDAO {
    private String url;

    public UserDAO(String url) {
        this.url = url;
    }
}
