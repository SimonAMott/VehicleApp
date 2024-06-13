package com.autoventive.vehicles.app;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class VehicleServiceImpl implements VehicleService{
        private final VehicleRepository repository;

        @Autowired
        public VehicleServiceImpl(VehicleRepository repository){
            this.repository = repository;
        }

    public List<Vehicle> parse(String json) throws IOException {
        List<Vehicle> vehicleList;
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> parsedJson = mapper.readValue(json, new TypeReference<>() {
        });

        vehicleList =  mapper.convertValue(parsedJson.get("vehicles"), new TypeReference<>() {
        });

        return vehicleList;
    }

    public List<Vehicle> discardVW(List<Vehicle> vehicles){

            vehicles.removeIf(vehicle -> vehicle.getManufacturer().equals("VW"));

            return vehicles;

    }

    public List<Vehicle> discardBayNumbersGreaterThan100(List<Vehicle> vehicles){
            vehicles.removeIf(vehicle -> vehicle.getBayNumber()> 100);

            return vehicles;
    }


    public void createVehicles(List<Vehicle> vehicles){

            for(Vehicle vehicle: vehicles){
                createVehicle(vehicle);
            }
    }



    public void createVehicle(Vehicle vehicle){
        repository.save(vehicle);
    }
}
