package com.autoventive.vehicles;

import com.autoventive.vehicles.app.Vehicle;
import com.autoventive.vehicles.app.VehicleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class VehicleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VehicleService vehicleService;

    private String payload;
    private Vehicle vehicle;

    @BeforeEach
    public void setUp() {

        vehicle = new Vehicle("Mustang1", "Ford", 50);

        // Test payload
        payload = "[{\"VIN\": \"Mustang1\", \"manufacturer\": \"Ford\", \"bay_number\": 50}]";
    }

    @Test
    public void whenNewVehicles_thenResponseIsOk() throws Exception {
        when(vehicleService.parse(anyString())).thenReturn(Collections.singletonList(vehicle));
        when(vehicleService.discardVW(anyList())).thenReturn(Collections.singletonList(vehicle));
        when(vehicleService.discardBayNumbersGreaterThan100(anyList())).thenReturn(Collections.singletonList(vehicle));
        doNothing().when(vehicleService).createVehicles(anyList());

        mockMvc.perform(post("/vehicles")
                        .contentType("application/json")
                        .content(payload))
                .andExpect(status().isOk());

        verify(vehicleService, times(1)).parse(anyString());
        verify(vehicleService, times(1)).discardVW(anyList());
        verify(vehicleService, times(1)).discardBayNumbersGreaterThan100(anyList());
        verify(vehicleService, times(1)).createVehicles(anyList());
    }
}


