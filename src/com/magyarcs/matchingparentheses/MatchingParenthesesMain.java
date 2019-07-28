package com.magyarcs.matchingparentheses;

import java.util.Stack;

public class MatchingParenthesesMain {
    private static Stack<Character> stack = new Stack<Character>();
    public static void main(String[] args) {
        System.out.println(checkIfParenthesesMatch("([{}])"));
        System.out.println(checkIfParenthesesMatch("((()))((()))"));
        System.out.println(checkIfParenthesesMatch("([[[]]]){[{[{]{]}]["));
    }

    private static boolean checkIfParenthesesMatch(String string) {
        if (string == null || string.isEmpty()) {
            throw new IllegalArgumentException("String value must not be null or empty!");
        }
        for (int i = 0; i < string.length(); i++) {
             Character currentCharacter = string.charAt(i);
             if (currentCharacter == '(' || currentCharacter == '{' || currentCharacter == '[') {
                 stack.push(currentCharacter);
             }
             else if (currentCharacter == ')') {
                 Character starterCharacter = stack.pop();
                 if (starterCharacter != '(') {
                     return false;
                 }
             }
             else if (currentCharacter == '}') {
                 Character starterCharacter = stack.pop();
                 if (starterCharacter != '{') {
                     return false;
                 }
             }
             else if (currentCharacter == ']') {
                 Character starterCharacter = stack.pop();
                 if (starterCharacter != '[') {
                     return false;
                 }
             }
        }
        return stack.isEmpty();
    }
}
