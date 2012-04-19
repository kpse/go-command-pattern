public class GoCodeBase {
    private String url;

    public GoCodeBase(String url) {

        this.url = url;
    }

    public boolean isChanged() {
        if (url.isEmpty()) {
            return false;
        }
        return true;
    }

    public String getUrl() {
        return url;
    }
}
