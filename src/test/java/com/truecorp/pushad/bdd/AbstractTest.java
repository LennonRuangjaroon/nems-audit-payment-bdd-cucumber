
package com.truecorp.pushad.bdd;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

/**
 * 
 * @author Lennon
 * 
 */

@SpringApplicationConfiguration(classes = TestApplication.class)
@ContextConfiguration(classes = TestApplication.class, loader = SpringApplicationContextLoader.class)
@ActiveProfiles("development,local")
public abstract class AbstractTest {
}
