package services;

public enum TestDataVariables {

    CUR_NAME("curName");

    private String variable;

    TestDataVariables(String variable) {
        this.variable = variable;
    }

    public String getVariable() {
        return variable;
    }
}
