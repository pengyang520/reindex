import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * pengyang
 * 2021/6/1 14:49
 * 1.0
 */
public class Reindex {
    public static void main(String[] args) throws Exception {
        long begin = System.currentTimeMillis();
        Properties properties = new Properties();
        properties.load(Reindex.class.getResourceAsStream("/application.properties"));
        String url = properties.getProperty("url");
        Integer port = Integer.valueOf(properties.getProperty("port"));
        String change = properties.getProperty("change");
        String keyword = properties.getProperty("keyword");
        String fileUrl = properties.getProperty("file_url");
        String indexFileName = properties.getProperty("index_file_name");
        List<Map<String, Object>> indices = LowClient.getIndices(url, port);
        HttpUtils.reindex(indices,change,keyword,url,port,fileUrl,indexFileName);
        long end = System.currentTimeMillis();
        double time = (end-begin)/3600000.0;
        BigDecimal b = new BigDecimal(time);
        time = b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println("消耗"+time+"小时");
    }
}
