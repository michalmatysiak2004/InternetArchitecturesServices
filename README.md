# ⚽ Football Club Management System

Aplikacja typu Fullstack do zarządzania klubami piłkarskimi i zawodnikami. Projekt zrealizowany w architekturze mikroserwisów przy użyciu **Spring Boot** oraz **Angular**.

## 🏗 Architektura

System składa się z następujących komponentów:

1.  **Frontend (Angular):** Interfejs użytkownika (SPA) do zarządzania danymi.
2.  **Spring Cloud Gateway:** Centralny punkt wejścia (brama), który kieruje ruch do odpowiednich mikroserwisów.
3.  **Club Service:** Mikroserwis odpowiedzialny za logikę i dane klubów.
4.  **Player Service:** Mikroserwis odpowiedzialny za logikę i dane piłkarzy.

## 🛠 Technologie

**Backend:**
* Java 21
* Spring Boot 3
* Spring Cloud Gateway
* Spring Data JPA
* Database: H2
* Maven

**Frontend:**
* Angular 20+
* TypeScript
* CSS
* HTML 
---

## 🚀 Jak uruchomić projekt

Aby uruchomić aplikację, musisz mieć zainstalowane: Java JDK, Maven, Node.js oraz Angular CLI.

### 1. Pobranie repozytorium

```bash
git clone https://github.com/michalmatysiak2004/InternetArchitecturesServices.git
cd InternetArchitecturesServices

```

### 2. Uruchomienie 3 microserwisów
```bash
cd backend/club-service
mvn spring-boot:run
# Serwis startuje na porcie: 8081
cd ..
cd backend/player-service
mvn spring-boot:run
# Serwis startuje na porcie: 8082
cd..
cd backend/gateway
mvn spring-boot:run
# Gateway startuje na porcie: 8080
```

### 3.Uruchomienie frontendu
```bash
cd frontend
npm install
ng serve

```


### 🔌 API Endpoints (Gateway)

| Zasób | Metoda | URL (Gateway) | Przekierowanie do | Opis |
| :--- | :---: | :--- | :--- | :--- |
| **Kluby** | `GET` | `/api/clubs` | `club-service/clubs` | Lista klubów |
| | `POST` | `/api/clubs` | `club-service/clubs` | Dodanie klubu |
| | `GET` | `/api/clubs/{id}` | `club-service/clubs/{id}` | Szczegóły klubu |
| **Piłkarze** | `GET` | `/api/players` | `player-service/players` | Lista piłkarzy |
| | `POST` | `/api/players` | `player-service/players` | Dodanie piłkarza |
| | `DELETE` | `/api/players/{id}`| `player-service/players/{id}`| Usunięcie piłkarza |



### 📂 Struktura Projektu

```text
/
├── backend/
│   ├── gateway/         # Konfiguracja routingu
│   ├── club-service/    # Moduł klubów (Controller, Service, Repository)
│   └── player-service/  # Moduł piłkarzy (Controller, Service, Repository)
├── frontend/            # Kod źródłowy aplikacji Angular (src/app/...)
└── README.md


