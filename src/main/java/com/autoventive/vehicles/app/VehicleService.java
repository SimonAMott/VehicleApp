package com.autoventive.vehicles.app;

import java.io.IOException;
import java.util.List;

public interface VehicleService {

    List<Vehicle> parse(String json) throws IOException;

    List<Vehicle> discardVW(List<Vehicle> vehicles);

    List<Vehicle> discardBayNumbersGreaterThan100(List<Vehicle> vehicles);


    void createVehicles(List<Vehicle> vehicles);



    void createVehicle(Vehicle vehicle);
}
