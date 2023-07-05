package ar.edu.itba.bd2.redmond.model.enums;

public enum Bank {
    BANK_1,
    BANK_2;

    public static Bank fromString(String bank) {
        if (bank.equals("BANK_1")) {
            return BANK_1;
        } else if (bank.equals("BANK_2")) {
            return BANK_2;
        } else {
            throw new IllegalArgumentException("Invalid bank");
        }
    }
}
