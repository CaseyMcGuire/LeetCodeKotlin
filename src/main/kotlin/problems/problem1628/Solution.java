package problems.problem1628;

import java.util.*;

abstract class Node {

  protected String value;

  public Node(String value) {
    this.value = value;
  }

  public abstract int evaluate();
};

class TerminalNode extends Node {

  public TerminalNode(String value) {
    super(value);
  }

  public int evaluate() {
    return Integer.parseInt(value);
  }
}

class OperatorNode extends Node {
  public Node left;
  public Node right;
  public OperatorNode(String value) {
    super(value);
  }

  public static Set<String> validOperands =
  new HashSet<>(
      Arrays.asList(new String[] {"+", "-", "*", "/"})
      );

  public int evaluate() {
    int leftValue = this.left.evaluate();
    int rightValue = this.right.evaluate();
    if (value.equals("+")) {
      return leftValue + rightValue;
    }
    else if (value.equals("-")) {
      return leftValue - rightValue;
    }
    else if (value.equals("*")) {
      return leftValue * rightValue;
    }
    else if (value.equals("/")) {
      return leftValue / rightValue;
    }
    else {
      throw new RuntimeException("Unknown operator: " + value);
    }
  }
}


/**
 * This is the TreeBuilder class.
 * You can treat it as the driver code that takes the postinfix input
 * and returns the expression tree represnting it as a Node.
 */

class TreeBuilder {
  Node buildTree(String[] postfix) {
    if (postfix.length == 0) {
      return null;
    }
    int[] i = new int[] { postfix.length - 1 };
    return buildTree(postfix, i);
  }

  public Node buildTree(String[] postfix, int[] i) {
    if (i[0] < 0) {
      return null;
    }
    String current = postfix[i[0]];
    i[0]--;
    if (OperatorNode.validOperands.contains(current)) {
      OperatorNode node = new OperatorNode(current);
      node.right = buildTree(postfix, i);
      node.left = buildTree(postfix, i);
      return node;
    }
    else {
      return new TerminalNode(current);
    }
  }
};