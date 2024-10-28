package main.java.com.example.currency_converter;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CurrencyConverter {
    private static final Map<String, Double> exchangeRates = new HashMap<>();

    public static void main(String[] args) {
        initializeExchangeRates();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Available currencies: USD, EUR, GBP, JPY");

        System.out.print("Enter currency to convert: ");
        String fromCurrency = scanner.nextLine().toUpperCase();

        System.out.print("Enter the currency that you want to convert to: ");
        String toCurrency = scanner.nextLine().toUpperCase();

        System.out.print("Enter the amount to convert: ");
        double amount = scanner.nextDouble();

        if (exchangeRates.containsKey(fromCurrency) && exchangeRates.containsKey(toCurrency)) {
            double convertedAmount = convert(fromCurrency, toCurrency, amount);
            System.out.printf("Converted amount: %.2f %s\n", convertedAmount, toCurrency);
        } else {
            System.out.println("Incorrect currency codes have been entered.");
        }

        scanner.close();
    }

    private static void initializeExchangeRates() {
        exchangeRates.put("USD", 1.0);
        exchangeRates.put("EUR", 0.85);
        exchangeRates.put("GBP", 0.75);
        exchangeRates.put("JPY", 110.0);
    }

    private static double convert(String fromCurrency, String toCurrency, double amount) {
        double fromRate = exchangeRates.get(fromCurrency);
        double toRate = exchangeRates.get(toCurrency);
        return amount * (toRate / fromRate);
    }
}