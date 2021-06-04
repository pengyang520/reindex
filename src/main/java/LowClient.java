import com.alibaba.fastjson.JSON;
import org.apache.http.HttpHost;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * pengyang
 * 2021/5/31 13:44
 * 1.0
 */
public class LowClient {

    public static List<Map<String,Object>> getIndices(String url,int port) throws IOException {
        System.out.println("开始!");
        RestClient restClient = RestClient.builder(
                new HttpHost(url, port, "http")).build();
        Request request = new Request(
                "GET",
                "/_cat/indices");
        request.addParameter("format","json");
        Response response = restClient.performRequest(request);
        String responseBody = EntityUtils.toString(response.getEntity());
        List<Map<String,Object>> parse = (List<Map<String,Object>>)JSON.parse(responseBody);
        restClient.close();
        return parse;
    }
}
