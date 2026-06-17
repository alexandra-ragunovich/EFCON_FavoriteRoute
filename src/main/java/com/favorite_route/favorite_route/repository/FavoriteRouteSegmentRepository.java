package com.favorite_route.favorite_route.repository;

import com.favorite_route.favorite_route.entity.SavedRouteSegmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRouteSegmentRepository extends JpaRepository<SavedRouteSegmentEntity, Long> {


}
