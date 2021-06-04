import java.util.HashMap;

/**
 * pengyang
 * 2021/6/1 10:30
 * 1.0
 */
public class Param {
    HashMap<String, String> source;
    HashMap<String, String> dest;

    public Param(HashMap<String, String> source, HashMap<String, String> dest) {
        this.source = source;
        this.dest = dest;
    }

    public HashMap<String, String> getSource() {
        return source;
    }

    public void setSource(HashMap<String, String> source) {
        this.source = source;
    }

    public HashMap<String, String> getDest() {
        return dest;
    }

    public void setDest(HashMap<String, String> dest) {
        this.dest = dest;
    }

    @Override
    public String toString() {
        return "Param{" +
                "source=" + source +
                ", dest=" + dest +
                '}';
    }
}
