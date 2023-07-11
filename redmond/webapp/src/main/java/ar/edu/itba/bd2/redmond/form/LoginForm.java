package ar.edu.itba.bd2.redmond.form;

import javax.validation.constraints.NotBlank;

public class LoginForm {
    @NotBlank
    private String redmondId;

    @NotBlank
    private String password;

    public String getRedmondId() {
        return redmondId;
    }

    public void setRedmondId(String redmondId) {
        this.redmondId = redmondId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
