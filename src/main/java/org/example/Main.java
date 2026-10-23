package org.example;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Locale locale = selectLanguage(scanner);

        ResourceBundle messages =
                ResourceBundle.getBundle(
                        "MessagesBundle",
                        locale
                );

        TemperatureConverter converter =
                new TemperatureConverter();

        System.out.println(messages.getString("welcome"));
        System.out.println(messages.getString("conversionPrompt"));
        System.out.println(messages.getString("conversionOptions"));

        String conversionChoice = scanner.nextLine();

        System.out.println(messages.getString("temperaturePrompt"));

        double temperature;

        try {
            temperature = Double.parseDouble(
                    scanner.nextLine().replace(',', '.')
            );
        } catch (NumberFormatException exception) {
            System.out.println(
                    messages.getString("invalidNumber")
            );
            return;
        }

        double result;
        String unit;

        switch (conversionChoice) {
            case "1" -> {
                result = converter.fahrenheitToCelsius(
                        temperature
                );
                unit = "°C";
            }

            case "2" -> {
                result = converter.celsiusToFahrenheit(
                        temperature
                );
                unit = "°F";
            }

            default -> {
                System.out.println(
                        messages.getString("invalidChoice")
                );
                return;
            }
        }

        NumberFormat numberFormat =
                NumberFormat.getNumberInstance(locale);

        numberFormat.setMaximumFractionDigits(2);

        System.out.println(
                messages.getString("result")
                        + " "
                        + numberFormat.format(result)
                        + " "
                        + unit
        );
    }

    private static Locale selectLanguage(
            Scanner scanner
    ) {
        System.out.println("Select language:");
        System.out.println("1. English");
        System.out.println("2. Suomi");
        System.out.println("3. Svenska");
        System.out.println("4. 日本語");

        String choice = scanner.nextLine();

        return switch (choice) {
            case "2" -> Locale.of("fi", "FI");
            case "3" -> Locale.of("sv", "SE");
            case "4" -> Locale.of("ja", "JP");
            default -> Locale.of("en", "US");
        };
    }
}