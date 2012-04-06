package de.mh4j.examples;

import java.util.ArrayList;
import java.util.Random;

import de.mh4j.solver.Solution;

/**
 * A Sorting represents a not necessarily ordered array of the numbers from 0 to
 * 99. This is the {@link Solution} class for the first set of example
 * implementations of MH4J.<br>
 * <br>
 * The goal of the optimization algorithms is to find the correct arrangement of
 * the numbers, so that each number stands at its correct array index (number
 * zero is at array index 0 and number three is at index 3)<br>
 * <br>
 * Of course the correct solution to this problem is trivial, but this is only
 * an easy example implementation that should help you to understand the
 * concrete algorithm implementations.
 */
public class Sorting implements Solution {

    private final int[] numbers;
    private int costs;

    public Sorting(int[] numbers) {
        this.numbers = numbers;
        checkForDuplicates();
        calculateCosts();
    }

    private void checkForDuplicates() throws IllegalArgumentException {
        boolean[] numberExists = new boolean[numbers.length];
        for (int i = 0; i < numberExists.length; i++) {
            numberExists[i] = false;
        }

        for (int i = 0; i < numbers.length; i++) {
            int number = numbers[i];
            if (numberExists[number] == true) {
                throw new IllegalArgumentException("Input array contains at least one duplicate number at index " + i);
            }
            else {
                numberExists[number] = true;
            }
        }
    }

    private void calculateCosts() {
        costs = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] != i) {
                costs++;
            }
        }
    }

    @Override
    public int getCosts() {
        return costs;
    }

    @Override
    public boolean isBetterThan(Solution otherSolution) {
        return this.getCosts() < otherSolution.getCosts();
    }

    public int[] getNumbers() {
        return numbers;
    }

    /**
     * Creates a new Sorting instance with a random non-repeating numbers array.
     * 
     * @param numberOfEntries
     *            Determines how many different numbers will be used in this
     *            sorting (i.e. the size of the underlying array)
     */
    public static Sorting createRandomSorting(int numberOfEntries) {
        ArrayList<Integer> remainingNumbers = new ArrayList<>(numberOfEntries);
        for (int i = 0; i < numberOfEntries; i++) {
            remainingNumbers.add(new Integer(i));
        }

        Random random = new Random();
        int[] numbers = new int[numberOfEntries];
        int currentIndex = 0;
        do {
            int randomIndex = random.nextInt(remainingNumbers.size());
            int randomNumber = remainingNumbers.remove(randomIndex);
            numbers[currentIndex] = randomNumber;
            currentIndex++;
        } while (remainingNumbers.isEmpty() == false);

        return new Sorting(numbers);
    }
}
