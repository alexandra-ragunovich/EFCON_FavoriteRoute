package com.favorite_route.favorite_route.controller;

import com.favorite_route.favorite_route.dto.request.FavoriteRouteRequest;
import com.favorite_route.favorite_route.dto.response.FavoriteRouteResponse;
import com.favorite_route.favorite_route.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/favorite")
@RequiredArgsConstructor
public class FavoriteRouteController {

    private final FavoriteService favoriteService;

    @PostMapping()
    public FavoriteRouteResponse saveFavoriteRoute(@RequestParam Long userId,
                                                   @RequestBody FavoriteRouteRequest favoriteRouteRequest){
        return favoriteService.saveFavoriteRoute(userId,favoriteRouteRequest);
    }

    @GetMapping()
    public List<FavoriteRouteResponse> getFavoriteRoutes(@RequestParam Long userId){
        return favoriteService.getFavoriteRoutes(userId);
    }

    @PatchMapping("/{routeId}/complete")
    public FavoriteRouteResponse markRouteAsCompleted(
            @RequestParam Long userId,
            @PathVariable Long routeId) {

        return favoriteService.markRouteAsCompleted(userId, routeId);
    }

    @PostMapping("/{routeId}/publish")
    public FavoriteRouteResponse publishRouteToDiary(
            @RequestParam Long userId,
            @PathVariable Long routeId) {
        return favoriteService.publishRouteToDiary(userId, routeId);
    }


    @DeleteMapping("/{routeId}")
    public void deleteFavoriteRoute(
            @RequestParam Long userId,
            @PathVariable Long routeId) {

        favoriteService.deleteFavoriteRoute(userId, routeId);
    }

}
