package com.magyarcs.katas.moleculetoatoms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ParseMolecule {
    public static Map<String,Integer> getAtoms(String formula) {
        /*int bracketNumber = 0;
        HashMap<Integer, ArrayList<Atom>> elements = new HashMap<>();
        HashMap<String, Atom> elementsByName = new HashMap<>();
        String previousCharacter = "";
        for (int i = 0; i < formula.length(); i++) {
            Character currentChar = formula.charAt(i);
            if (currentChar > 64 && currentChar < 91) {
                Atom newElement = new Atom(Character.toString(currentChar), bracketNumber, 1);
                elementsByName.put(newElement.getName(), newElement);
                if (elements.containsKey(bracketNumber)) {
                    elements.get(bracketNumber).add(newElement);
                } else {
                    elements.put(bracketNumber, new ArrayList<Atom>());
                    elements.get(bracketNumber).add(newElement);
                }

            }
            if (currentChar > 97 && currentChar < 123) {
                if (previousCharacter.equals("")) {
                    throw new IllegalArgumentException("Elements not present in correct form!");
                }
                else {
                    Atom toRemove = elementsByName.remove(previousCharacter);
                    elements.get(bracketNumber).remove(toRemove);
                    Atom newElement = new Atom(previousCharacter + currentChar, bracketNumber, 1);
                    elementsByName.put(newElement.getName(), newElement);
                    if (elements.containsKey(bracketNumber)) {
                        elements.get(bracketNumber).add(newElement);
                    } else {
                        elements.put(bracketNumber, new ArrayList<Atom>());
                        elements.get(bracketNumber).add(newElement);
                    }
                }
            }
            else if (currentChar == '(' || currentChar == '[' || currentChar == '{') {
                bracketNumber += 1;
            }
            else if (currentChar == ')' || currentChar == ']' || currentChar == '}') {
                if (i + 1 < formula.length())
                {
                    Integer multiplicationNumber =  (int) formula.charAt(i + 1) - 48;
                    if (elements.containsKey(bracketNumber)) {
                        for (Atom atom : elements.get(bracketNumber)
                        ) {
                            if (atom.getBracketLevel() == bracketNumber) {
                                multiply(atom, multiplicationNumber);
                            }
                        }
                    }
                }
                bracketNumber -= 1;
            }
            else if (currentChar > 48 && currentChar < 58) {
                Integer multiplicationNumber =  (int)currentChar - 48;
                if (elements.containsKey(bracketNumber)) {
                    for (Atom atom : elements.get(bracketNumber)
                    ) {
                        if (atom.getBracketLevel() == bracketNumber) {
                            multiply(atom, multiplicationNumber);
                        }
                    }
                }
            }
            previousCharacter = Character.toString(currentChar);
        }*/
        AtomContainer container = new AtomContainer();
        int containerLevel = 0;
        int maxLevel = containerLevel;
        String previousCharacter = "";
        for (int i = 0; i < formula.length(); i++) {
            char currentChar = formula.charAt(i);
            if (currentChar > 64 && currentChar < 91) {
                container.addAtom(new Atom(Character.toString(currentChar), 1), containerLevel);
            }
            if (currentChar > 97 && currentChar < 123) {
                if (previousCharacter.equals("")) {
                    throw new IllegalArgumentException("Elements not present in correct form!");
                }
                Atom atomToRemove = new Atom(previousCharacter, 1);
                container.getNthContainer(containerLevel).getAtoms().removeIf(atom -> atom.getName().equals(atomToRemove.getName()));
                Atom atomToInsert = new Atom(previousCharacter + Character.toString(currentChar), 1);
                container.getNthContainer(containerLevel).addAtom(atomToInsert);
            }
            else if (currentChar == '(' || currentChar == '[' || currentChar == '{') {
                containerLevel += 1;
                maxLevel = containerLevel;
                container.createNthContainer(containerLevel);
            }
            else if (currentChar == ')' || currentChar == ']' || currentChar == '}') {
                if (i + 1 < formula.length()) {
                    int multiplier = (int) formula.charAt(i + 1) - 48;
                    container.getNthContainer(containerLevel).setMultiplier(multiplier);
                }
                i += 1;
                containerLevel -= 1;
            }
            else if (currentChar > 48 && currentChar < 58) {
                Integer multiplicationNumber =  (int)currentChar - 48;
                for (Atom atom : container.getNthContainer(containerLevel).getAtoms()
                     ) {
                    if (atom.getName().contains(previousCharacter)) {
                        multiply(atom, multiplicationNumber);
                    }
                }
            }
            previousCharacter = Character.toString(currentChar);
        }

        HashMap<String, Integer> valueMap = new HashMap<>();
        for (int i = 0; i <= maxLevel; i++) {
            //TODO Create summarizing method here
        }
        System.out.println(container);
        return new HashMap<String,Integer>();
    }

    private static void multiply(Atom atom, Integer multiplicationNumber) {
        atom.setValue(atom.getValue() * multiplicationNumber);
    }
}
