package DataStructures.Stack;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;


/**
 * Test both stack implementations: internal list and internal array
 */
@RunWith(Parameterized.class)
public class StackTest {

  private Stack stack;

  @Parameterized.Parameters
  public static Collection stackImplementations() {
    return Arrays.asList(new Object[][] {
      { new ArrayStack() },
      { new LinkedStack() }
    });
  }

  public StackTest(Object obj) {
    this.stack = (Stack) obj;
  }

  @Before
  public void setUp() {
    this.clearStack();
  }

  private void clearStack() {
    while (this.stack.size() > 0) {
      this.stack.pop();
    }
  }

  /**
   * Pushes the elements of the array to the stack in the array's order
   * Tests size, search and peek methods
   * @param objects
   */
  private void fillAndCheckStack(Object[] objects) {

    for (int i = 0; i < objects.length; i++) {
      this.stack.push(objects[i]);

      assertEquals(this.stack.size(), i + 1); // check new size

      for (int j = 0; j <= i; j++) {
        // check new positions of all objects
        assertEquals(this.stack.search(objects[j]), i + 1 - j);
      }
      assertEquals(this.stack.peek(), objects[i]); // check new top object
    }
  }

  /**
   * Assumes that the passed object array contains the elements that were lastly
   * pushed onto the stack in the array's order
   * @param objects
   */
  private void popAllPassedElementsFromStackAndCheck(Object[] objects) {
    for (int i = 0; i < objects.length; i++) {
      assertEquals(this.stack.pop(), objects[objects.length - 1 - i]);
      assertEquals(this.stack.size(), objects.length - 1 - i);
    }
  }

  @Test
  public void addOneElementToStack() {
    fillAndCheckStack(new Object[]{10});
  }

  @Test
  public void addTwoElementsToStack() {
    fillAndCheckStack(new Object[]{27, 30});
  }

  @Test
  public void addMultipleElementsToStack() {
    fillAndCheckStack(new Object[]{17, 102, 50});
  }

  @Test
  public void popOnEmptyStack() {
    assertEquals(this.stack.pop(), null);
    assertEquals(this.stack.size(), 0);
  }

  @Test
  public void popOnSingleElementStack() {
    Object[] objects = new Object[]{34};

    fillAndCheckStack(objects);
    popAllPassedElementsFromStackAndCheck(objects);
  }

  @Test
  public void popOnMultipleElementStack() {
    Object[] objects = new Object[]{3, 304, 102, 11, 207, 190};

    fillAndCheckStack(objects);
    popAllPassedElementsFromStackAndCheck(objects);
  }

  @Test
  public void searchEmptyStack() {
    assertEquals(this.stack.search(11), -1);
  }

  @Test
  public void searchForNonExistingElement() {
    fillAndCheckStack(new Object[]{3, 304, 102, 11, 207, 190});
    assertEquals(this.stack.search(2), -1);
  }
}