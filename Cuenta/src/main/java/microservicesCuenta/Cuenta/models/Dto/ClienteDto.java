package microservicesCuenta.Cuenta.models.Dto;

public class ClienteDto {

    private int idcliente;
    private int idpersona;
    private String clienteid;
    private String contrasena;
    private Boolean estadocliente;


    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public int getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(int idpersona) {
        this.idpersona = idpersona;
    }

    public String getClienteid() {
        return clienteid;
    }

    public void setClienteid(String clienteid) {
        this.clienteid = clienteid;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Boolean getEstadocliente() {
        return estadocliente;
    }

    public void setEstadocliente(Boolean estadocliente) {
        this.estadocliente = estadocliente;
    }
}
