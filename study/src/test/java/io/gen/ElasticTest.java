package io.gen;

import com.carrotsearch.hppc.cursors.ObjectObjectCursor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.ActionResponse;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.action.admin.cluster.storedscripts.GetStoredScriptResponse;
import org.elasticsearch.action.admin.indices.settings.get.GetSettingsResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;

import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.*;
import org.elasticsearch.client.core.TermVectorsRequest;
import org.elasticsearch.client.core.TermVectorsResponse;
import org.elasticsearch.client.sniff.Sniffer;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.health.ClusterHealthStatus;
import org.elasticsearch.cluster.health.ClusterIndexHealth;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptType;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

import static java.util.Collections.singletonMap;
import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

public class ElasticTest {

    public Settings settings = Settings.builder()
            .put("client.transport.sniff", true).build();
    public TransportClient client = new PreBuiltTransportClient(settings)
            .addTransportAddress(new TransportAddress(InetAddress.getByName("127.0.0.1"), 9300));

    public static RestHighLevelClient restClient = new RestHighLevelClient(RestClient.builder(
            new HttpHost("localhost", 9200, "http")
    ));



    static {
        System.out.println("Happy New Year!");
    }

    public ElasticTest() throws UnknownHostException {
    }


    @Test
    public void testTerm() throws IOException {
        TermVectorsRequest request = new TermVectorsRequest("posts", "doc", "2");
        request.setFields("user");
        Map<String, Integer> filterSettings = new HashMap<>();
        filterSettings.put("max_num_terms", 3);
        filterSettings.put("min_term_freq", 1);
        filterSettings.put("max_term_freq", 10);
        filterSettings.put("min_doc_freq", 1);
        filterSettings.put("max_doc_freq", 100);
        filterSettings.put("min_word_length", 1);
        filterSettings.put("max_word_length", 10);
        request.setFilterSettings(filterSettings);

        Map<String, String> perFieldAnalyzer = new HashMap<>();
        perFieldAnalyzer.put("user", "keyword");
        request.setPerFieldAnalyzer(perFieldAnalyzer);

        request.setRealtime(false);
        request.setRouting("routing");

        TermVectorsResponse response = restClient.termvectors(request, RequestOptions.DEFAULT);
        System.out.println(response.toString());

        ActionListener listener = new ActionListener<TermVectorsResponse>() {
            @Override
            public void onResponse(TermVectorsResponse termVectorsResponse) {

                System.out.println("success");
            }
            @Override
            public void onFailure(Exception e) {

                System.out.println("fuck");
            }
        };
        restClient.termvectorsAsync(request, RequestOptions.DEFAULT, listener);
    }

    @Test
    public void testUpdate(){
        UpdateRequest request = new UpdateRequest("posts", "doc", "1");
//        Map<String, Object> parameters = singletonMap("count", 4);
//        Script inline = new Script(ScriptType.INLINE, "painless", "ctx._source.field += params.count", parameters);
//        request.script(inline);
        String jsonString = "{" +
                "\"updated\":\"2017-01-01\"," +
                "\"reason\":\"daily update\"" +
                "}";
        request.doc(jsonString, XContentType.JSON);

        String jsonString1 = "{\"created\":\"2017-01-01\"}";
        request.upsert(jsonString1, XContentType.JSON);
//        Map<String, Object> jsonMap = new HashMap<>();
//        jsonMap.put("updated", new Date());
//        jsonMap.put("reason", "daily update");
//
    }

    @Test
    public void testExist()throws Exception{
        GetRequest getRequest = new GetRequest("posts", "doc", "1");
        getRequest.fetchSourceContext(new FetchSourceContext(false));
        getRequest.storedFields("_none_");
        boolean exists = restClient.exists(getRequest, RequestOptions.DEFAULT);
    }

    @Test
    public void testGetAsyn()throws Exception{
        GetRequest getRequest = new GetRequest("posts", "doc", "1");
        getRequest.fetchSourceContext(FetchSourceContext.DO_NOT_FETCH_SOURCE);

        String[] includes = new String[]{"message", "*Date"};
        String[] excludes = Strings.EMPTY_ARRAY;
        FetchSourceContext fetchSourceContext = new FetchSourceContext(true, includes, excludes);
        getRequest.fetchSourceContext(fetchSourceContext);

        ActionListener<GetResponse> listener = new ActionListener<GetResponse>() {
            @Override
            public void onResponse(GetResponse documentFields) {
                for (;;){
                    System.out.println("success");
                }
            }

            @Override
            public void onFailure(Exception e) {
                for (;;){
                    System.out.println("fuck it");
                }
            }
        };

        restClient.getAsync(getRequest, RequestOptions.DEFAULT, listener);
    }

    @Test
    public void testGetSyn()throws Exception{
        GetRequest getRequest = new GetRequest("posts", "doc", "1");
        getRequest.fetchSourceContext(FetchSourceContext.DO_NOT_FETCH_SOURCE);

        String[] includes = new String[]{"message", "*Date"};
        String[] excludes = Strings.EMPTY_ARRAY;
        FetchSourceContext fetchSourceContext = new FetchSourceContext(true, includes, excludes);
        getRequest.fetchSourceContext(fetchSourceContext);

        GetResponse getResponse = restClient.get(getRequest, RequestOptions.DEFAULT);
        System.out.println(getResponse.toString());
    }

