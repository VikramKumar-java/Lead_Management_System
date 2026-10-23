# LeadFlow — React frontend for Spring Boot Lead Management System

## Requirements
- Node.js 20.19+ or 22.12+ (supported by Vite 6)
- Running Spring Boot API on http://localhost:8080
- MySQL configured for the Spring Boot project

## Run
```bash
npm install
copy .env.example .env
npm run dev
```
Open http://localhost:5173.

## Backend CORS setup (Spring Security)
In `SecurityConfig.java`, enable CORS in your existing SecurityFilterChain:
```java
http.cors(cors -> {});
```
Also add a bean to SecurityConfig (imports shown):
```java
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;
import java.util.List;

@Bean
public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowedOrigins(List.of("http://localhost:5173"));
    config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
    config.setAllowedHeaders(List.of("Authorization", "Content-Type"));
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", config);
    return source;
}
```
Do not remove your existing JWT filter, CSRF/session settings or authorization rules. Restart Spring Boot.

## APIs used
- POST /api/auth/register
- POST /api/auth/login
- GET /api/dashboard
- GET /api/leads
- GET /api/leads/{id}
- POST /api/leads
- PUT /api/leads/{id}
- DELETE /api/leads/{id}

## Integration notes
- `src/api.js` is the central Axios API layer.
- Login expects `AuthResponse` to contain `token` (or `accessToken`/`jwt`). Adjust it if your DTO differs.
- Registration sends `name`, `email`, `password`. Adjust `name` if your RegisterRequest uses a different field.
- `GET /api/leads` accepts either a JSON array or a Spring Data `Page` with `content`. This UI only shows the returned page; it does not implement server pagination.
- Dates use `YYYY-MM-DD` and an empty follow-up date is sent as `null`.
- JWT is kept in sessionStorage for this demo; for production, use a stronger session/token design and avoid exposing tokens to XSS.
- The UI's client-side route guard is for navigation only. Spring Security must enforce authorization on the backend.
