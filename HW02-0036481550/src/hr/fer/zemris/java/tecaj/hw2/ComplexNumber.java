package hr.fer.zemris.java.tecaj.hw2;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Data type for complex numbers. The data type is "immutable" so once you
 * create and initialize a Complex object, you cannot change it.
 * 
 * @author TheKarlo95
 * @version 1.0
 */
public class ComplexNumber {
    /** Real part of the complex number. */
    private final double real;
    /** Imaginary part of the complex number. */
    private final double imaginary;
    /** Magnitude of the complex number. */
    private final double magnitude;
    /** Angle of the complex number. */
    private final double angle;

    /**
     * Constructs a new complex number object with the given real and imaginary
     * parts.
     * 
     * @param real
     *            Real part of a complex number
     * @param imaginary
     *            Imaginary part of a complex number
     */
    public ComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
        this.magnitude = Math.pow(Math.pow(real, 2) + Math.pow(imaginary, 2), 1.0 / 2);
        this.angle = Math.atan2(imaginary, real);
    }

    /**
     * Factory method which creates complex number from real number.
     * 
     * @param real
     *            Real number
     * @return Complex number
     */
    public static ComplexNumber fromReal(double real) {
        return new ComplexNumber(real, 0);
    }

    /**
     * Factory method which creates complex number from imaginary part of
     * complex number.
     * 
     * @param imaginary
     *            Imaginary number
     * @return Complex number
     */
    public static ComplexNumber fromImaginary(double imaginary) {
        return new ComplexNumber(0, imaginary);
    }

    /**
     * Factory method which creates complex number from specified magnitude and
     * angle.
     * 
     * @param magnitude
     *            Magnitude of complex number
     * @param angle
     *            Angle of complex number
     * @return Complex number
     */
    public static ComplexNumber fromMagnitudeAndAngle(double magnitude, double angle) {
        return new ComplexNumber(magnitude * Math.cos(angle), magnitude * Math.sin(angle));
    }

    /**
     * Factory method which creates complex number by parsing a string.
     * 
     * @param s
     *            String to be parsed
     * @return Complex number
     * @throws ParseException
     *             Signals that an error has been reached unexpectedly while
     *             parsing.
     */
    public static ComplexNumber parse(String s) throws ParseException {
        double real = 0;
        double imaginary = 0;

        s = s.trim();

        if (s.matches("^[-+]?[0-9]*\\.?[0-9]+$")) {
            real = Double.parseDouble(s);
        } else if (s.matches("^[-+]?[0-9]*\\.?[0-9]+i$")) {
            imaginary = Double.parseDouble(s.substring(0, s.length() - 1));
        } else {
            Pattern pattern = Pattern.compile(
                    "([-+]?[0-9]*\\.?[0-9]+)([-+]?[0-9]*\\.?[0-9]+)i",
                    Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
            Matcher matcher = pattern.matcher(s);

            if (matcher.matches()) {
                real = Double.parseDouble(matcher.group(1));
                imaginary = Double.parseDouble(matcher.group(2));
            } else {
                throw new ParseException("", 0);
            }
        }

        return new ComplexNumber(real, imaginary);
    }

    /**
     * Gets the real part of a complex number.
     * 
     * @return Real part of a complex number
     */
    public double getReal() {
        return real;
    }

    /**
     * Gets the imaginary part of a complex number.
     * 
     * @return Imaginary part of a complex number
     */
    public double getImaginary() {
        return imaginary;
    }

    /**
     * Gets the magnitude of a complex number.
     * 
     * @return Magnitude of a complex number
     */
    public double getMagnitude() {
        return magnitude;
    }

    /**
     * Gets the angle of a complex number.
     * 
     * @return Angle of a complex number
     */
    public double getAngle() {
        return angle;
    }

    /**
     * Adds specified complex number to this complex number.
     * 
     * @param c
     *            Complex number to be added
     * @return Result of the addition
     */
    public ComplexNumber add(ComplexNumber c) {
        if (c == null) {
            throw new NullPointerException();
        }

        return new ComplexNumber(this.real + c.real, this.imaginary + c.imaginary);
    }

    /**
     * Subtracts specified complex number from this complex number.
     * 
     * @param c
     *            Complex number to be subtracted
     * @return Result of the subtraction
     */
    public ComplexNumber sub(ComplexNumber c) {
        if (c == null) {
            throw new NullPointerException();
        }

        return new ComplexNumber(this.real - c.real, this.imaginary - c.imaginary);
    }

    /**
     * Multiplies specified complex number with this complex number.
     * 
     * @param c
     *            Complex number to be multiplied
     * @return Result of the multiplication
     */
    public ComplexNumber mul(ComplexNumber c) {
        if (c == null) {
            throw new NullPointerException();
        }

        double real = this.real * c.real - this.imaginary * c.imaginary;
        double imaginary = this.real * c.imaginary + this.imaginary * c.real;

        return new ComplexNumber(real, imaginary);
    }

    /**
     * Divides this complex number with specified complex number.
     * 
     * @param c
     *            Complex number used as divisor
     * @return Result of the division
     */
    public ComplexNumber div(ComplexNumber c) {
        if (c == null) {
            throw new NullPointerException();
        }

        ComplexNumber result = mul(c.conjugate());
        double real = result.real / Math.pow(c.magnitude, 2);
        double imaginary = result.imaginary / Math.pow(c.magnitude, 2);

        return new ComplexNumber(real, imaginary);
    }

    /**
     * Calculates the value of this complex number raised to the power of the
     * second argument.
     * 
     * @param n
     *            Exponent
     * @return This complex number to the power of n
     */
    public ComplexNumber power(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }

        if (n == 0) {
            return new ComplexNumber(1, 0);
        }

        double newMagnitude = Math.pow(this.magnitude, n);
        double newAngle = (this.angle * n) % (2 * Math.PI);

        double real = newMagnitude * Math.cos(newAngle);
        double imaginary = newMagnitude * Math.sin(newAngle);

        return new ComplexNumber(real, imaginary);
    }

    /**
     * Calculates the value of this complex number to the root of the second
     * argument.
     * 
     * @param n
     *            Root
     * @return This complex number to the root of n
     */
    public ComplexNumber[] root(int n) {
        if (n < 1) {
            throw new IllegalArgumentException();
        }

        ComplexNumber[] roots = new ComplexNumber[n];

        for (int i = 0; i < n; i++) {
            double real = Math.pow(magnitude, 1 / ((double) n))
                    * Math.cos((angle + i * 2.0 * Math.PI) / n);
            double imaginary = Math.pow(magnitude, 1 / ((double) n))
                    * Math.sin((angle + i * 2.0 * Math.PI) / n);

            roots[i] = new ComplexNumber(real, imaginary);
        }

        return roots;
    }

    /**
     * Conjugates complex number
     * 
     * @return Conjugated complex number
     */
    private ComplexNumber conjugate() {
        return new ComplexNumber(real, 0 - imaginary);
    }

    /**
     * {inheritDoc}
     */
    public String toString() {
        DecimalFormat format = new DecimalFormat("#.####");
        format.setDecimalSeparatorAlwaysShown(false);

        if (real == 0) {
            return String.format("%si", format.format(imaginary));
        } else if (imaginary > 0) {
            return String.format("%s + %si", format.format(real), format.format(imaginary));
        } else if (imaginary < 0) {
            return String.format("%s - %si", format.format(real), format.format(Math.abs(imaginary)));
        } else {
            return String.format("%s", format.format(real));
        }
    }
}
