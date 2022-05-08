package services;

public enum TestDataVariables {

    CURRENCY_BANK_BUYING_USD_COLUMN_INDEX("currencyBankBuyingUsdColumnIndex"),
    CURRENCY_BANK_SALE_USD_COLUMN_INDEX("currencyBankSaleUsdColumnIndex"),
    DATE("date");

    private String variable;

    TestDataVariables(String variable) {
        this.variable = variable;
    }

    public String getVariable() {
        return variable;
    }
}
