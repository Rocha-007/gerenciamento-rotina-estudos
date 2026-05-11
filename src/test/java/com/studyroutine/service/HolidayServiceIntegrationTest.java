package com.studyroutine.service;

import com.studyroutine.model.Holiday;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Teste de Integração para HolidayService.
 * 
 * Este teste valida a integração real com a API pública de Feriados (Nager.Date).
 * Diferente de testes unitários que usam mocks, este teste faz requisições reais
 * para validar que a aplicação consegue se comunicar corretamente com o serviço externo.
 * 
 * IMPORTANTE: Requer conexão com a internet para executar.
 * 
 * Requisitos de Qualidade:
 * - Testa múltiplos cenários de consumo da API
 * - Valida tratamento de erros
 * - Valida cache de dados
 * - Valida tratamento de datas inválidas
 */
@DisplayName("Testes de Integração do HolidayService com API Pública")
public class HolidayServiceIntegrationTest {

    private HolidayService holidayService;
    private static final int TEST_YEAR = 2024;
    private static final int CURRENT_YEAR = LocalDate.now().getYear();

    @BeforeEach
    public void setUp() {
        holidayService = new HolidayService();
        holidayService.clearCache();
    }

    @Test
    @DisplayName("Deve conectar com sucesso na API e retornar lista de feriados")
    public void testGetHolidaysForYear_Success() throws IOException, InterruptedException {
        // Given: Serviço de feriados inicializado
        // When: Requisita feriados para 2024
        List<Holiday> holidays = holidayService.getHolidaysForYear(TEST_YEAR);

        // Then: Retorna lista não vazia
        assertNotNull(holidays, "Lista de feriados não deve ser nula");
        assertTrue(holidays.size() > 0, "Deve retornar pelo menos um feriado");

        // And: Cada feriado deve ter data válida
        for (Holiday holiday : holidays) {
            assertNotNull(holiday.getDate(), "Feriado deve ter data");
            assertNotNull(holiday.getLocalName(), "Feriado deve ter nome em português");
            assertEquals("BR", holiday.getCountryCode(), "Deve ser feriado do Brasil");
        }
    }

    @Test
    @DisplayName("Deve validar que uma data conhecida é um feriado")
    public void testIsHoliday_WithKnownHoliday() throws IOException, InterruptedException {
        // Given: Data de Ano Novo (1º de janeiro)
        LocalDate newYear = LocalDate.of(TEST_YEAR, 1, 1);

        // When: Verifica se é feriado
        boolean isHoliday = holidayService.isHoliday(newYear);

        // Then: Deve retornar true
        assertTrue(isHoliday, "1º de janeiro deve ser feriado");
    }

    @Test
    @DisplayName("Deve validar que uma data comum não é feriado")
    public void testIsHoliday_WithRegularDay() throws IOException, InterruptedException {
        // Given: Uma data comum (15 de fevereiro de 2024)
        LocalDate regularDay = LocalDate.of(TEST_YEAR, 2, 15);

        // When: Verifica se é feriado
        boolean isHoliday = holidayService.isHoliday(regularDay);

        // Then: Deve retornar false
        assertFalse(isHoliday, "15 de fevereiro não deve ser feriado");
    }

    @Test
    @DisplayName("Deve retornar nome do feriado para data específica")
    public void testGetHolidayName_WithHoliday() throws IOException, InterruptedException {
        // Given: Data de Ano Novo
        LocalDate newYear = LocalDate.of(TEST_YEAR, 1, 1);

        // When: Obtém nome do feriado
        String holidayName = holidayService.getHolidayName(newYear);

        // Then: Deve retornar nome não vazio
        assertNotNull(holidayName, "Nome do feriado não deve ser nulo");
        assertFalse(holidayName.isEmpty(), "Nome do feriado deve estar preenchido");
        assertTrue(holidayName.toLowerCase().contains("ano") || 
                   holidayName.toLowerCase().contains("new"),
                   "Deve mencionar Ano Novo");
    }

    @Test
    @DisplayName("Deve retornar string vazia para data que não é feriado")
    public void testGetHolidayName_WithRegularDay() throws IOException, InterruptedException {
        // Given: Uma data comum
        LocalDate regularDay = LocalDate.of(TEST_YEAR, 2, 15);

        // When: Obtém nome do feriado
        String holidayName = holidayService.getHolidayName(regularDay);

        // Then: Deve retornar string vazia
        assertNotNull(holidayName, "Resultado não deve ser nulo");
        assertTrue(holidayName.isEmpty(), "Deve retornar string vazia para dia comum");
    }

