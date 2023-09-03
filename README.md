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

## Endpoints
- POST admin-auth/register
- POST admin-auth/login
- DELETE admin-auth/delete/{email}

- POST doctor-auth/register
- POST doctor-auth/login
- DELETE doctor-auth/delete/{email}

- POST patient-auth/register
- POST patient-auth/login
- DELETE patient-auth/delete/{email}

- GET country/{uuid}
- PUT country/{uuid}
- DELETE country/{uuid}
- POST country
- POST country/get-all-filter

- GET city/{uuid}
- PUT city/{uuid}
- DELETE city/{uuid}
- POST city
- POST city/get-all-filter

- GET hospital/{uuid}
- PUT hospital/{uuid}
- DELETE hospital/{uuid}
- POST hospital
- POST hospital/get-all-filter

- GET doctor/{uuid}
- PUT doctor/{uuid}
- DELETE doctor/{uuid}
- POST doctor
- POST doctor/get-all-filter

- GET patient/{uuid}
- PUT patient/{uuid}
- DELETE patient/{uuid}
- POST patient
- POST patient/get-all-filter

- GET appointment/{uuid}
- GET appointment/get-patients-appointments/{identityNumber}
- PUT appointment/{uuid}
- PUT appointment/update-appointment-date/{uuid}
- PUT appointment/add-medical-record/{uuid}
- DELETE appointment/{uuid}
- POST appointment
- POST appointment/get-all-filter

- GET hotel/{uuid}
- PUT hotel/{uuid}
- DELETE hotel/{uuid}
- POST hotel
- POST hotel/get-all-filter

- GET hotel-booking/{uuid}
- PUT hotel-booking/{uuid}
- DELETE hotel-booking/{uuid}
- POST hotel-booking
- POST hotel-booking/get-all-filter

- GET flight/{uuid}
- PUT flight/{uuid}
- DELETE flight/{uuid}
- POST flight
- POST flight/get-all-filter

- GET flight-booking/{uuid}
- PUT flight-booking/{uuid}
- DELETE flight-booking/{uuid}
- POST flight-booking
- POST flight-booking/get-all-filter

- GET medical-record/{uuid}
- PUT medical-record/{uuid}
- DELETE medical-record/{uuid}
- POST medical-record
- POST medical-record/get-all-filter


## Contact

Include contact information or community links where users can seek support, ask questions, report issues, or contribute to the project.
