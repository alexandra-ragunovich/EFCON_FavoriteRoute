package com.favorite_route.favorite_route.repository;

import com.favorite_route.favorite_route.entity.SavedRouteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FavoriteRouteRepository extends JpaRepository<SavedRouteEntity,Long> {

    List<SavedRouteEntity> findByUserId(Long userId);
}
