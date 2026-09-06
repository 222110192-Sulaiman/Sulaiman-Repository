package edu.psu.se411.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;
public class StackTest {

	@Test
	public void push_push_pop() {
		 Stack<String> stringStack = new Stack<>();

	        stringStack.push("Z");
	        stringStack.push("A");

	        assertEquals("A", stringStack.pop());
	    }
	@Test
	public void pop_empty_stack() {
		  Stack<String> stringStack = new Stack<>();
		NoSuchElementException thrown = 
assertThrows(NoSuchElementException.class,
		      () -> stringStack.pop(),
		      "Expected pop from empty Stack to throw, but it didn't"
		);

        assertTrue(thrown.getMessage().equals("Stack is empty, cannot pop"));

}
	@Test
	public void pop_reverse_order() {

	    Stack<String> stringStack = new Stack<>();

	    stringStack.push("A");
	    stringStack.push("B");
	    stringStack.push("C");

	    assertEquals("C", stringStack.pop());
	    assertEquals("B", stringStack.pop());
	    assertEquals("A", stringStack.pop());
	}

}


