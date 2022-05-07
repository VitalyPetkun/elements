package services;

public enum ProjectFiles {

    CONFIG("config.properties"),
    TEST_DATA("testData.properties");

    private String file;

    ProjectFiles(String file) {
        this.file = file;
    }

    public String getFile() {
        return file;
    }
}
