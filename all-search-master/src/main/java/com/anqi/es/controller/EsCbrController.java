package com.anqi.es.controller;

import com.alibaba.fastjson.JSON;
import com.anqi.es.entity.Cloth;
import com.anqi.es.highclient.RestHighLevelClientService;
import com.anqi.es.util.SnowflakeIdWorker;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

/**
 * @author cbr
 */
@RestController
@RequestMapping("/cbr")
public class EsCbrController {

    @Autowired
    RestHighLevelClientService service;

    public void createEsProductUtils(Long id, Boolean isResource) {
        Assert.notNull(isResource, "类型不能为空");
        Assert.notNull(id, "id不能为空");

    }

}
