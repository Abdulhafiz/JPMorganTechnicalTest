package uk.glasgow.jpmorgan.report.daily.util;

/**
 * J.P Morgan Java Technical Test
 * @author Abdul Hafiz
 *
 */

public enum CurrencyType {

    SAR("SAR", "Saudi Arabian Riyal"), AED("AED", "Arab Emirates Dirham"), GBP("GBP", "Great Britain Pound"), USD(
            "USD", "Unitaed State Dollar");

    private final String name;
    private final String description;

    private CurrencyType(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getValue() {
        return name;
    }

    public String getDescription() {
        return description;
    }

}
