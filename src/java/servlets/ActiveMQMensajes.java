/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
//import org.apache.activemq.command.ActiveMQDestination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.management.MBeanServerConnection;
import javax.management.MBeanServerInvocationHandler;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.jmx.DestinationViewMBean;
import org.apache.activemq.ActiveMQConnection;


/**
 *
 * @author fabia
 */
public class ActiveMQMensajes {
    
    public static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
    
    public static void mandaMensaje(Mensaje msg, String colaParaMandar){
        MessageProducer messageProducer;
        ObjectMessage obj;
        try {

            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
            Connection connection = connectionFactory.createConnection();
            connection.start();

            Session session = connection.createSession(false /*Transacter*/, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(colaParaMandar);

            messageProducer = session.createProducer(destination);
            
            obj = session.createObjectMessage();
            obj.setObject(msg);
            
            messageProducer.send(obj);

            messageProducer.close();
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
    
    public static Mensaje recibeMensaje(String colaParaRecibir){
        if(colaParaRecibir==null){
            return null;
        }
        Mensaje p = null;
        boolean goodByeReceived = false;

        try {
            //ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
            connectionFactory.setTrustAllPackages(true); 
            Connection connection = connectionFactory.createConnection();
            connection.start();

            Session session = connection.createSession(false /*Transacter*/, Session.AUTO_ACKNOWLEDGE);

            Destination destination = session.createQueue(colaParaRecibir);
            
            
            MessageConsumer messageConsumer = session.createConsumer(destination);

            while(!goodByeReceived){
                ObjectMessage objectMessage = (ObjectMessage) messageConsumer.receive(500);
                if (objectMessage != null) {
                    System.out.print("Received the following message: ");
                    p = (Mensaje) objectMessage.getObject();
                    System.out.println(p.toString());
                    System.out.println();   
                }
                goodByeReceived = true;
            }
            
                
            messageConsumer.close();
            session.close();
            connection.close();

        } catch (JMSException e) {
            e.printStackTrace();
        }
        return p;
    }
    
    //https://stackoverflow.com/questions/10795220/how-to-get-all-enqueued-messages-in-activemq
    public static ArrayList<Mensaje> recibeTODOMensaje(String colaParaRecibir){
        ArrayList<Mensaje> p = new ArrayList();
        boolean goodByeReceived = false;

        try {
            //ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
            connectionFactory.setTrustAllPackages(true); 
            Connection connection = connectionFactory.createConnection();
            connection.start();

            Session session = connection.createSession(false /*Transacter*/, Session.AUTO_ACKNOWLEDGE);
            Queue destination = session.createQueue(colaParaRecibir);
            MessageConsumer messageConsumer = session.createConsumer(destination);

            while (!goodByeReceived) {  
            
                System.out.println("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
                Message msg = messageConsumer.receive();
            
                System.out.println(msg.toString());
                System.out.println("\n\n\n\n\n\n\n\n\n-----------------------------\n\n\n\n\n\n\n\n\n\n\n");
            
            if (msg instanceof Mensaje) {
                Mensaje m = (Mensaje) msg;
                p.add(m);
            }
            else{
                goodByeReceived= !goodByeReceived;
                System.out.println("Queue Empty"); 
                messageConsumer.close();
                session.close();
                connection.close();
                break;
            }
        }          

        } catch (JMSException e) {
        }
        return p;
    }
    
    public static ArrayList<Mensaje> recibeTODO(String colaParaRecibir) throws JMSException{
        ArrayList<Mensaje> p = new ArrayList();
        boolean goodByeReceived = false;

        try {
            ConnectionFactory factory =  new ActiveMQConnectionFactory(url);
            Connection con = factory.createConnection();
            Session session =   con.createSession(false, Session.AUTO_ACKNOWLEDGE);
            System.out.println("\n\n\n\n\n\n\n\n\n\n\nCreacion de la cola\n\n\n\n\n\n\n\n\n\n");
            
            
            Destination destination = session.createQueue(colaParaRecibir);
            
            
            MessageConsumer messageConsumer = session.createConsumer(destination);
            //Queue queue = session.createQueue(colaParaRecibir);
            //MessageConsumer consumer = session.createConsumer(queue);
            con.start();
            
            int count = 0;
            while (!goodByeReceived) {
                System.out.println("Entra en el loop:"+count++);
                Message msg = messageConsumer.receive(5000); 
                System.out.println("Se recibió mensaje...");
                System.out.println(msg==null);
                //System.out.println(msg.toString());
                if (msg != null) {
                    System.out.println("**********************************************************************\n\n\n\n\n\n\n\n");
                    Mensaje tm = (Mensaje) msg;
                    System.out.println(tm.toString());
                    System.out.println("**********************************************************************\n\n\n\n\n\n\n\n");
                    p.add(tm);
                }
                else{
                    System.out.println("Queue Empty"); 
                    con.stop();
                    goodByeReceived=!goodByeReceived;
                    break;
                }
            }
                  

        } catch (Exception  e) {
            System.out.println("-->"+e.toString());
        }
        return p;
    }
    
}
