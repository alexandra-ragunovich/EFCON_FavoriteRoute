package com.favorite_route.favorite_route.service;

import com.favorite_route.favorite_route.Enum.RouteStatus;
import com.favorite_route.favorite_route.dto.request.FavoriteRouteRequest;
import com.favorite_route.favorite_route.dto.response.FavoriteRouteResponse;
import com.favorite_route.favorite_route.entity.SavedRouteEntity;
import com.favorite_route.favorite_route.entity.SavedRouteSegmentEntity;
import com.favorite_route.favorite_route.mapper.FavoriteRouteMapper;
import com.favorite_route.favorite_route.repository.FavoriteRouteRepository;
import com.favorite_route.favorite_route.grpc.DiaryGrpcClient;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final FavoriteRouteRepository favoriteRouteRepository;
    private  final FavoriteRouteMapper favoriteRouteMapper;
    private final DiaryGrpcClient diaryGrpcClient;

    @Transactional
    public FavoriteRouteResponse saveFavoriteRoute(Long userId, FavoriteRouteRequest favoriteRouteRequest){

        SavedRouteEntity savedRouteEntity=favoriteRouteMapper.toEntity(userId,favoriteRouteRequest);
        SavedRouteEntity saved=favoriteRouteRepository.save(savedRouteEntity);
        return favoriteRouteMapper.toResponse(saved);
    }

    public List<FavoriteRouteResponse> getFavoriteRoutes(Long userId){

        List<SavedRouteEntity> routes=favoriteRouteRepository.findByUserId(userId);
        return favoriteRouteMapper.toResponse(routes);
    }

    @Transactional
    public FavoriteRouteResponse markRouteAsCompleted(Long userId, Long routeId) {

        SavedRouteEntity route = getRouteAndCheckOwnership(userId, routeId);
        route.setStatus(RouteStatus.COMPLETED);
        SavedRouteEntity updated = favoriteRouteRepository.save(route);
        return favoriteRouteMapper.toResponse(updated);
    }

    @Transactional
    public void deleteFavoriteRoute(Long userId, Long routeId) {

        SavedRouteEntity route = getRouteAndCheckOwnership(userId, routeId);
        favoriteRouteRepository.delete(route);
    }

    @Transactional
    public FavoriteRouteResponse publishRouteToDiary(Long userId, Long routeId) {

        SavedRouteEntity route = getRouteAndCheckOwnership(userId, routeId);

        if (route.getStatus() != RouteStatus.COMPLETED) {
            throw new RuntimeException("Опубликовать можно только завершенные поездки (статус COMPLETED)");
        }

        Set<String> visitedPlaces = new LinkedHashSet<>();


      for(SavedRouteSegmentEntity segment : route.getSegments()){

            String cityInfo= segment.getToCity();
            String title=String.format("Моя остановка в %s (%s) ", segment.getToCity(),segment.getToCountry() );
            String content = String.format("Я посетил это место во время путешествия из %s в %s.\n",
                    route.getOriginCity(), route.getDestinationCity());

            content += String.format("Сюда я добрался из %s на транспорте: %s. Расстояние составило %.1f км.\n",
                    segment.getFromCity(), segment.getTransportType(), segment.getDistanceKm());

          Long postId = diaryGrpcClient.createDiaryPost(
                  userId,
                  title,
                  content,
                  cityInfo,
                  String.valueOf(segment.getDurationHours()) + " ч."
          );
          segment.setLinkedPostId(postId);

          System.out.println("Создан пост ID=" + postId + " для локации: " + cityInfo);

        }

        route.setStatus(RouteStatus.SHARED);
        SavedRouteEntity updated = favoriteRouteRepository.save(route);
        return favoriteRouteMapper.toResponse(updated);

    }

    private SavedRouteEntity getRouteAndCheckOwnership(Long userId, Long routeId) {

        SavedRouteEntity route = favoriteRouteRepository.findById(routeId)
                .orElseThrow(() -> new RuntimeException("Маршрут не найден"));

        if (!route.getUserId().equals(userId)) {
            throw new RuntimeException("Вы не являетесь владельцем маршрута");
        }

        return route;
    }
}
