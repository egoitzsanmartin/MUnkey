package serial;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

public class Serial extends JFrame implements ActionListener, PropertyChangeListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JComboBox<String> cbPuertos;
	JButton btConnect, btDisconnect;
	JLabel lbInfo;
	List<CommPortIdentifier> listaPuertos;
	List<CommPortIdentifier> puertosUsados;
	static Robot robot;
	public int PosY = 400;
    public int PosX = 400;
	PropertyChangeSupport soporte;
	public SerialWriter writer;
	SerialReader reader;
	final int sendL = 'l';
	
	public Serial() {
		super("Conexión Periférico");
		this.listaPuertos = new ArrayList<>();
		this.soporte = new PropertyChangeSupport(this);
		
		try
	    {
			robot = new Robot();
	    }
	    catch (AWTException e)
	    {
	    	e.printStackTrace();
	    }
		
		this.setSize (400,300);
		this.setLocation(400,200);
		this.getContentPane().add(crearPanelVentana(), BorderLayout.NORTH);
		this.getContentPane().add(crearPanelLabel(), BorderLayout.CENTER);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	private Component crearPanelLabel() {
		JPanel panel = new JPanel();
		lbInfo = new JLabel("");
		lbInfo.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lbInfo);
		return panel;
	}
	
	public void getPortList() {
		Enumeration<CommPortIdentifier> puertos = CommPortIdentifier.getPortIdentifiers();
		while(puertos.hasMoreElements()) {
			CommPortIdentifier com = ((CommPortIdentifier) puertos.nextElement());
			switch(com.getPortType()) {
			case CommPortIdentifier.PORT_SERIAL:
				if(!com.isCurrentlyOwned()) this.listaPuertos.add(com);
				break;
			}
		}
	}
	
	public String[] getPortListIdentifiers(List<CommPortIdentifier> lista) {
		List<String> nombre = new ArrayList<>();
		for(CommPortIdentifier puerto : lista) {
			nombre.add(puerto.getName());
		}
		return nombre.toArray(new String[0]);
	}
	
	private Container crearPanelVentana() {
		JPanel panel = new JPanel(new GridLayout(3,1,10,10));
		this.getPortList();
		cbPuertos = new JComboBox<>(getPortListIdentifiers(this.listaPuertos));
		
		btConnect = new JButton("Connect!");
		btConnect.addActionListener(this);
		
		panel.add(crearPanelFlow(cbPuertos));
		panel.add(crearPanelFlow(btConnect));
		panel.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
		return panel;
	}
	
	private Component crearPanelFlow(Component component) {
		JPanel panel = new JPanel(new FlowLayout());
		panel.add(component);
		return panel;		
	}

	void connect ( String portName, Serial ventana ) throws Exception
    {

        CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
        if ( portIdentifier.isCurrentlyOwned() )
        {
            System.out.println("Error: Port is currently in use");
        }
        else
        {
            CommPort commPort = portIdentifier.open(this.getClass().getName(),2000);
            
            if ( commPort instanceof SerialPort )
            {
                SerialPort serialPort = (SerialPort) commPort;
                serialPort.setSerialPortParams(9600,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
                
                InputStream in = serialPort.getInputStream();
                OutputStream out = serialPort.getOutputStream();
                writer = new SerialWriter(out);               
                (new Thread(writer)).start();
                
                reader = new SerialReader(in, this);
                serialPort.addEventListener(reader);
                serialPort.notifyOnDataAvailable(true);
                this.addPropertyChangeListener(ventana);
                lbInfo.setText("Conexión establecida con el puerto "+this.listaPuertos.get(cbPuertos.getSelectedIndex()).getName()+"...");
                this.dispose();
            }
            else
            {
                System.out.println("Error: Only serial ports are handled by this example.");
            }
        }     
    }

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		soporte.addPropertyChangeListener(listener);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btConnect) {
			lbInfo.setText("Intentando conexión a "+this.listaPuertos.get(cbPuertos.getSelectedIndex()).getName()+"...");
			try {
	            this.connect((String)cbPuertos.getSelectedItem(), this);
	            //this.puertosUsados.add(this.listaPuertos.get(cbPuertos.getSelectedIndex()));
	        }
	        catch ( Exception e1 ) {
	            // TODO Auto-generated catch block
	        	lbInfo.setText("No se pudo establecer la conexión :(");
	            e1.printStackTrace();
	        }
			
		}
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent e) {
		if(e.getPropertyName().equals("serialConnect")) {
			lbInfo.setText("Conexión establecida con "+e.getNewValue());
			Serial.this.repaint();
		}
		if(e.getPropertyName().equals("recibido")) {
			char caracter = (char) e.getNewValue();
			switch(caracter) {
			case 'U':
				PosY = PosY - 20;
				robot.mouseMove((PosX), (PosY));
				break;
			case 'D':
				PosY = PosY + 20;
				robot.mouseMove((PosX), (PosY));
				break;
			case 'R':
				PosX = PosX + 20;
				robot.mouseMove((PosX), (PosY));
				break;
			case 'L':
				PosX = PosX - 20;
				robot.mouseMove((PosX), (PosY));
				break;
			case 'C':
				robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
				break;
			}
		}
		if(e.getPropertyName().equals("enviado")) {
			this.writer.sendData((int) e.getNewValue());
		}
	}
	
	public static class SerialReader implements SerialPortEventListener 
    {
        private InputStream in;
        private byte[] buffer = new byte[1024];
        PropertyChangeSupport soporte;
        
        public SerialReader ( InputStream in, Serial ventana )
        {
            this.in = in;
            this.soporte = new PropertyChangeSupport(this);
            this.addPropertyChangeListener(ventana);
        }
        
        public void serialEvent(SerialPortEvent arg0) {
            int data;
          
            try
            {
                int len = 0;
                while ( ( data = in.read()) > -1 )
                {
                    /*if ( data == 'w' ) {
                        break;
                    }*/
                    buffer[len++] = (byte) data;
                }
                String comando = new String(buffer,0,len);
                soporte.firePropertyChange("recibido", null, comando.charAt(0));
            }
            catch ( IOException e )
            {
                e.printStackTrace();
                System.exit(-1);
            }             
        }
        
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        	soporte.addPropertyChangeListener(listener);
        }

    }
	
	public static class SerialWriter implements Runnable 
	{
		int info = 'P';
	    OutputStream out;
	    
	    public SerialWriter ( OutputStream out )
	    {
	        this.out = out;
	    }
	    
	    public void run ()
	    {
	        try
	        {               
	        	 int c = 0;
	             while ( ( c = System.in.read()) > -1 )
	             {
	                 this.out.write(info);
	             }    
	        }
	        catch ( IOException e )
	        {
	            e.printStackTrace();
	            System.exit(-1);
	        }            
	    }
	    public void sendData(int data) {
	    	try
	        {             
	    		this.out.write(data);             
	        }
	        catch ( IOException e )
	        {
	            e.printStackTrace();
	            System.exit(-1);
	        } 
	    }
	    
	}
	
}