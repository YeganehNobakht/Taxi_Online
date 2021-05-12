package maktabsharif.data.repository;

import maktabsharif.data.domain.Travel;

public interface TravelRepository {
    void saveTravel(Travel travel);
    Travel findTravelByDriverUsername(String id);
    int  updateTravel(Travel travel);
}
