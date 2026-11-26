package pe.edu.upc.demovi.dtos;

import java.util.List;

public class LoginResponseDTO {

    private String token;
    private String username;
    private List<String> roles;
    private Long id;

    public LoginResponseDTO() {
    }

    public LoginResponseDTO(String token, String username, List<String> roles, Long id) {
        this.token = token;
        this.username = username;
        this.roles = roles;
        this.id = id;
    }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public List<String> getRoles() { return roles; }
    public void setRoles(List<String> roles) { this.roles = roles; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
}
