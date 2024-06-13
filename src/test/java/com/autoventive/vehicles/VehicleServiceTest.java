package com.autoventive.vehicles;

import com.autoventive.vehicles.app.Vehicle;
import com.autoventive.vehicles.app.VehicleRepository;
import com.autoventive.vehicles.app.VehicleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class VehicleServiceTest {
    @Mock
    private VehicleRepository repository;

    @InjectMocks
    private VehicleServiceImpl service;

    private Vehicle vehicle1, vehicle2, vehicle3, vehicle4;
    private List<Vehicle> vehicles;

    @BeforeEach
    public void setup() {
        vehicle1 = new Vehicle("VIN1", "Ford", 99);
        vehicle2 = new Vehicle("VIN2", "VW", 50);
        vehicle3 = new Vehicle("VIN3", "GM", 80);
        vehicle4 = new Vehicle("VIN4", "GM", 101);
        vehicles = new ArrayList<>();
        vehicles.add(vehicle1);
        vehicles.add(vehicle2);
        vehicles.add(vehicle3);
        vehicles.add(vehicle4);
    }

    @Test
    public void discardVW_RemoveVehiclesByManufacturer() {
        var result = service.discardVW(vehicles);
        assertThat(result).doesNotContain(vehicle2);
    }

    @Test
    public void discardBayNumbersGreaterThan100_ShouldRemoveCorrectVehicles() {
        var result = service.discardBayNumbersGreaterThan100(vehicles);
        assertThat(result).doesNotContain(vehicle4);
    }

    @Test
    public void createVehicles_ShouldCallRepoMethodForEachVehicle() {
        service.createVehicles(vehicles);
        verify(repository, times(vehicles.size())).save(any(Vehicle.class));
    }

    @Test
    public void createVehicle_ShouldCallRepoSaveMethod() {
        when(repository.save(any())).thenReturn(vehicle1);
        service.createVehicle(vehicle1);
        verify(repository).save(vehicle1);
    }

    @Test
    public void parse_ValidJson_ReturnsVehicleList() throws IOException {
        String json = "{\"vehicles\": [{\"VIN\": \"Must1\",\"manufacturer\": \"Ford\",\"bay_number\": 60}]}";
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = objectMapper.readValue(json, new TypeReference<>() {});
        var expected = objectMapper.convertValue(map.get("vehicles"), new TypeReference<List<Vehicle>>() {});

        var result = service.parse(json);
        assertThat(result).containsExactlyElementsOf(expected);
    }
}
