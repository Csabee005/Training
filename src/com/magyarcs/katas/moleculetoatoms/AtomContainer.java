package com.magyarcs.katas.moleculetoatoms;

import java.util.ArrayList;
import java.util.List;

public class AtomContainer {
    private static int containerCount = 0;
    private List<Atom> atoms = new ArrayList<>();
    private int maxContainers = 10;
    private int multiplier = 1;
    private AtomContainer container;

    public AtomContainer() {}

    public AtomContainer(List<Atom> atoms, int multiplier, AtomContainer container) {
        this.atoms = atoms;
        this.multiplier = multiplier;
        int maxContainers = 10;
        if (maxContainers > containerCount) {
            this.container = container;
            containerCount++;
        }
    }

    public AtomContainer(Atom atom) {
        atoms.add(atom);
    }

    public void addAtom(Atom atom, int level) {
        AtomContainer container = this;
        for (int i = 0; i < level; i++) {
            container = container.getContainer();
        }
        if (container == null) {
            container = new AtomContainer();
        }
        container.addAtom(atom);
    }

    public void addAtom(Atom atom) {
        atoms.add(atom);
    }

    public List<Atom> getAtoms() {
        return atoms;
    }

    public AtomContainer getContainer() {
        return container;
    }

    public AtomContainer getNthContainer(int level) {
        AtomContainer container = this;
        for (int i = 0; i < level; i++) {
            container = container.getContainer();
        }
        if (container == null) {
            container = new AtomContainer();
        }
        return container;
    }

    public void createNthContainer(int level) {
        AtomContainer container = this;
        for (int i = 0; i < level -1; i++) {
            container = container.getContainer();
        }
        container.setContainer(new AtomContainer());
        containerCount++;
    }

    public void setContainer(AtomContainer container) {
        this.container = container;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    public List<Atom> getEveryAtomUp(int level) {
        List<Atom> atoms = getAtoms();
        AtomContainer container = this;
        for (int i = level; i < containerCount; i++) {
            container = container.getContainer();
            if (container != null) {
                atoms.addAll(container.getAtoms());
            }
        }
        return atoms;
    }
}
