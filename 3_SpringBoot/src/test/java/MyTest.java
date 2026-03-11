import dsk.tutorials.springboot.MyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyTest {
    @MockitoBean
    private MyService myService;


    @Test
    public void exampleTest() {
        assertEquals(myService.getName(), "myService");
    }
}
