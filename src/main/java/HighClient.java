import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.reindex.ReindexRequest;

import java.io.IOException;

/**
 * pengyang
 * 2021/5/31 13:48
 * 1.0
 */
public class HighClient {
    public static void main(String[] args) throws IOException {
        System.out.println(11);
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("172.17.13.22", 9699, "http")));
        ReindexRequest request = new ReindexRequest();

    }
}
