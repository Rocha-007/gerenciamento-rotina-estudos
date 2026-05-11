package com.studyroutine.service;

import com.studyroutine.model.Holiday;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Serviço para integração com API pública de Feriados Nacionais Brasileiros.
 * 
 * Utiliza a API do Nager.Date (https://api.nager.date/v3/PublicHolidays/{year}/{countryCode})
 * 
 * A integração permite:
 * - Verificar se uma data é feriado nacional
 * - Obter lista de feriados para um ano
 * - Alertar o usuário ao agendar tarefas em datas de feriado
 * 
 * Design: Esta classe segue o padrão Service, separando a lógica de consumo de API
 * da lógica de negócio principal. Isso facilita testes (mockagem) e manutenção.
 */
public class HolidayService {
    private static final String API_BASE_URL = "https://api.nager.date/v3/PublicHolidays";
    private static final String COUNTRY_CODE = "BR";
    private static final int CONNECTION_TIMEOUT_SECONDS = 5;
    private static final int READ_TIMEOUT_SECONDS = 10;

    private HttpClient httpClient;
    private Gson gson;
    private List<Holiday> cachedHolidays;
    private int cachedYear;

    public HolidayService() {
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(java.time.Duration.ofSeconds(CONNECTION_TIMEOUT_SECONDS))
                .build();
        this.gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                .create();
        this.cachedHolidays = new ArrayList<>();
        this.cachedYear = -1;
    }

    /**
     * Obtém a lista de feriados para um ano específico.
     * 
     * @param year Ano desejado
     * @return Lista de feriados para o ano
     * @throws IOException Se houver erro na requisição HTTP
     * @throws InterruptedException Se a requisição for interrompida
     * @throws IllegalArgumentException Se o ano for inválido
     */
    public List<Holiday> getHolidaysForYear(int year) throws IOException, InterruptedException {
        if (year < 2000 || year > 2100) {
            throw new IllegalArgumentException("Ano deve estar entre 2000 e 2100");
        }

        // Usa cache para evitar múltiplas requisições
        if (year == cachedYear && !cachedHolidays.isEmpty()) {
            return new ArrayList<>(cachedHolidays);
        }

        String url = String.format("%s/%d/%s", API_BASE_URL, year, COUNTRY_CODE);
        
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .timeout(java.time.Duration.ofSeconds(READ_TIMEOUT_SECONDS))
                    .header("User-Agent", "StudyRoutineManager/1.0")
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new IOException("Erro ao conectar com API de Feriados. Status: " + response.statusCode());
            }

            List<Holiday> holidays = gson.fromJson(response.body(), new TypeToken<List<Holiday>>(){}.getType());
            
            // Armazena em cache
            cachedHolidays = Objects.requireNonNull(holidays, "Resposta vazia da API");
            cachedYear = year;

            return new ArrayList<>(cachedHolidays);
        } catch (IOException | InterruptedException e) {
            throw e;
        }
    }

    /**
     * Verifica se uma data específica é um feriado nacional brasileiro.
     * 
     * @param date Data a verificar
     * @return true se for feriado, false caso contrário
     * @throws IOException Se houver erro na requisição HTTP
     * @throws InterruptedException Se a requisição for interrompida
     */
    public boolean isHoliday(LocalDate date) throws IOException, InterruptedException {
        Objects.requireNonNull(date, "Data não pode ser nula");

        int year = date.getYear();
        List<Holiday> holidays = getHolidaysForYear(year);

        return holidays.stream()
                .anyMatch(h -> h.getDate().equals(date));
    }

    /**
     * Obtém o nome do feriado para uma data específica, se existir.
     * 
     * @param date Data a verificar
     * @return Nome do feriado em português (localName), ou vazio se não for feriado
     * @throws IOException Se houver erro na requisição HTTP
     * @throws InterruptedException Se a requisição for interrompida
     */
    public String getHolidayName(LocalDate date) throws IOException, InterruptedException {
        Objects.requireNonNull(date, "Data não pode ser nula");

        int year = date.getYear();
        List<Holiday> holidays = getHolidaysForYear(year);

        return holidays.stream()
                .filter(h -> h.getDate().equals(date))
                .map(Holiday::getLocalName)
                .findFirst()
                .orElse("");
    }

    /**
     * Obtém feriados de uma data em diante (próximos feriados).
     * 
     * @param fromDate Data inicial (inclusive)
     * @param limit Número máximo de feriados a retornar
     * @return Lista de próximos feriados
     * @throws IOException Se houver erro na requisição HTTP
     * @throws InterruptedException Se a requisição for interrompida
     */
    public List<Holiday> getUpcomingHolidays(LocalDate fromDate, int limit) throws IOException, InterruptedException {
        Objects.requireNonNull(fromDate, "Data não pode ser nula");
        if (limit <= 0) {
            throw new IllegalArgumentException("Limite deve ser maior que 0");
        }

        List<Holiday> upcomingList = new ArrayList<>();
        LocalDate currentDate = fromDate;
        int maxYears = 3; // Procura por até 3 anos à frente
        int year = currentDate.getYear();

        for (int y = 0; y < maxYears && upcomingList.size() < limit; y++) {
            List<Holiday> holidays = getHolidaysForYear(year + y);
            
            holidays.stream()
                    .filter(h -> h.getDate().isAfter(currentDate.minusDays(1)))
                    .limit(limit - upcomingList.size())
                    .forEach(upcomingList::add);
        }

        return upcomingList;
    }

    /**
     * Limpa o cache de feriados (útil para testes).
     */
    public void clearCache() {
        cachedHolidays.clear();
        cachedYear = -1;
    }
}
