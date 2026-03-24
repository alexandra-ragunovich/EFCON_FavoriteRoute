package com.favorite_route.favorite_route.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteRouteRequest {

    private String originCity;
    private String originCountry;
    private String destinationCity;
    private String destinationCountry;
    private double totalDistanceKm;
    private double totalCo2Kg;
    private double totalDurationHours;
    private String aiAdviceText;

    private List<FavoriteRouteSegmentRequest> segments;
}
