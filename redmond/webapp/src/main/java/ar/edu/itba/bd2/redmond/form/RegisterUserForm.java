package ar.edu.itba.bd2.redmond.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class RegisterUserForm {
    @NotBlank
    @Pattern(regexp = "^[a-z][a-z.]+[a-z]$", message = "Redmond ID must be composed of lowercase letters and dots and must start and end with a letter")
    @Size(min = 3, max = 20, message = "Redmond ID must be between 3 and 20 characters")
    private String redmondId;

    @NotEmpty(message = "CBU is required")
    @Pattern(regexp = "^[0-9]+$", message = "CBU must be numeric")
    @Size(min = 22, max = 22, message = "CBU must be 22 digits")
    private String cbu;

    @NotEmpty(message = "CUIL is required")
    @Pattern(regexp = "^[0-9]+$", message = "CUIL must be numeric")
    @Size(min = 11, max = 11, message = "CUIL must be 11 digits")
    private String cuil;

    @NotBlank
    @Size(min = 8, max = 32, message = "Password must be between 8 and 32 characters")
    private String password;

    public String getRedmondId() {
        return redmondId;
    }

    public void setRedmondId(String redmondId) {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
