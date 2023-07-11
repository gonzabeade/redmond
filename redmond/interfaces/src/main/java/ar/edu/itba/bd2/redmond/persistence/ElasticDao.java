package ar.edu.itba.bd2.redmond.persistence;

import ar.edu.itba.bd2.redmond.model.elastic.ElasticUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ElasticDao extends ElasticsearchRepository<ElasticUser, String> {
    @Query("{\"match_bool_prefix\": {\"redmondId\": {\"query\": \"?0\"}}}")
    Page<ElasticUser> findByRedmondId(String redmondId, Pageable pageable);
}
