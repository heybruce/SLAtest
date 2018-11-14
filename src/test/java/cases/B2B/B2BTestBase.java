package cases.B2B;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

@ComponentScan(basePackages = { "utils", "b2b" })
@ContextConfiguration(classes = B2BTestBase.class)
public class B2BTestBase extends AbstractTestNGSpringContextTests {

}
