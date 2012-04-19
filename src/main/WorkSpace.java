import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class WorkSpace {
    private String path;

    public WorkSpace(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void forceClean() {
        try {
            Runtime.getRuntime().exec("/bin/rm -rf " + path);
        } catch (IOException e) {
        }
    }

    public boolean contains(String file) throws IOException {
        FileReader reader = null;
        try {
            reader = new FileReader(file);
        } catch (FileNotFoundException e) {
            return false;
        }
        reader.close();
        return true;
    }
}
