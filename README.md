# IIPS Innovation, Startup & Incubation Management System

Spring Boot 3.2 · Java 17 · Spring Data JPA · H2 (default) / MySQL · **No Lombok** (manual getters/setters)

## Modules
1. **Startup Ideas** – submit, review, approve/reject ideas
2. **Incubation Support** – workspace / legal / technical / training support requests per startup
3. **Mentor Allocation** – manage mentors and assign them to startups
4. **Funding Tracker** – grants, seed, angel funding with status tracking
5. **Dashboard** – overall summary

## Run
```
mvn spring-boot:run
```
API base: http://localhost:8080/api  ·  H2 console: http://localhost:8080/h2-console
(JDBC URL `jdbc:h2:file:./data/iipsdb`, user `sa`, empty password)

To use MySQL, edit `src/main/resources/application.properties` (instructions inside).

## API
| Module | Method & URL |
|---|---|
| Startups | `POST /api/startups`, `GET /api/startups?status=`, `GET/PUT/DELETE /api/startups/{id}`, `PATCH /api/startups/{id}/status?status=APPROVED` |
| Support | `POST /api/startups/{id}/support`, `GET /api/support`, `GET /api/startups/{id}/support`, `PATCH /api/support/{id}/status?status=COMPLETED`, `DELETE /api/support/{id}` |
| Mentors | `POST /api/mentors`, `GET /api/mentors?expertise=`, `GET/PUT/DELETE /api/mentors/{id}` |
| Allocation | `POST /api/allocations`, `GET /api/allocations`, `GET /api/allocations/mentor/{id}`, `GET /api/allocations/startup/{id}`, `PATCH /api/allocations/{id}/release` |
| Funding | `POST /api/startups/{id}/funding`, `GET /api/funding`, `GET /api/startups/{id}/funding`, `GET /api/startups/{id}/funding/summary`, `PATCH /api/funding/{id}/status?status=DISBURSED`, `DELETE /api/funding/{id}` |
| Dashboard | `GET /api/dashboard` |

## Sample requests
```
POST /api/startups
{"title":"AgriSense","description":"IoT soil monitoring","domain":"AgriTech",
 "founderName":"Riya Sharma","founderEmail":"riya@iips.edu","teamMembers":"Aman, Neha"}

POST /api/mentors
{"name":"Dr. A. Verma","email":"verma@mentor.com","expertise":"AgriTech, IoT","organization":"IIM Indore"}

POST /api/allocations
{"mentorId":1,"startupIdeaId":1,"remarks":"Product guidance"}

POST /api/startups/1/support
{"supportType":"PROTOTYPING","description":"Need lab access for sensor prototype"}

POST /api/startups/1/funding
{"fundingSource":"Startup India Seed Fund","amount":500000,"fundingType":"SEED"}
```
