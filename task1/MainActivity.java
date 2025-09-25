package com.example.unitconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.*;

public class MainActivity extends AppCompatActivity {

    EditText inputValue;
    Spinner categorySpinner, conversionSpinner;
    Button convertButton;
    TextView resultText;

    Map<String, String[]> conversionMap = new HashMap<>();
    String selectedConversion = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputValue = findViewById(R.id.inputValue);
        categorySpinner = findViewById(R.id.categorySpinner);
        conversionSpinner = findViewById(R.id.conversionSpinner);
        convertButton = findViewById(R.id.convertButton);
        resultText = findViewById(R.id.resultText);

        setupConversionMap();

        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, new ArrayList<>(conversionMap.keySet()));
        categorySpinner.setAdapter(categoryAdapter);

        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String category = categorySpinner.getSelectedItem().toString();
                String[] conversions = conversionMap.get(category);
                ArrayAdapter<String> conversionAdapter = new ArrayAdapter<>(MainActivity.this,
                        android.R.layout.simple_spinner_dropdown_item, conversions);
                conversionSpinner.setAdapter(conversionAdapter);
            }

            @Override public void onNothingSelected(AdapterView<?> parent) {}
        });

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                performConversion();
            }
        });
    }

    private void setupConversionMap() {
        conversionMap.put("Length", new String[]{
                "Centimeters to Meters", "Meters to Centimeters", "Meters to Kilometers", "Kilometers to Meters",
                "Inches to Feet", "Feet to Inches", "Inches to Centimeters", "Centimeters to Inches",
                "Feet to Meters", "Meters to Feet", "Kilometers to Miles", "Miles to Kilometers"
        });

        conversionMap.put("Mass", new String[]{
                "Grams to Kilograms", "Kilograms to Grams", "Pounds to Kilograms", "Kilograms to Pounds", "Milligrams to Grams", "Grams to Milligrams"
        });

        conversionMap.put("Speed", new String[]{
                "Meters/sec to Km/h", "Km/h to Meters/sec", "Km/h to Miles/h", "Miles/h to Km/h"
        });

        conversionMap.put("Temperature", new String[]{
                "Celsius to Fahrenheit", "Fahrenheit to Celsius", "Celsius to Kelvin", "Kelvin to Celsius"
        });

        conversionMap.put("Storage", new String[]{
                "KB to MB", "MB to KB", "MB to GB", "GB to MB", "GB to TB", "TB to GB"
        });

        conversionMap.put("Time", new String[]{
                "Seconds to Minutes", "Minutes to Seconds", "Minutes to Hours", "Hours to Minutes", "Hours to Days", "Days to Hours"
        });

        conversionMap.put("Volume", new String[]{
                "Milliliters to Liters", "Liters to Milliliters", "Liters to Gallons", "Gallons to Liters"
        });

        conversionMap.put("Energy", new String[]{
                "Joules to Kilojoules", "Kilojoules to Joules", "Calories to Kilocalories", "Kilocalories to Calories"
        });

        conversionMap.put("Angle", new String[]{
                "Degrees to Radians", "Radians to Degrees"
        });
    }

    private void performConversion() {
        String inputStr = inputValue.getText().toString();
        if (inputStr.isEmpty()) {
            resultText.setText("Please enter a value.");
            return;
        }

        double value = Double.parseDouble(inputStr);
        String conversion = conversionSpinner.getSelectedItem().toString();
        double result = 0;
        String unit = "";

        switch (conversion) {
            // Length
            case "Centimeters to Meters": result = value / 100; unit = "m"; break;
            case "Meters to Centimeters": result = value * 100; unit = "cm"; break;
            case "Meters to Kilometers": result = value / 1000; unit = "km"; break;
            case "Kilometers to Meters": result = value * 1000; unit = "m"; break;
            case "Inches to Feet": result = value / 12; unit = "ft"; break;
            case "Feet to Inches": result = value * 12; unit = "in"; break;
            case "Inches to Centimeters": result = value * 2.54; unit = "cm"; break;
            case "Centimeters to Inches": result = value / 2.54; unit = "in"; break;
            case "Feet to Meters": result = value * 0.3048; unit = "m"; break;
            case "Meters to Feet": result = value / 0.3048; unit = "ft"; break;
            case "Kilometers to Miles": result = value * 0.621371; unit = "mi"; break;
            case "Miles to Kilometers": result = value / 0.621371; unit = "km"; break;

            // Mass
            case "Grams to Kilograms": result = value / 1000; unit = "kg"; break;
            case "Kilograms to Grams": result = value * 1000; unit = "g"; break;
            case "Pounds to Kilograms": result = value * 0.453592; unit = "kg"; break;
            case "Kilograms to Pounds": result = value / 0.453592; unit = "lbs"; break;
            case "Milligrams to Grams": result = value / 1000; unit = "g"; break;
            case "Grams to Milligrams": result = value * 1000; unit = "mg"; break;

            // Speed
            case "Meters/sec to Km/h": result = value * 3.6; unit = "km/h"; break;
            case "Km/h to Meters/sec": result = value / 3.6; unit = "m/s"; break;
            case "Km/h to Miles/h": result = value * 0.621371; unit = "mph"; break;
            case "Miles/h to Km/h": result = value / 0.621371; unit = "km/h"; break;

            // Temperature
            case "Celsius to Fahrenheit": result = (value * 9 / 5) + 32; unit = "°F"; break;
            case "Fahrenheit to Celsius": result = (value - 32) * 5 / 9; unit = "°C"; break;
            case "Celsius to Kelvin": result = value + 273.15; unit = "K"; break;
            case "Kelvin to Celsius": result = value - 273.15; unit = "°C"; break;

            // Storage
            case "KB to MB": result = value / 1024; unit = "MB"; break;
            case "MB to KB": result = value * 1024; unit = "KB"; break;
            case "MB to GB": result = value / 1024; unit = "GB"; break;
            case "GB to MB": result = value * 1024; unit = "MB"; break;
            case "GB to TB": result = value / 1024; unit = "TB"; break;
            case "TB to GB": result = value * 1024; unit = "GB"; break;

            // Time
            case "Seconds to Minutes": result = value / 60; unit = "min"; break;
            case "Minutes to Seconds": result = value * 60; unit = "sec"; break;
            case "Minutes to Hours": result = value / 60; unit = "hr"; break;
            case "Hours to Minutes": result = value * 60; unit = "min"; break;
            case "Hours to Days": result = value / 24; unit = "days"; break;
            case "Days to Hours": result = value * 24; unit = "hr"; break;

            // Volume
            case "Milliliters to Liters": result = value / 1000; unit = "L"; break;
            case "Liters to Milliliters": result = value * 1000; unit = "ml"; break;
            case "Liters to Gallons": result = value * 0.264172; unit = "gal"; break;
            case "Gallons to Liters": result = value / 0.264172; unit = "L"; break;

            // Energy
            case "Joules to Kilojoules": result = value / 1000; unit = "kJ"; break;
            case "Kilojoules to Joules": result = value * 1000; unit = "J"; break;
            case "Calories to Kilocalories": result = value / 1000; unit = "kcal"; break;
            case "Kilocalories to Calories": result = value * 1000; unit = "cal"; break;

            // Angle
            case "Degrees to Radians": result = Math.toRadians(value); unit = "rad"; break;
            case "Radians to Degrees": result = Math.toDegrees(value); unit = "°"; break;

            default:
                resultText.setText("Unknown conversion.");
                return;
        }

        resultText.setText("Result: " + result + " " + unit);
    }
}