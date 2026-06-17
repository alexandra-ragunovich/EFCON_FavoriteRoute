package com.favorite_route.favorite_route.entity;

import com.favorite_route.favorite_route.Enum.RouteStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name=SavedRouteEntity.TABLE_NAME)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SavedRouteEntity {

    public final static String TABLE_NAME="saved_routes";
    public final static String ID="id";
    public final static String USER_ID="user_id";
    public final static String TOTAL_DISTANCE_KM="total_distance_km";
    public final static String TOTAL_CO2_KG="total_co2_kg";
    public final static String TOTAL_DURATION_HOURS="total_duration_hours";
    public final static String AI_ADVICE_TEXT="ai_advice_text";
    public final static String STATUS="status";
    public final static String CREATED_AT="created_at";
    public final static String ORIGIN_CITY = "origin_city";
    public final static String ORIGIN_COUNTRY = "origin_country";
    public final static String DESTINATION_CITY = "destination_city";
    public final static String DESTINATION_COUNTRY = "destination_country";


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name=ID)
    private Long id;

    @Column(name=USER_ID,nullable = false)
    private Long userId;

    @Column(name=ORIGIN_CITY)
    private String originCity;

    @Column(name=ORIGIN_COUNTRY)
    private String originCountry;

    @Column(name=DESTINATION_CITY)
    private String destinationCity;

    @Column(name=DESTINATION_COUNTRY)
    private String destinationCountry;

    @Column(name = TOTAL_DISTANCE_KM)
    private double totalDistanceKm;

    @Column(name=TOTAL_CO2_KG)
    private double totalCo2Kg;

    @Column(name=TOTAL_DURATION_HOURS)
    private double totalDurationHours;

    @Column(name=AI_ADVICE_TEXT, columnDefinition = "TEXT")
    private String aiAdviceText;

    @Column(name=STATUS)
    @Enumerated(EnumType.STRING)
    private RouteStatus status = RouteStatus.PLANNED;


    @Column(name=CREATED_AT)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SavedRouteSegmentEntity> segments;

}
