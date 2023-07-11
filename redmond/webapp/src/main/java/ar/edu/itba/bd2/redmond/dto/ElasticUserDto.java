package ar.edu.itba.bd2.redmond.dto;

import ar.edu.itba.bd2.redmond.model.elastic.ElasticUser;

public class ElasticUserDto {
    private final String redmondId;

    public ElasticUserDto(ElasticUser elasticUser) {
        this.redmondId = elasticUser.getRedmondId();
    }

    public String getRedmondId() {
        return redmondId;
    }
}
