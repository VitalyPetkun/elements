package services;

public enum Paths {

    MAIN_RESOURCES_PATH("src\\main\\resources\\"),
    TEST_RESOURCES_PATH("src\\test\\resources\\"),
    IMAGES_PATH("src\\test\\resources\\images\\"),
    SCREENSHOT_TESTS_PATH("src\\test\\resources\\screenshotTests\\%s.png");

    private String path;

    Paths(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