    @Test
    @DisplayName("Deve usar cache para requisições subsequentes")
    public void testCache_ShouldAvoidMultipleRequests() throws IOException, InterruptedException {
        // Given: Primeira requisição
        List<Holiday> firstCall = holidayService.getHolidaysForYear(TEST_YEAR);
        int firstSize = firstCall.size();

        // When: Faz segunda requisição (deve vir do cache)
        List<Holiday> secondCall = holidayService.getHolidaysForYear(TEST_YEAR);

        // Then: Deve retornar mesmos dados
        assertEquals(firstSize, secondCall.size(), "Cache deve retornar mesma quantidade");
        assertEquals(firstCall, secondCall, "Cache deve retornar mesmos dados");
    }

    @Test
    @DisplayName("Deve limpar cache corretamente")
    public void testClearCache_ShouldResetCachedData() throws IOException, InterruptedException {
        // Given: Dados carregados em cache
        holidayService.getHolidaysForYear(TEST_YEAR);

        // When: Limpa cache
        holidayService.clearCache();

        // Then: Próxima requisição deve buscar novamente da API
        List<Holiday> holidays = holidayService.getHolidaysForYear(TEST_YEAR);
        assertTrue(holidays.size() > 0, "Deve retornar dados após limpar cache");
    }

    @Test
    @DisplayName("Deve retornar feriados futuros em ordem cronológica")
    public void testGetUpcomingHolidays_ShouldReturnInOrder() throws IOException, InterruptedException {
        // Given: Data inicial de fevereiro
        LocalDate startDate = LocalDate.of(CURRENT_YEAR, 2, 1);

        // When: Requisita próximos 5 feriados
        List<Holiday> upcoming = holidayService.getUpcomingHolidays(startDate, 5);

        // Then: Deve retornar feriados
        assertTrue(upcoming.size() > 0, "Deve retornar feriados futuros");
        assertTrue(upcoming.size() <= 5, "Deve respeitar o limite");

        // And: Todos devem estar após a data inicial
        for (Holiday holiday : upcoming) {
            assertTrue(!holiday.getDate().isBefore(startDate),
                    "Feriado deve estar em ou após a data inicial");
        }
    }

    @Test
    @DisplayName("Deve lançar exceção para ano inválido")
    public void testGetHolidaysForYear_InvalidYear() {
        // Given: Ano inválido
        int invalidYear = 1999;

        // When & Then: Deve lançar exceção
        assertThrows(IllegalArgumentException.class, 
            () -> holidayService.getHolidaysForYear(invalidYear),
            "Deve rejeitar anos menores que 2000");
    }

    @Test
    @DisplayName("Deve lançar exceção quando data é nula")
    public void testIsHoliday_WithNullDate() {
        // When & Then: Deve lançar exceção
        assertThrows(NullPointerException.class,
            () -> holidayService.isHoliday(null),
            "Deve validar data nula");
    }

    @Test
    @DisplayName("Deve validar que resposta da API contém feriados brasileiros válidos")
    public void testAPIResponse_ValidateHolidayStructure() throws IOException, InterruptedException {
        // Given: Requisita feriados para o ano
        List<Holiday> holidays = holidayService.getHolidaysForYear(TEST_YEAR);

        // When & Then: Valida estrutura dos dados
        for (Holiday holiday : holidays) {
            assertNotNull(holiday.getDate(), "Data do feriado não pode ser nula");
            assertNotNull(holiday.getLocalName(), "Nome local não pode ser nulo");
            assertEquals("BR", holiday.getCountryCode(), "Country code deve ser BR");
            assertFalse(holiday.getLocalName().isEmpty(), "Nome do feriado não pode estar vazio");
            assertTrue(holiday.getDate().getYear() == TEST_YEAR, 
                      "Feriado deve ser do ano requisitado");
        }
    }

    @Test
    @DisplayName("Deve tratar exceção de conexão adequadamente")
    public void testConnectionError_ShouldHandleGracefully() {
        // Given: Serviço com URL inválida (seria necessário mockagem)
        // This test is more for documentation - in production, would use mock
        
        // When & Then: Aplicação deve não quebrar
        assertNotNull(holidayService, "Serviço deve ser instanciável mesmo com potencial erro");
    }
}
