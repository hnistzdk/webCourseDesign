import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zdk
 * @date 2021/12/12 17:25
 */
public class MyTest {
    private final Logger log = LoggerFactory.getLogger(MyTest.class);

    @Test
    public void test() {
        log.debug("测试-> {}", "abc");
        log.info("测试-> {}", "123");
    }
}
