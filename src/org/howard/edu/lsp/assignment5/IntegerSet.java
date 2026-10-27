package org.howard.edu.lsp.assignment5;

import java.util.Collections;
import java.util.Set;
import java.util.StringJoiner;
import java.util.TreeSet;

/**
 * A mathematical set of integers. Duplicates are never stored.
 * <p>
 * Elements are kept internally in a {@link TreeSet}, which keeps them sorted
 * and makes {@link #toString()}, {@link #largest()} and {@link #smallest()}
 * straightforward. The internal state is private and never exposed.
 * Every operation that returns an {@code IntegerSet} returns a new,
 * independent object and leaves both inputs unchanged.
 */
public class IntegerSet {

    /** Sorted storage for the elements of this set. */
    private final TreeSet<Integer> elements = new TreeSet<>();

    /** Creates an empty set. */
    public IntegerSet() {
    }

    /** Removes all elements from this set. */
    public void clear() {
        elements.clear();
    }

    /**
     * Returns the number of elements in this set.
     *
     * @return the count of distinct elements
     */
    public int length() {
        return elements.size();
    }

    /**
     * Compares this set with another for equality of contents, ignoring
     * insertion order.
     *
     * @param b the set to compare with
     * @return {@code true} if both sets contain exactly the same elements
     */
    public boolean equals(IntegerSet b) {
        if (this == b) {
            return true;
        }
        return b != null && elements.equals(b.elements);
    }

    /**
     * Compares this set with an arbitrary object. Equal only to another
     * {@code IntegerSet} with the same elements.
     *
     * @param o the object to compare with
     * @return {@code true} if {@code o} is an equal {@code IntegerSet}
     */
    @Override
    public boolean equals(Object o) {
        return o instanceof IntegerSet && equals((IntegerSet) o);
    }

    /**
     * Returns a hash code consistent with {@link #equals(Object)}.
     *
     * @return the hash code of this set's elements
     */
    @Override
    public int hashCode() {
        return elements.hashCode();
    }

    /**
     * Tests whether a value is in this set.
     *
     * @param value the value to look for
     * @return {@code true} if the value is present
     */
    public boolean contains(int value) {
        return elements.contains(value);
    }

    /**
     * Returns the largest element.
     *
     * @return the maximum value in the set
     * @throws IllegalStateException if the set is empty
     */
    public int largest() {
        if (elements.isEmpty()) {
            throw new IllegalStateException("Set is empty");
        }
        return elements.last();
    }

    /**
     * Returns the smallest element.
     *
     * @return the minimum value in the set
     * @throws IllegalStateException if the set is empty
     */
    public int smallest() {
        if (elements.isEmpty()) {
            throw new IllegalStateException("Set is empty");
        }
        return elements.first();
    }

    /**
     * Adds an item to this set. Duplicates are ignored.
     *
     * @param item the value to add
     */
    public void add(int item) {
        elements.add(item);
    }

    /**
     * Removes an item from this set. Has no effect if the item is absent.
     *
     * @param item the value to remove
     */
    public void remove(int item) {
        elements.remove(item);
    }

    /**
     * Returns the union of this set and {@code b}.
     *
     * @param b the other set
     * @return a new set with elements in either set
     */
    public IntegerSet union(IntegerSet b) {
        IntegerSet result = copyOf(elements);
        result.elements.addAll(b.elements);
        return result;
    }

    /**
     * Returns the intersection of this set and {@code b}.
     *
     * @param b the other set
     * @return a new set with elements present in both sets
     */
    public IntegerSet intersect(IntegerSet b) {
        IntegerSet result = copyOf(elements);
        result.elements.retainAll(b.elements);
        return result;
    }

    /**
     * Returns the difference {@code this - b}.
     *
     * @param b the set whose elements are excluded
     * @return a new set with elements in this set but not in {@code b}
     */
    public IntegerSet diff(IntegerSet b) {
        IntegerSet result = copyOf(elements);
        result.elements.removeAll(b.elements);
        return result;
    }

    /**
     * Returns the assignment-specific complement: elements in {@code b}
     * that are not in this set (that is, {@code b - this}).
     *
     * @param b the other set
     * @return a new set with elements in {@code b} but not in this set
     */
    public IntegerSet complement(IntegerSet b) {
        IntegerSet result = copyOf(b.elements);
        result.elements.removeAll(elements);
        return result;
    }

    /**
     * Tests whether every element of this set is also in {@code b}.
     * An empty set is a subset of every set.
     *
     * @param b the candidate superset
     * @return {@code true} if this set is a subset of {@code b}
     */
    public boolean isSubsetOf(IntegerSet b) {
        return b.elements.containsAll(elements);
    }

    /**
     * Tests whether this set and {@code b} share no elements.
     *
     * @param b the other set
     * @return {@code true} if the sets have no elements in common
     */
    public boolean isDisjoint(IntegerSet b) {
        return Collections.disjoint(elements, b.elements);
    }

    /**
     * Returns the symmetric difference of this set and {@code b}.
     *
     * @param b the other set
     * @return a new set with elements that occur in exactly one of the sets
     */
    public IntegerSet symmetricDiff(IntegerSet b) {
        IntegerSet result = union(b);
        result.elements.removeAll(intersect(b).elements);
        return result;
    }

    /**
     * Tests whether this set has no elements.
     *
     * @return {@code true} if the set is empty
     */
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    /**
     * Returns the elements in ascending order, comma-and-space separated,
     * inside square brackets, e.g. {@code [-3, 0, 8, 15]}. An empty set
     * yields {@code []}.
     *
     * @return the formatted string
     */
    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        for (int value : elements) {
            joiner.add(Integer.toString(value));
        }
        return joiner.toString();
    }

    /**
     * Builds a new independent set holding a copy of the given values.
     *
     * @param values the values to copy
     * @return a new set containing the values
     */
    private static IntegerSet copyOf(Set<Integer> values) {
        IntegerSet result = new IntegerSet();
        result.elements.addAll(values);
        return result;
    }
}
