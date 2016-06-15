package hr.fer.zemris.java.tecaj.hw2.demo;

import java.text.ParseException;

import hr.fer.zemris.java.tecaj.hw2.ComplexNumber;

/**
 * Command-line application which demonstrates the functionilaty of
 * ComplexNumber class.
 * 
 * @author TheKarlo95
 * @version 1.0
 */
public class ComplexDemo {

    /**
     * Starting point of a program.
     * 
     * @param args
     *            Command-line argument
     * @throws ParseException
     *             ParseException Signals that an error has been reached
     *             unexpectedly while parsing.
     */
    public static void main(String[] args) throws ParseException {
        ComplexNumber c1 = new ComplexNumber(2, 3);
        ComplexNumber c2 = ComplexNumber.parse("2.5-3i");
        ComplexNumber c3 = c1.add(ComplexNumber.fromMagnitudeAndAngle(2, 1.57))
                .div(c2).power(3).root(2)[1];

        System.out.println(c3);
    }
}
