package com.anqi.es.controller;

import com.alibaba.fastjson.JSON;
import com.anqi.es.entity.Cloth;
import com.anqi.es.highclient.RestHighLevelClientService;
import com.anqi.es.util.SnowflakeIdWorker;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

/**
 * @author anqi
 */
@RestController
public class EsController {

    @Autowired
    RestHighLevelClientService service;

    @GetMapping("/es/add")
    public String testHigh(HttpServletResponse httpServletResponse) throws IOException{
        String source = "{\n" +
                "  \"name\" : \"耐苦无领运动半袖\",\n" +
                "  \"price\" : 300,\n" +
                "  \"num\" : 800,\n" +
                "  \"date\" : \"2019-07-28\"\n" +
                "}";

        IndexResponse response = service.addDoc("idx_clouthing", source);

        return response.toString();
    }



    public void createEsProductUtils(Long id, Boolean isResource) {
        Assert.notNull(isResource, "类型不能为空");
        Assert.notNull(id, "id不能为空");

    }

    @GetMapping("/es/creIndex")
    public void createIndex() throws IOException{
        String settings =
                "{\n" +
                        " \"number_of_shards\" : 1,\n" +
                        " \"number_of_replicas\" : 0\n" +
                        " }\n" ;


        //设置 id 为 keyword 不分词，用来精准匹配，存放主键 可以设置 index : true 来让属性只存储，不会被查到
        String mappings =
                "{\n" +
                        "  \"properties\": {\n" +
                        "   \"id\": {\n" +
                        "    \"type\": \"keyword\"\n" +
                        "   },\n" +
                        "   \"name\": {\n" +
                        "    \"type\": \"text\",\n" +
                        "     \"analyzer\": \"ik_max_word\"\n" +
                        "   },\n" +
                        "   \"desc\": {\n" +
                        "    \"type\": \"text\",\n" +
                        "    \"analyzer\": \"ik_max_word\"\n" +
                        "   },\n" +
                        "    \"price\": {\n" +
                        "     \"type\": \"double\"\n" +
                        "   },\n" +
                        "   \"date\": {\n" +
                        "    \"format\": \"yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis\",\n" +
                        "     \"type\": \"date\"\n" +
                        "   },\n" +
                        "   \"num\": {\n" +
                        "    \"type\": \"integer\"\n" +
                        "     }\n" +
                        "  }\n" +
                        "}";
        CreateIndexResponse response = service.createIndex("idx_cloth", settings, mappings);
        if (response.isAcknowledged()) {
            System.out.println("创建成功");
        }
    }


    @GetMapping("/es/delIndex")
    public void deleteIndex() throws IOException{
        AcknowledgedResponse response = service.deleteIndex("idx_cloth");
        if (response.isAcknowledged()) {
            System.out.println("删除成功");
        }
    }

    @GetMapping("/es/exists")
    public void indexExists() throws IOException{
        System.out.println(service.indexExists("idx_tt"));
    }


    @Test
    public void addDoc() throws IOException {
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
        Cloth cloth = new Cloth(idWorker.nextId()+"","新版日系毛衣", "潮流前线等你来Pick!", 50, 199.99, new Date());
        String source = JSON.toJSONString(cloth);
        IndexResponse response = service.addDoc("idx_cloth", source);
        System.out.println(response.status());
    }

    @Test
    public void search() throws IOException{
        SearchResponse response = service.search("name", "毛衣", 0, 30, "idx_cloth");
        Arrays.asList(response.getHits().getHits())
                .forEach(e -> System.out.println(e.getSourceAsString()));
    }

    @Test
    public void termSearch() throws IOException{
        SearchResponse response = service.termSearch("name", "nike潮流毛衣", 0, 50);
        SearchHits hits = response.getHits();
        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsString());
        }
    }

    @Test
    public void deleteDoc() throws IOException{
        String id = "yvJ_Q24BymdyZW22Os2D";
        SearchResponse search = service.search("id", "2", 0, 5, "idx_cloth");

        for (SearchHit hit : search.getHits().getHits()) {
            id = hit.getId();
        }
        DeleteResponse response = service.deleteDoc("idx_cloth", id);
        if (response.status().equals(RestStatus.OK)) {
            System.out.println("删除成功");
        }
    }
}
