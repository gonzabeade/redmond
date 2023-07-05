package ar.edu.itba.bd2.redmond.dto;

public class JwtDto {
    private String refresh;
    private String token;

    public JwtDto() {
    }

    public JwtDto(String refresh, String token) {
        this.refresh = refresh;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public String getRefresh() {
        return refresh;
    }
}
