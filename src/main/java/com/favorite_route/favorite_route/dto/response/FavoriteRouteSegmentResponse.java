package com.favorite_route.favorite_route.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteRouteSegmentResponse {

    private Long id;
    private String fromCity;
    private String fromCountry;
    private String toCity;
    private String toCountry;
    private String transportType;
    private double distanceKm;
    private double durationHours;
    private double co2EmissionKg;
}
