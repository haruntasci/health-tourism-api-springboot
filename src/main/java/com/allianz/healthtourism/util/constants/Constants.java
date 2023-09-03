package com.allianz.healthtourism.util.constants;

public class Constants {
    public static final String DELETE_SUCCESS_MESSAGE = "Delete success";
    public static final String DELETE_ERROR_MESSAGE = "Delete error";
    public static final String DEPARTURE_FLIGHT_TIME_ERROR_MESSAGE = "" +
            "Arrival time must be at least 4 hours before the appointment time";
    public static final String RETURN_FLIGHT_TIME_ERROR_MESSAGE = "" +
            "Return time must be at least 4 hours after the appointment time";
    public static final String CHECK_IN_TIME_ERROR_MESSAGE = "" +
            "Check-in time must be at least 1 day before the appointment time";
    public static final String CHECK_OUT_TIME_ERROR_MESSAGE = "" +
            "Check-out time must be at least 1 day after the appointment time";
    public static final String DEPARTURE_FLIGHT_DEPARTURE_CITY_ERROR_MESSAGE = "" +
            "Departure city must be same as the patients' city";
    public static final String DEPARTURE_FLIGHT_ARRIVAL_CITY_ERROR_MESSAGE = "" +
            "Arrival city must be same as doctors' city.";
    public static final String RETURN_FLIGHT_DEPARTURE_CITY_ERROR_MESSAGE = "" +
            "Departure city must be same as doctors' city.";
    public static final String RETURN_FLIGHT_ARRIVAL_CITY_ERROR_MESSAGE = "" +
            "Arrival city must be same as patients' city.";
    public static final String HOTEL_CITY_ERROR_MESSAGE = "" +
            "Hotel city must be same as doctors' city.";
    public static final long SCHEDULER_DELAY = 1;
}
