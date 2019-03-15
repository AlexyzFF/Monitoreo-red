package redes;

import java.io.Serializable;

/**
 *
 * @author Alexyz
 */
public class Orden implements Serializable
{
    private String ip;
    private String orden;

    public Orden(String ip, String orden)
    {
        this.ip = ip;
        this.orden = orden;
    }

    public Orden() 
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

    public String getOrden() 
    {
        return orden;
    }

    public void setOrden(String orden) 
    {
        this.orden = orden;
    }
}
