package redes;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.*;

/**
 *
 * @author Alexyz
 */
public class FormCliente extends JFrame implements ActionListener
{
    private JLabel labelLogo;
    private JLabel labelRango;
    
    private JTextField campoIp1;
    private JLabel labelPunto1;
    private JTextField rangoIp1;
    
    private JTextField campoIp2;
    private JLabel labelPunto2;
    private JTextField rangoIp2;
    
    private JButton botonRango;
    private JTextArea areaRangos;
    private JScrollPane scrollPane;
    
    private JButton botonComando;
    private JTextArea areaComando;
    private JScrollPane scrollPane2;
    private JComboBox comboComandos;
    
    //-------------------CLIENTE
    private JLabel labelIp;
    private JComboBox comboIp;
    private JLabel labelAccion;
    private JComboBox comboAccion;
    
    private JTextField campoIp;
    private JTextField campoOrden;
    private JButton botonEnviar;
    
    private ArrayList<Dispositivo> dispositivosConectados;
    
    public static void main(String args[])
    {
        FormCliente interfaz=new FormCliente();
    }
    
    public FormCliente()
    {
        setLayout(null);
        setSize(1010,600);
        this.setLocationRelativeTo(null);
        setTitle("Redes-UPIIZ");
        
        this.labelLogo=new JLabel();
        this.labelLogo.setSize(100, 100);
        this.labelLogo.setBounds(10, 10, 120, 120);
        ImageIcon logo = new javax.swing.ImageIcon("src/img/upiizLogo.png");
        Icon icono=new ImageIcon(logo.getImage().getScaledInstance(labelLogo.getWidth(), labelLogo.getHeight(), Image.SCALE_DEFAULT));
        this.labelLogo.setIcon(icono);

        this.labelRango=new JLabel("Rango:");
        this.labelRango.setBounds(160, 50, 150, 30);
        
        this.campoIp1=new JTextField();
        this.campoIp1.setBounds(210, 30, 150, 30);
        
        this.labelPunto1=new JLabel(".");
        this.labelPunto1.setBounds(380, 30, 10, 30);
        
        this.rangoIp1=new JTextField();
        this.rangoIp1.setBounds(400, 30, 50, 30);
        
        //------------------------------------------
        
        this.campoIp2=new JTextField();
        this.campoIp2.setBounds(210, 70, 150, 30);
        
        this.labelPunto2=new JLabel(".");
        this.labelPunto2.setBounds(380, 70, 10, 30);
        
        this.rangoIp2=new JTextField();
        this.rangoIp2.setBounds(400, 70, 50, 30);
      
        this.botonRango=new JButton("Buscar en Rango");
        this.botonRango.setBounds(250, 170, 200, 30);
        this.botonRango.addActionListener(this);
        
        this.areaRangos= new JTextArea();
        this.scrollPane = new JScrollPane(areaRangos);
        this.scrollPane.setHorizontalScrollBarPolicy (ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.scrollPane.setVerticalScrollBarPolicy (ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.scrollPane.setBounds(50, 250, 400, 300);
        
        //----------------------------------------------------------
        this.botonComando=new JButton("Ejecutar");
        this.botonComando.setBounds(550, 170, 100, 30);
        this.botonComando.addActionListener(this);
        
        this.comboComandos=new JComboBox();
        this.comboComandos.addItem("arp -a");
        this.comboComandos.addItem("netstat");
        this.comboComandos.addItem("netstat -a");
        this.comboComandos.addItem("netstat -n");
        this.comboComandos.addItem("netstat -e");
        this.comboComandos.addItem("netstat -r");
        this.comboComandos.addItem("netstat -s");
        this.comboComandos.addItem("getmac");
        this.comboComandos.setBounds(750, 170, 200, 30);
        add(this.comboComandos);
        
        this.areaComando= new JTextArea();
        
        this.scrollPane2 = new JScrollPane(areaComando);
        this.scrollPane2.setHorizontalScrollBarPolicy (ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.scrollPane2.setVerticalScrollBarPolicy (ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.scrollPane2.setBounds(550, 250, 400, 300);
        
        //------------------------------------------CLIENTE
        this.labelIp=new JLabel("Ip:");
        this.labelIp.setBounds(550, 30, 100, 30);
        
        this.comboIp=new JComboBox();
        this.comboIp.setBounds(630,30,130,30);
        
        this.labelAccion=new JLabel("Accion:");
        this.labelAccion.setBounds(550, 70, 100, 30);
        
        this.comboAccion=new JComboBox();
        this.comboAccion.setBounds(630,70,130,30);
        
        this.comboAccion.addItem("Apagar");
        this.comboAccion.addItem("Reiniciar");
        this.comboAccion.addItem("Calculadora");
        
        //---------------------------------------------------
        this.botonEnviar=new JButton("Ejecutar Acción");
        this.botonEnviar.setBounds(800, 50, 150, 30);
        this.botonEnviar.addActionListener(this);
        
        add(labelLogo);
        add(this.botonRango);
        add(this.botonComando);
        add(this.scrollPane);
        add(this.scrollPane2);
        
        //CLIENTE
        add(this.labelIp);
        add(this.comboIp);
        add(this.labelAccion);
        add(this.comboAccion);
       
        add(this.botonEnviar);
        add(this.labelRango);
        add(this.campoIp1);
        add(this.labelPunto1);
        add(this.rangoIp1);
        add(this.campoIp2);
        add(this.labelPunto2);
        add(this.rangoIp2);
   
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource()==botonRango)
        {
            try
            {
                String subred;
                this.dispositivosConectados=new ArrayList<Dispositivo>();
                int rango1=0;
                int rango2=0;
                int timeout=1000;
                InetAddress ip=null;
                
                NetworkInterface red=NetworkInterface.getByInetAddress(InetAddress.getLocalHost());
                byte mac[]=red.getHardwareAddress();
                
                StringBuilder sb=new StringBuilder();
                
                for (int i = 0; i < mac.length; i++) 
                {
                    sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
                }
                
                System.out.println(sb.toString());
                
                subred=new String();
               // subred="10.5.3";
               subred=this.campoIp1.getText();
                
               // rango1=1;
                //rango2=25;
                rango1=Integer.parseInt(this.rangoIp1.getText());
                rango2=Integer.parseInt(this.rangoIp2.getText());
                
                for (int i = rango1; i <= rango2; i++) 
                {
                    String host=subred+"."+i;
                    ip=InetAddress.getByName(host); 
                    System.out.println("Leyendo..."+ip);
                 
                    if(ip.isReachable(timeout))
                    {
                        String msj=new String();
                        String nombreHost=ip.getHostName();
                        String direccionMac="";
                        char in=nombreHost.charAt(0);
                        
                        if(in>48 && in<58)
                        {
                            nombreHost="";
                        }
                        else
                        {
                            nombreHost=ip.getHostName();
                        }
                        
                        if(host.equals(InetAddress.getLocalHost().getHostAddress()))
                        {
                            direccionMac = sb.toString();
                        }
                        else
                        {
                            if(i==1)
                            {
                                msj="Gateway";
                            }
                            else
                            {
                                msj = "";
                            }       
                        }
                        
                        Dispositivo dis = new Dispositivo(ip.getHostAddress(),nombreHost,direccionMac);
                        dispositivosConectados.add(dis);   
                    }
                }
                
                String informacion="  RESULTADOS:\n\n";
                for (Dispositivo d : dispositivosConectados) 
                {
                    informacion=informacion+("  IP:"+d.getIp()+"\n  MAC:"+d.getMac()+"\n  HostName:"+d.getNombre()+"\n-------------------------------------------------------------------------------------\n");
                }
                
                for (Dispositivo d : dispositivosConectados) 
                {
                    String ipConectada=d.getIp();
                    this.comboIp.addItem(ipConectada);
                }
                
                this.areaRangos.setText(informacion);
            }
            catch(Exception ex)
            {
                System.out.println(ex.getStackTrace());
            }
        }
        if(e.getSource()==botonComando)
        {
            String resultado="";
            this.areaComando.setText("");
            String comando=this.comboComandos.getSelectedItem().toString();
           
            try
                {
                    Runtime r=Runtime.getRuntime();
                    Process proceso=r.exec(comando);

                    BufferedReader br=new BufferedReader(new InputStreamReader(proceso.getInputStream()));

                    String linea="";

                    while((linea=br.readLine())!=null)
                    {
                        System.out.println(linea);
                        resultado+="  "+linea+"\n";
                    }
                    System.out.println(linea);
                    this.areaComando.setText(resultado.toString());
                    br.close();
                }
                catch(Exception ex)
                {
                    System.out.println(ex.getMessage());
                }
        }
        
        if(e.getSource()==this.botonEnviar) //BOTON EJECUTAR ACCION
        {     
            try
            {
                //PASARLE LA IP SELECCIONADA DEL COMBOBOX IP
                String ipConectada=this.comboIp.getSelectedItem().toString();
                
                Socket cliente=new Socket(ipConectada,9090);
                Orden orden=new Orden();
                
                String ip=this.comboIp.getSelectedItem().toString();
                String accion=this.comboAccion.getSelectedItem().toString();
                
                
                System.out.println(ip);
                System.out.println(accion);
                                      
                orden.setIp(ip);
                orden.setOrden(accion);
                
                ObjectOutputStream flujo=new ObjectOutputStream(cliente.getOutputStream());
                flujo.writeObject(orden);
                cliente.close();
            }
            catch(Exception ex)
            {
                System.out.println("Error");
            }
        }
    }
}
