import java.rmi.RemoteException;

public class NipAppImpl extends java.rmi.server.UnicastRemoteObject
        implements NipApp {

    public NipAppImpl() throws java.rmi.RemoteException {
        super();
    }


    @Override
    public Boolean checkNip(String nip) throws RemoteException {
        if (nip.length() == 13) {
            nip = nip.replaceAll("-", "");
        }
        if (nip.length() != 10) {
            return false;
        }
        int[] weights = {6, 5, 7, 2, 3, 4, 5, 6, 7};
        try {
            int sum = 0;
            for (int i = 0; i < weights.length; ++i) {
                sum += Integer.parseInt(nip.substring(i, i + 1)) * weights[i];
            }
            return (sum % 11) == Integer.parseInt(nip.substring(9, 10));
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

