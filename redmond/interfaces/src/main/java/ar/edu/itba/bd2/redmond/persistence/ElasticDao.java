package ar.edu.itba.bd2.redmond.persistence;

import ar.edu.itba.bd2.redmond.model.elastic.ElasticUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ElasticDao extends ElasticsearchRepository<ElasticUser, String> {
    @Query("{\n" +
            "  \"bool\": {\n" +
            "    \"must\": [\n" +
            "      {\n" +
            "        \"match_bool_prefix\": {\n" +
            "          \"redmondId\": {\n" +
            "            \"query\": \"?0\"\n" +
            "          }\n" +
            "        }\n" +
            "      },\n" +
            "      {\n" +
            "        \"bool\": {\n" +
            "          \"must_not\": {\n" +
            "            \"match\": {\n" +
            "              \"redmondId\": \"?1\"\n" +
            "            }\n" +
            "          }\n" +
            "        }\n" +
            "      }\n" +
            "    ]\n" +
            "  }\n" +
            "}")
    Page<ElasticUser> findByRedmondId(String query, String userId, Pageable pageable);
}
