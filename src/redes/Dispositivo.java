package redes;

/**
 *
 * @author Alexyz
 */
public class Dispositivo 
{
    private String ip;
    private String nombre;
    private String mac;

    public Dispositivo(String ip, String nombre, String mac) 
    {
        this.ip = ip;
        this.nombre = nombre;
        this.mac = mac;
    }

    public Dispositivo() 
    {
        
    }

    public String getIp() 
    {
        return ip;
    }

    public void setIp(String ip) 
    {
        this.ip = ip;
    }

    public String getNombre() 
    {
        return nombre;
    }

    public void setNombre(String nombre) 
    {
        this.nombre = nombre;
    }

    public String getMac() 
    {
        return mac;
    }

    public void setMac(String mac)
    {
        this.mac = mac;
    }
}
