package com.autoventive.vehicles.app;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Vehicle {
    @Id
    @NonNull
    @JsonProperty("VIN")
    private String vin;

    @NonNull
    @JsonProperty("manufacturer")
    private String manufacturer;

    @JsonProperty("bay_number")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private int bayNumber;

}
