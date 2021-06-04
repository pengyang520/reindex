
import com.alibaba.fastjson.JSON;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.common.Strings;

import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

public class HttpUtils {

    public static String sendPost(String url, String data) {
        String response = null;
        try {
            CloseableHttpClient httpclient = null;
            CloseableHttpResponse httpresponse = null;
            try {
                httpclient = HttpClients.createDefault();
                org.apache.http.client.methods.HttpPost httppost = new org.apache.http.client.methods.HttpPost(url);
                StringEntity stringentity = new StringEntity(data,
                        ContentType.create("application/json", "UTF-8"));
                httppost.setEntity(stringentity);
                httpresponse = httpclient.execute(httppost);
                response = EntityUtils
                        .toString(httpresponse.getEntity());
            } finally {
                if (httpclient != null) {
                    httpclient.close();
                }
                if (httpresponse != null) {
                    httpresponse.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public static String reindex(List<Map<String,Object>> parse,String change,String keyword,String url,Integer port,String fileUrl,String indexFileName) throws IOException {
        //存储处理成功的索引，后续输出到文件中
        ArrayList<String> list = new ArrayList<String>();
        //存储"需要排除在外的索引名称"的文件名称
        String indexFileUrl=fileUrl+indexFileName;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        Date date = new Date();
        String dateFileName = simpleDateFormat.format(date);
        //重新生成存储索引名称的文件
        dateFileName=dateFileName+".log";
        fileUrl=fileUrl+dateFileName;
        String json = null;
        File file;
        try {
            if(parse==null||parse.size()==0){
                throw new Exception("集合不能为null或空");
            }
            //读取处理过的索引
            file = new File(indexFileUrl);
            if(file.exists()&&file.isFile()){
                Reader reader = new InputStreamReader(new FileInputStream(file),"utf-8");
                int ch = 0;
                StringBuffer sb = new StringBuffer();
                while ((ch = reader.read()) != -1) {
                    sb.append((char) ch);
                }
                reader.close();
                json = sb.toString();
            }
            //查询地址拼接
            url="http://"+url+":"+port+"/_reindex";
            //输入执行reindex所需参数
            HashMap<String, String> source = new HashMap<String, String>();
            HashMap<String, String> dest = new HashMap<String, String>();
            //索引名称
            String index;
            //返回值
            String value;
            //处理索引数量
            int count=0;
            //循环处理索引
            for(Map<String,Object> map: parse){
                index=String.valueOf(map.get("index").toString());
                if((!Strings.isNullOrEmpty(json)&&json.contains(index))||
                        !index.contains(keyword)){
                    continue;
                }
                long begin = System.currentTimeMillis();
                count++;
                System.out.println("正在执行第"+count+"个");
                source.put("index",index+change);
                dest.put("index",index);
                String param = String.valueOf(JSON.toJSON(new Param(source,dest)));
                //参数
                System.out.println(param);
                System.out.println("执行"+index+"的reindex");
                value= HttpUtils.sendPost(url,param);
                System.out.println(value);
                Map<String, Object> valueMap = (Map<String, Object>) JSON.parse(value);
                if(valueMap.get("error")!=null){
                    throw new Exception("reindex过程中出现异常");
                }
                list.add(index);
                long end = System.currentTimeMillis();
                double time = (end-begin)/60000.0;
                BigDecimal b = new BigDecimal(time);
                time = b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
                System.out.println(index+"的reindex执行完毕,用时"+time+"分钟");
            }
            System.out.println("共"+count+"个索引被执行！");
            System.out.println(list.toString());
            return "任务完成!";
        } catch (Exception e) {
            file = new File(fileUrl);
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(String.valueOf(JSON.toJSON(list)));
            fileWriter.close();
            System.out.println(list.toString());
            e.printStackTrace();
            return "任务失败!";
        }
    }
}





