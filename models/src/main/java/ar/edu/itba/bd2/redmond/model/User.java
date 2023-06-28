package ar.edu.itba.bd2.redmond.model;

public class User {

    private String cbu;
    private String cuil;
    private String redmondId;

    public User() {
        // Just for caching
    }

    public User(String cbu, String cuil, String redmondId) {
        this.cbu = cbu;
        this.cuil = cuil;
        this.redmondId = redmondId;
    }

    public String getCbu() {
        return cbu;
    }

    public void setCbu(String cbu) {
        this.cbu = cbu;
    }

    public String getCuil() {
        return cuil;
    }

    public void setCuil(String cuil) {
        this.cuil = cuil;
    }

    public String getRedmondId() {
        return redmondId;
    }

    public void setRedmondId(String redmondId) {
        this.redmondId = redmondId;
    }
}
