package services;

public enum Paths {

    MAIN_RESOURCES_PATH("src/main/resources/"),
    TEST_RESOURCES_PATH("src/test/resources/"),
    IMAGES_TESTS("src//test//resources//images//%s.png");

    private String path;

    Paths(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
