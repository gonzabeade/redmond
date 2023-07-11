package ar.edu.itba.bd2.redmond.model.elastic;

import ar.edu.itba.bd2.redmond.model.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "users")
public class ElasticUser {
    @Id
    private String id;

    @Field(type = FieldType.Search_As_You_Type)
    private String redmondId;

    public ElasticUser(User user) {
        this.redmondId = user.getRedmondId();
    }

    protected ElasticUser() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRedmondId() {
        return redmondId;
    }

    public void setRedmondId(String redmondId) {
        this.redmondId = redmondId;
    }
}
