# Health Tourism API
👨‍⚕️🩺👴🏥🛌💉💊⚕️✈️🏨
## Table of Contents
- [Description](#description)
- [Requirements](#requirements)
- [Installation](#installation)
- [Class Diagram](#class-diagram)
- [Roles](#roles)
- [Usage](#usage)
- [Postman](#postman)

## Description

The Health Tourism Application is a platform that facilitates patients' travel to different countries for receiving healthcare services. 
The application aims to accelerate the process of patients receiving services from good doctors and hospitals abroad.
The patient can perform all transactions on this site instead of wasting time on other sites for hotel and flight reservations.

### Requirements

- IntelliJ IDEA(Ultimate preferred)
- PostgreSQL

### Installation

```
1. git clone https://github.com/haruntasci/health-tourism-api-springboot.git

2. Edit properties according to your database information
   spring.datasource.url=jdbc:postgresql:yourURL
   spring.datasource.username=yourUsername
   spring.datasource.password=yourPassword

3. In Edit Configurations section write as Active profiles: dev
```  
### Class Diagram
![health-tourism-diag](https://github.com/haruntasci/health-tourism-api-springboot/assets/99567926/ff294842-a4ca-419a-acc9-aa7a0d68f256)

## Roles
There are 3 roles:
1. Admin Role
- Can access all endpoints.
- Creates countries, cities, hospitals, doctors, flights, hotels.
2. Patient Role
- Can access city(read-only), flight(read-only), hotel(read-only), flight-booking, hotel-booking, doctor(read-only), patient, appointment.
3. Doctor Role
- Can access appointment, doctor, patient(read-only) and medical-record endpoints.

## Usage
1. Admin creates countries, cities, hospitals, doctors, flights, hotels.
2. Patient creates a patient and an appointment. To create appointment, Patient selects doctor and sets the appointment date.
3. The patient then chooses suitable departure and return flights based on the appointment, creating a flight booking.
- Departure flights' arrival time must be at least 4 hours before the appointment time.
- Return flights' departure time must be at least 4 hours after the appointment time.
- Departure flights' departure city must be the same as the patient's city.
- Departure flights' arrival city must be the same as the doctor's city.
- Return flights' departure city must be the same as the doctor's city.
- Return flights' arrival city must be the same as the patient's city.
- Patient selects suitable hotel according to appointment date and city, and creates hotel-booking.
4. Patient selects suitable hotels based on the appointment, creating a hotel booking.
- Departure flights' arrival time must be at least 4 hours before the appointment time.
- Return flights' departure time must be at least 4 hours after the appointment time.
- Departure flights' departure city must be the same as the patient's city.
- Departure flights' arrival city must be the same as the doctor's city.
- Return flights' departure city must be the same as the doctor's city.
- Return flights' arrival city must be the same as the patient's city.
- Hotel check-in time must be at least 1 day before the appointment time.
- Hotel check-out time must be at least 1 day after the appointment time
- Hotel city must be same as doctors' city.
5. Doctor creates a medical-record and adds it to appointment.

  ## Postman
  [Link](https://api.postman.com/collections/9589337-f18e4bbd-556d-43e0-85e9-72aefe16f0f5?access_key=PMAT-01H96D1XQ756Z0PCAHWX33NY53)

