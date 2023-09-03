# Health Tourism API
## Table of Contents
- [Description](#description)
- [Requirements](#requirements)
- [Installation](#installation)
- [Usage](#usage)
- [Contact](#contact)

## Description

The Health Tourism Application is a platform that facilitates patients' travel to different countries for receiving healthcare services. 
The application aims to accelerate the process of patients receiving services from good doctors and hospitals abroad.
The patient can perform all transactions on this site instead of wasting time on other sites for hotel and flight reservations.

### Requirements

- IntelliJ IDEA(Ultimate preferred)
- PostgreSQL

### Installation

1. git clone https://github.com/haruntasci/health-tourism-api-springboot.git
2. Edit properties according to your database information
- spring.datasource.url=jdbc:postgresql:yourURL
- spring.datasource.username=yourUsername
- spring.datasource.password=yourPassword
3. In Edit Configurations section write Active profiles: dev
  
### Class Diagram
![health-tourism-diag](https://github.com/haruntasci/health-tourism-api-springboot/assets/99567926/dfe4975e-4b3d-475f-8181-69e37ff91ff9)

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
2. Patient creates a patient and an appointment. To create appointment, Patient selects doctor and adds appointment date.
3. Patient selects suitable departure and return flights according to appointment date and city, and creates flight-booking.
4. Patient selects suitable hotel according to appointment date and city, and creates hotel-booking.
5. Departure flights' arrival time must be at least 4 hours before the appointment time.
6. Return flights' departure time must be at least 4 hours after the appointment time.
7. Departure flights' departure city must be the same as the patient's city.
8. Departure flights' arrival city must be the same as the doctor's city.
9. Return flights' departure city must be the same as the doctor's city.
10. Return flights' arrival city must be the same as the patient's city.
11. Hotel check-in time must be at least 1 day before the appointment time.
12. Hotel check-out time must be at least 1 day after the appointment time
13. Hotel city must be same as doctors' city.

## Contact

Include contact information or community links where users can seek support, ask questions, report issues, or contribute to the project.
