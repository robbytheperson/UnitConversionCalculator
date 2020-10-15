package com.example.unitconversioncalculator;

public class Conversion {
    private static final double[] CONVERSION_ARRAY = {
            0.3048, //Feet to Meters
            3.28084, //Meters to Feet
            2.54, //Inches to Centimeters
            0.3937007874, //Centimeters to Inches
            1.60934, //Miles to Kilometers
            0.621371 //Kilometers to Miles
    };

    public static final String[] labelNames = {
            "Feet",
            "Meters",
            "Inches",
            "Centimeters",
            "Miles",
            "Kilometers"
    };

    private int unit1;
    private Double unit2;

    private int convNumber;

    private double convFactor;

    public Conversion() {
        unit1 = 0;
        unit2 = 0.0;
        convNumber = 0;
        convFactor = 0.0;
    }

    public void setUnit1(int unit1) {
        this.unit1 = unit1;
    }

    public void setUnit2(double unit2) {
        this.unit2 = unit2;
    }

    public void setConvNumber(int conv) {
        convNumber = conv;
    }

    public int getConvNumber() {
        return convNumber;
    }

    public Double calculate() {
        convFactor = CONVERSION_ARRAY[convNumber];
        unit2 = unit1 * convFactor;
        return unit2;
    }
}
