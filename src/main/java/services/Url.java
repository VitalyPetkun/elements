package services;

public enum Url {

    MYFIN_URL("myfinUrl");

    private String url;

    Url(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
