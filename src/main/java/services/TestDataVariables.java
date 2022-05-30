package services;

public enum TestDataVariables {

    CURRENCY_BANK_BUYING_USD_COLUMN_INDEX("currencyBankBuyingUsdColumnIndex"),
    CURRENCY_BANK_SALE_USD_COLUMN_INDEX("currencyBankSaleUsdColumnIndex"),
    CURRENCY_BANK_BUYING_EUR_COLUMN_INDEX("currencyBankBuyingEurColumnIndex"),
    CURRENCY_BANK_SALE_EUR_COLUMN_INDEX("currencyBankSaleEurColumnIndex"),
    FIRST_DATE("firstDate"),
    SECOND_DATE("secondDate"),
    THIRD_DATE("thirdDate"),
    NAME_MAIN_NEWS_IMAGE("nameMainNewsImage"),
    NAME_WALLET_NEWS_IMAGE("nameWalletNewsImage");

    private String variable;

    TestDataVariables(String variable) {
        this.variable = variable;
    }

    public String getVariable() {
        return variable;
    }
}
