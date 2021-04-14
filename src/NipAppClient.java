import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class NipAppClient extends javax.swing.JFrame {

    private JFrame frame = new JFrame();
    private JLabel checked = new JLabel("status");
    private JLabel nipLabel = new JLabel("podaj nip");
    private JButton checkButton = new JButton("sprawdz");
    private JTextField nipField = new JTextField();
    private JLabel nipStatus = new JLabel();
    private NipApp stub;


     NipAppClient(){
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in),1);

        try {
            stub = (NipApp)
                    Naming.lookup(
                            "rmi://localhost/NipService");
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } finally {
            frame.setSize(320,340);
            frame.setLayout(null);
            frame.setVisible(true);
            frame.setTitle("NIP");
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-320)/2,(Toolkit.getDefaultToolkit().getScreenSize().height-340)/2);

            nipLabel.setSize(100,20);
            nipLabel.setLocation(20,20);
            nipLabel.setVisible(true);
            frame.add(nipLabel);

            checkButton.setSize(100,40);
            checkButton.setLocation(125,50);
            checkButton.setVisible(true);
            checkButton.addActionListener(e -> checkNip(stub));
            frame.add(checkButton);

            nipField.setSize(150,20);
            nipField.setLocation(125,20);
            nipField.setVisible(true);
            frame.add(nipField);

            checked.setSize(100,20);
            checked.setLocation(20,100);
            checked.setVisible(true);
            frame.add(checked);

            nipStatus.setSize(100,20);
            nipStatus.setLocation(100,100);
            nipStatus.setVisible(true);
            frame.add(nipStatus);

        }
    }

    private void checkNip(NipApp stub) {
         if(stub==null)
         {
             nipStatus.setText("server error");
             nipStatus.setForeground(Color.RED);
         } else {
             try {
                 boolean status = stub.checkNip(nipField.getText());
                 if (status) {
                     nipStatus.setText("poprawny");
                     nipStatus.setForeground(Color.GREEN);
                 } else {
                     nipStatus.setText("niepoprawny");
                     nipStatus.setForeground(Color.RED);
                 }
             } catch (RemoteException e) {
                 e.printStackTrace();
             }
         }


    }

    public static void main(String[] args){
        NipAppClient client = new NipAppClient();
    }

}
