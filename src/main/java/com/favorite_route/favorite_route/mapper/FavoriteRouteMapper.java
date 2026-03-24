package com.favorite_route.favorite_route.mapper;

import com.favorite_route.favorite_route.Enum.RouteStatus;
import com.favorite_route.favorite_route.dto.request.FavoriteRouteRequest;
import com.favorite_route.favorite_route.dto.request.FavoriteRouteSegmentRequest;
import com.favorite_route.favorite_route.dto.response.FavoriteRouteResponse;
import com.favorite_route.favorite_route.dto.response.FavoriteRouteSegmentResponse;
import com.favorite_route.favorite_route.entity.SavedRouteEntity;
import com.favorite_route.favorite_route.entity.SavedRouteSegmentEntity;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FavoriteRouteMapper {

   public  SavedRouteEntity toEntity(Long userId, FavoriteRouteRequest request){

       SavedRouteEntity savedRouteEntity=new SavedRouteEntity();
       savedRouteEntity.setUserId(userId);
       savedRouteEntity.setStatus(RouteStatus.PLANNED);
       savedRouteEntity.setOriginCity(request.getOriginCity());
       savedRouteEntity.setOriginCountry(request.getOriginCountry());
       savedRouteEntity.setDestinationCity(request.getDestinationCity());
       savedRouteEntity.setDestinationCountry(request.getDestinationCountry());
       savedRouteEntity.setTotalDistanceKm(request.getTotalDistanceKm());
       savedRouteEntity.setTotalCo2Kg(request.getTotalCo2Kg());
       savedRouteEntity.setTotalDurationHours(request.getTotalDurationHours());
       savedRouteEntity.setAiAdviceText(request.getAiAdviceText());

       if (request.getSegments() != null) {

           List<SavedRouteSegmentEntity> segmentEntities = new ArrayList<>();
           int stepOrder = 1;

           for (FavoriteRouteSegmentRequest segRequest : request.getSegments()) {

               SavedRouteSegmentEntity segEntity = toSegmentEntity(segRequest);
               segEntity.setStepOrder(stepOrder++);
               segEntity.setRoute(savedRouteEntity);
               segmentEntities.add(segEntity);

           }
           savedRouteEntity.setSegments(segmentEntities);
       }

       return savedRouteEntity;
   }

    public SavedRouteSegmentEntity toSegmentEntity(FavoriteRouteSegmentRequest request) {

        SavedRouteSegmentEntity entity = new SavedRouteSegmentEntity();
        entity.setFromCity(request.getFromCity());
        entity.setFromCountry(request.getFromCountry());
        entity.setToCity(request.getToCity());
        entity.setToCountry(request.getToCountry());
        entity.setTransportType(request.getTransportType());
        entity.setDistanceKm(request.getDistanceKm());
        entity.setDurationHours(request.getDurationHours());
        entity.setCo2EmissionKg(request.getCo2EmissionKg());

        return entity;
    }

    public FavoriteRouteResponse toResponse(SavedRouteEntity entity){

        FavoriteRouteResponse favoriteRouteResponse=new FavoriteRouteResponse();
        favoriteRouteResponse.setId(entity.getId());
        favoriteRouteResponse.setUserId(entity.getUserId());
        favoriteRouteResponse.setFromCity(entity.getOriginCity());
        favoriteRouteResponse.setFromCountry(entity.getOriginCountry());
        favoriteRouteResponse.setToCity(entity.getDestinationCity());
        favoriteRouteResponse.setToCountry(entity.getDestinationCountry());
        favoriteRouteResponse.setTotalDistanceKm(entity.getTotalDistanceKm());
        favoriteRouteResponse.setTotalCo2Kg(entity.getTotalCo2Kg());
        favoriteRouteResponse.setTotalDurationHours(entity.getTotalDurationHours());
        favoriteRouteResponse.setAiAdviceText(entity.getAiAdviceText());
        favoriteRouteResponse.setStatus(entity.getStatus() != null ? entity.getStatus().name() : null);
        favoriteRouteResponse.setLinkedPostId(entity.getLinkedPostId());
        favoriteRouteResponse.setCreatedAt(entity.getCreatedAt());

        if (entity.getSegments() != null) {
            favoriteRouteResponse.setSegments(
                    entity.getSegments().stream()
                            .map(this::toSegmentResponse)
                            .collect(Collectors.toList())
            );
        }

        return favoriteRouteResponse;
    }

    public List<FavoriteRouteResponse> toResponse(List<SavedRouteEntity> entities) {
        return entities.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public FavoriteRouteSegmentResponse toSegmentResponse(SavedRouteSegmentEntity entity) {

        FavoriteRouteSegmentResponse response = new FavoriteRouteSegmentResponse();
        response.setId(entity.getId());
        response.setFromCity(entity.getFromCity());
        response.setFromCountry(entity.getFromCountry());
        response.setToCity(entity.getToCity());
        response.setToCountry(entity.getToCountry());
        response.setTransportType(entity.getTransportType());
        response.setDistanceKm(entity.getDistanceKm());
        response.setDurationHours(entity.getDurationHours());
        response.setCo2EmissionKg(entity.getCo2EmissionKg());

        return response;
    }

}
