package com.favorite_route.favorite_route.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteRouteResponse {

    private Long id;
    private Long userId;
    private String fromCity;
    private String fromCountry;
    private String toCity;
    private String toCountry;
    private double totalDistanceKm;
    private double totalCo2Kg;
    private double totalDurationHours;
    private String aiAdviceText;
    private String status;
    private Long linkedPostId;
    private LocalDateTime createdAt;

    private List<FavoriteRouteSegmentResponse> segments;
}
