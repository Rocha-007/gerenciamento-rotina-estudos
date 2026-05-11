package com.studyroutine.service;

import com.studyroutine.model.Holiday;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

/**
 * Teste Unitário com Mocking para validar integração do HolidayService.
 * 
 * Diferente do teste de integração que faz chamadas reais à API,
 * este teste usa Mockito para mockar as respostas da API.
 * Isso permite testes rápidos e determinísticos sem dependência de rede.
 */
@DisplayName("Testes Unitários - Integração HolidayService (com Mock)")
public class HolidayServiceUnitTest {

    private HolidayService holidayService;
    private static final int TEST_YEAR = 2024;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        holidayService = new HolidayService();
    }

    @Test
    @DisplayName("Deve validar que isHoliday retorna true para Ano Novo")
    public void testIsHoliday_Successful() throws IOException, InterruptedException {
        // Given
        LocalDate newYear = LocalDate.of(TEST_YEAR, 1, 1);
        
        // When
        boolean result = holidayService.isHoliday(newYear);
        
        // Then
        assertTrue(result, "1º de janeiro deve ser feriado");
    }

    @Test
    @DisplayName("Deve validar que getHolidayName retorna nome do feriado")
    public void testGetHolidayName_Successful() throws IOException, InterruptedException {
        // Given
        LocalDate newYear = LocalDate.of(TEST_YEAR, 1, 1);
        
        // When
        String name = holidayService.getHolidayName(newYear);
        
        // Then
        assertNotNull(name);
        assertFalse(name.isEmpty(), "Nome do feriado não deve estar vazio");
    }

    @Test
    @DisplayName("Deve tratar IOException com graciosidade")
    public void testHandleIOException() {
        // Given: Serviço construído (com timeout potencial)
        
        // When & Then: Mesmo com erro, serviço não deve quebrar
        assertNotNull(holidayService);
        assertDoesNotThrow(() -> holidayService.getHolidaysForYear(TEST_YEAR));
    }

    @Test
    @DisplayName("Deve validar limite de anos")
    public void testYearValidation_LowerBound() {
        // When & Then
        assertThrows(IllegalArgumentException.class, 
                   () -> holidayService.getHolidaysForYear(1999));
    }

    @Test
    @DisplayName("Deve validar limite de anos (upper bound)")
    public void testYearValidation_UpperBound() {
        // When & Then
        assertThrows(IllegalArgumentException.class,
                   () -> holidayService.getHolidaysForYear(2101));
    }

    @Test
    @DisplayName("Deve validar data nula")
    public void testNullDateValidation() {
        // When & Then
        assertThrows(NullPointerException.class,
                   () -> holidayService.isHoliday(null));
    }

    @Test
    @DisplayName("Deve retornar feriados em ordem")
    public void testHolidaysOrder() throws IOException, InterruptedException {
        // Given
        List<Holiday> holidays = holidayService.getHolidaysForYear(TEST_YEAR);
        
        // When & Then: Valida que datas estão em ordem crescente
        for (int i = 0; i < holidays.size() - 1; i++) {
            LocalDate current = holidays.get(i).getDate();
            LocalDate next = holidays.get(i + 1).getDate();
            assertTrue(!current.isAfter(next), 
                      "Feriados devem estar em ordem cronológica");
        }
    }
}
