import java.util.*;

public class ParkingLot {
    public final int capacity;
    private final HashMap<String, Vehicle> vehicles;
  

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        this.vehicles = new HashMap<>();
        
    }

    public boolean addVehicle(String number) {
        if (vehicles.size() >= capacity || vehicles.containsKey(number)) return false;
        vehicles.put(number, new Vehicle(number));
        return true;
    }

public String removeVehicle(String number) {
    Vehicle v = vehicles.remove(number);
    if (v == null) return "❌ Vehicle not found!";
    
    long parkedSeconds = (new Date().getTime() - v.getEntryTime().getTime()) / 1000; 
    int fee = (int) parkedSeconds * 1; 

    return "Vehicle " + number + " exited.\n Parked time: " + parkedSeconds + " second(s)\n Parking Fee: ₹" + fee;
}

    public HashMap<String, Vehicle> getParkedVehicles() {
        return vehicles;
    }

    public int getAvailableSlots() {
        return capacity - vehicles.size();
    }

}
