package com.autoventive.vehicles.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class VehicleController {

    private final VehicleService service;

    @Autowired
    public VehicleController(VehicleService service){
        this.service = service;
    }

    @PostMapping("/vehicles")
    void newVehicles(@RequestBody String payload) throws IOException {
        List<Vehicle> vehicleList = service.parse(payload);
        service.discardVW(vehicleList);

        service.discardBayNumbersGreaterThan100(vehicleList);
        service.createVehicles(vehicleList);

    }


}
