import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ParkingLot lot = new ParkingLot(10);
                new UI(lot);
            }
        });
    }
}
