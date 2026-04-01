package com.favorite_route.favorite_route.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name=SavedRouteSegmentEntity.TABLE_NAME)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SavedRouteSegmentEntity {

    public final static String TABLE_NAME="saved_route_segments";
    public final static String ID="id";
    public final static String ROUTE_ID="route_id";
    public final static String STEP_ORDER="step_order";
    public final static String TRANSPORT_TYPE="transport_type";
    public final static String TOTAL_DURATION_HOURS="total_duration_hours";
    public final static String DISTANCE_KM="distance_km";
    public final static String CO2_EMISSION_KG="co2_emission_kg";
    public final static String FROM_CITY = "from_city";
    public final static String FROM_COUNTRY = "from_country";
    public final static String TO_CITY = "to_city";
    public final static String TO_COUNTRY = "to_country";
    public final static String LINKED_POST_ID = "linked_post_id";

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name=ID)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = ROUTE_ID, nullable = false)
    private SavedRouteEntity route;

    @Column(name=STEP_ORDER,nullable = false)
    private int stepOrder;

    @Column(name = FROM_CITY, nullable = false)
    private String fromCity;

    @Column(name = FROM_COUNTRY, nullable = false)
    private String fromCountry;

    @Column(name = TO_CITY, nullable = false)
    private String toCity;

    @Column(name = TO_COUNTRY, nullable = false)
    private String toCountry;

    @Column(name=TRANSPORT_TYPE)
    private String transportType;

    @Column(name=TOTAL_DURATION_HOURS)
    private double durationHours;

    @Column(name=DISTANCE_KM)
    private double distanceKm;

    @Column(name=CO2_EMISSION_KG)
    private double co2EmissionKg;

    @Column(name = LINKED_POST_ID)
    private Long linkedPostId;


}
