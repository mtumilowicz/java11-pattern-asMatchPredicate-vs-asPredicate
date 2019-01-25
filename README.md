[![Build Status](https://travis-ci.com/mtumilowicz/java11-vavr-validation.svg?branch=master)](https://travis-ci.com/mtumilowicz/java11-vavr-validation)
# java11-pattern-asMatchPredicate-vs-asPredicate
Pattern: `asMatchPredicate()` vs `asPredicate()` investigation.

# project description
Since java 11 we have two methods in `Pattern` class transforming
pattern matching to a `Predicate`:
* `asMatchPredicate()` - Creates a predicate that tests if this 
pattern matches a given input string.
    ```
    public Predicate<String> asMatchPredicate() {
        return s -> matcher(s).matches();
    }
    ```
* `asPredicate()` - Creates a predicate that tests if this 
pattern is found in a given input string.
    ```
    public Predicate<String> asPredicate() {
        return s -> matcher(s).find();
    }
    ```
    **remark**: Note that `find()` returns true 
    if, and only if, a subsequence of the input 
    sequence matches this matcher's pattern
    
# tests
* `asMatchPredicate()`
    ```
    Predicate<String> validator = Pattern.compile("[a-z]").asMatchPredicate();
    
    assertFalse(validator.test("1"));
    assertTrue(validator.test("a"));
    assertFalse(validator.test("111a"));
    ```
* `asPredicate()`
    ```
    Predicate<String> validator = Pattern.compile("[a-z]").asPredicate();
    
    assertFalse(validator.test("1"));
    assertTrue(validator.test("a"));
    assertTrue(validator.test("111a"));
    ```
    
**Remark**: so the only difference between two methods mentioned
above is when it comes to substrings:
```
assertFalse(asMatchPredicate.test("111a"));
assertTrue(asPredicate.test("111a"));
```