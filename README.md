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
* Java 17/21
* Spring Boot 3.x
* Spring Cloud Gateway
* Spring Data JPA
* Database: [np. H2 / PostgreSQL / MySQL - wpisz właściwą]
* Maven

**Frontend:**
* Angular 16+
* TypeScript
* RxJS
* [np. Angular Material / Bootstrap - wpisz jeśli używasz]

---

## 🚀 Jak uruchomić projekt

Aby uruchomić aplikację, musisz mieć zainstalowane: Java JDK, Maven, Node.js oraz Angular CLI.

### 1. Pobranie repozytorium

```bash
git clone <adres-twojego-repo>
cd moj-projekt
