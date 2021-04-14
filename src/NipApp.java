import java.rmi.Remote;
import java.rmi.RemoteException;

public interface NipApp extends Remote {
        public Boolean checkNip(String nip) throws RemoteException;

}
