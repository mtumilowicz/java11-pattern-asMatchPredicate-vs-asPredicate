import org.junit.Test;

import java.util.function.Predicate;
import java.util.regex.Pattern;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by mtumilowicz on 2019-01-25.
 */
public class PatternPredicateTest {
    
    @Test
    public void asMatchPredicate() {
        Predicate<String> validator = Pattern.compile("[a-z]").asMatchPredicate();
        
        assertFalse(validator.test("1"));
        assertTrue(validator.test("a"));
        assertFalse(validator.test("111a"));
    }

    @Test
    public void asPredicate() {
        Predicate<String> validator = Pattern.compile("[a-z]").asPredicate();

        assertFalse(validator.test("1"));
        assertTrue(validator.test("a"));
        assertTrue(validator.test("111a"));
    }
}