    @Test
    public void testSniff(){
        RestClient restClient = RestClient.builder(
                new HttpHost("localhost", 9200, "http")
        ).build();
        Sniffer sniffer = Sniffer.builder(restClient).setSniffIntervalMillis(60000).build();
    }

    @Test
    public void testNodeSelector(){
        RestClientBuilder builder = RestClient.builder(
                new HttpHost("localhost", 9200, "http")
        );
        builder.setNodeSelector(new NodeSelector() {
            @Override
            public void select(Iterable<Node> nodes) {
                boolean foundOne = false;
                for (Node node: nodes){
                    String rackId = node.getAttributes().get("rack_id").get(0);
                    if ("rack_one".equals(rackId)){
                        foundOne = true;
                        break;
                    }
                }

                if (foundOne){
                    Iterator<Node> nodesIt = nodes.iterator();
                    while (nodesIt.hasNext()){
                        Node node = nodesIt.next();
                        String rackId = node.getAttributes().get("rack_id").get(0);
                        if ("rack_one".equals(rackId) == false){
                            nodesIt.remove();
                        }
                    }
                }
            }
        });
    }


    @Test
    public void testRestCRUD() throws IOException {
        IndexRequest request = new IndexRequest("posts","doc", "1");
        String json = "{" +
                "\"user\":\"Gen\"," +
                "\"postDate\":\"2013-01-30\"," +
                "\"message\":\"trying out Elasticsearch\"" +
                "}";
        request.source(json, XContentType.JSON);

        IndexResponse indexResponse = restClient.index(request, RequestOptions.DEFAULT);
        System.out.println(indexResponse.toString());
    }

    @Test
    public void testCRUD() throws IOException {
        String json = "{" +
                "\"user\":\"kimchy\"," +
                "\"postDate\":\"2013-01-30\"," +
                "\"message\":\"trying out Elasticsearch\"" +
                "}";

//        Map<String, Object> json1 = new HashMap<>();
//        json1.put("user", "kimchy");
//        json1.put("postDate", new Date());
//        json1.put("message", "trying out Elasticsearch");
//
//        ObjectMapper mapper = new ObjectMapper();
//        byte[] json3 = mapper.writeValueAsBytes(new String("fuck"));
//
//        XContentBuilder builder = jsonBuilder().startObject()
//                .field("user", "kimchy")
//                .field("postDate", new Date())
//                .field("message", "fuck you ")
//                .endObject();
//        String json4 = Strings.toString(builder);
        IndexResponse response = client.prepareIndex("twitter", "_doc")
                .setSource(json, XContentType.JSON)
                .get();

        String index = response.getIndex();
        String type = response.getType();
        String id = response.getId();
        long version = response.getVersion();
        RestStatus status = response.status();
        System.out.println(response.toString());
    }
    @Test
    public void testCluster(){
        ClusterAdminClient clusterAdminClient = client.admin().cluster();
        ClusterHealthResponse healths = clusterAdminClient.prepareHealth().get();
        String clusterName = healths.getClusterName();
        int dataNodes = healths.getNumberOfDataNodes();
        int nodes = healths.getNumberOfNodes();
        System.out.println("clustername: " + clusterName + ", dataNodes: " + dataNodes + ", nodes: " + nodes);

        for (ClusterIndexHealth health : healths.getIndices().values()) {
            String index = health.getIndex();
            int numberOfShards = health.getNumberOfShards();
            int numberOfReplicas = health.getNumberOfReplicas();
            ClusterHealthStatus status = health.getStatus();
        }

        ClusterHealthResponse response = client.admin().cluster().prepareHealth("twitter")
                .setWaitForGreenStatus()
                .get();

        ClusterHealthStatus status = response.getIndices().get("twitter").getStatus();
        while (!status.equals(ClusterHealthStatus.GREEN)){
            System.out.println("fuck");
            if (!status.equals(ClusterHealthStatus.GREEN)) {
                throw new RuntimeException("Index is in " + status + " state");
            }
        }

    }

    @Test
    public void testAdmin(){
        IndicesAdminClient indicesAdminClient = client.admin().indices();
//        ActionResponse response = indicesAdminClient.prepareCreate("twitter").setSettings(Settings.builder().put("index.number_of_shards", 3)
//        .put("index.number_of_replicas", 2)).get();
//        System.out.println(response);
//
//        ActionResponse response02 =  indicesAdminClient.prepareCreate("twitter").addMapping("_doc", "message", "type=text").get();
//        System.out.println(response02);
//        ActionResponse response = indicesAdminClient.prepareCreate("twitter").get();
        GetSettingsResponse getSettingsResponse = indicesAdminClient.prepareGetSettings("twitter").get();
        for (ObjectObjectCursor<String, Settings> cursor : getSettingsResponse.getIndexToSettings() ){
            String index = cursor.key;
            Settings settings = cursor.value;
            Integer shards = settings.getAsInt("index.number_of_shards", null);
            Integer replicas = settings.getAsInt("index.number_of_replicas", null);
            System.out.println("index= " + index + " " + "shards= " + shards + " " + "replicas= " + replicas);
        }
//        indicesAdminClient.prepareRefresh().get();

    }

    @Test
    public void test()throws Exception{
        Settings settings = Settings.builder()
                .put("client.transport.sniff", true).build();
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("solar"), 9300));
        client.listedNodes();
        client.close();
    }
}
