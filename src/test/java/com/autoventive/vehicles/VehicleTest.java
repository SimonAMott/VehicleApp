package com.autoventive.vehicles;

import com.autoventive.vehicles.app.Vehicle;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class VehicleTest {

    private Vehicle vehicle;

    @BeforeEach
    public void setup(){
        vehicle = new Vehicle();
    }


    @Test
    public void whenSetVin_thenVinIsSet(){
        String vin = "MUSTABC1";
        vehicle.setVin(vin);
        assertThat(vehicle.getVin()).isEqualTo(vin);
    }

    @Test
    public void whenSetManufacturer_thenManufacturerIsSet(){
        String manufacturer = "GM";
        vehicle.setManufacturer(manufacturer);
        assertThat(vehicle.getManufacturer()).isEqualTo(manufacturer);
    }

    @Test
    public void whenSetBayNumber_thenBayNumberIsSet(){
        int bayNumber = 1;
        vehicle.setBayNumber(bayNumber);
        assertThat(vehicle.getBayNumber()).isEqualTo(bayNumber);
    }

    @Test
    void createNewVehicle_ManufacturerShouldRequireAValue(){

        assertThatThrownBy(()->
                new Vehicle("Must1", null, 1)).isInstanceOf(NullPointerException.class);

    }

    @Test
    void createNewVehicle_VinShouldRequireAValue(){
        assertThatThrownBy(()->
                new Vehicle(null, "GM", 1)).isInstanceOf(NullPointerException.class);

    }

    @Test
    void createNewVehicle_BayMayBeZero(){
        Assertions.assertThatCode(()->
                new Vehicle("Must1", "GM", 0)).doesNotThrowAnyException();

    }

    @Test
    void createNewVehicle_BayMayBeNegative(){
        Assertions.assertThatCode(()->
                new Vehicle("Must1", "GM", -1)).doesNotThrowAnyException();

    }

}