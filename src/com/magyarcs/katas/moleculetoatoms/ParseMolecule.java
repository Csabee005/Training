package com.magyarcs.katas.moleculetoatoms;

import java.util.HashMap;
import java.util.Map;

public class ParseMolecule {
    public static Map<String,Integer> getAtoms(String formula) {
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
        for (int i = maxLevel; i >= 0; i--) {
            AtomContainer currentContainer = container.getNthContainer(i);
            AtomContainer upperContainer = currentContainer.getContainer();
            for (Atom atom : currentContainer.getAtoms()
                 ) {
                //"K4[ON(SO3)2]2"
                /*
                * Add together every atom in a bracket, add to current if already present
                * if a bracket has a multiplier, multiply the items
                * */
                if (valueMap.containsKey(atom.getName())) {
                    valueMap.put(atom.getName(), valueMap.get(atom.getName()) + atom.getValue());
                }
                else {
                    valueMap.put(atom.getName(), atom.getValue());
                }
            }
            if (currentContainer.getMultiplier() > 1 || upperContainer != null && upperContainer.getMultiplier() > 1) {
                if (upperContainer != null) {
                    for (Atom upperAtom : upperContainer.getAtoms()
                    ) {
                        if (valueMap.containsKey(upperAtom.getName())) {
                            valueMap.put(upperAtom.getName(), valueMap.get(upperAtom.getName()) * currentContainer.getMultiplier());
                        }
                    }
                }
                    for (Atom currentAtom : currentContainer.getAtoms()
                    ) {
                        if (valueMap.containsKey(currentAtom.getName())) {
                            if (upperContainer == null || !upperContainer.containsByName(currentAtom))
                            valueMap.put(currentAtom.getName(), currentAtom.getValue() * currentContainer.getMultiplier());
                        }
                }
            }
        }
        System.out.println(container);
        return valueMap;
    }

    private static void multiply(Atom atom, Integer multiplicationNumber) {
        atom.setValue(atom.getValue() * multiplicationNumber);
    }
}
