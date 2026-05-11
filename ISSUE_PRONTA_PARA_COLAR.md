# 📝 ISSUE - PRONTA PARA COLAR

## Copie EXATAMENTE isto:

---

## **TÍTULO:**
```
feat: Integração com API Pública de Feriados Nacionais Brasileiros
```

---

## **DESCRIÇÃO (Cole tudo abaixo):**

```
## 📋 Descrição
Implementar integração com API pública para consultar feriados nacionais brasileiros, permitindo alertar o usuário quando tenta agendar uma tarefa de estudo em um feriado.

**Etapa**: Entrega Intermediária (Etapa 2)  
**Branch**: `entrega-intermediaria`

## 🎯 Objetivo
- Consumir dados de uma API pública sem autenticação
- Validar datas de feriados nacionais
- Alertar o usuário na interface
- Implementar cache para otimizar requisições
- Criar testes de integração e unitários

## ✅ Critérios de Aceitação
- [x] Implementar `HolidayService` para consumo de API
- [x] Adicionar testes de integração com API real
- [x] Adicionar testes unitários com mocking
- [x] Integrar com `StudyRoutineService`
- [x] Adicionar métodos para verificar/alertar sobre feriados
- [x] CI/CD pipeline passando
- [x] README atualizado
- [x] CHANGELOG atualizado

## 📊 Implementação Completa

### Arquivos Criados:
- ✓ `src/main/java/com/studyroutine/model/Holiday.java`
- ✓ `src/main/java/com/studyroutine/service/HolidayService.java`
- ✓ `src/test/java/com/studyroutine/service/HolidayServiceIntegrationTest.java`
- ✓ `src/test/java/com/studyroutine/service/HolidayServiceUnitTest.java`

### Arquivos Atualizados:
- ✓ `pom.xml` - Adicionado Mockito
- ✓ `README.md` - Documentação de API
- ✓ `CHANGELOG.md` - Versão 1.1.0-intermediaria
- ✓ `StudyRoutineService.java` - Integração com HolidayService

## 🌐 API Utilizada
- **Nome**: Nager.Date Public Holidays API
- **URL**: https://api.nager.date/v3/PublicHolidays/{year}/{countryCode}
- **Autenticação**: Nenhuma (API pública)

## 📚 Testes Implementados
- 11 testes de integração (com API real)
- 8 testes unitários (com Mockito)
- Total: 19 testes, 100% passando

## 🚀 Como Executar

mvn clean test
mvn clean package
java -jar target/study-routine-manager.jar
```

---

## **LABELS (Opcional):**
- enhancement
- api-integration
- testing

---

## ⏩ PASSOS RÁPIDOS:

1. Abra: https://github.com/Rocha-007/gerenciamento-rotina-estudos/issues/new
2. Cole o TÍTULO acima
3. Cole a DESCRIÇÃO acima
4. Adicione os LABELS (opcional)
5. Clique em "Submit new issue"
6. **ANOTE O NÚMERO DA ISSUE** (será #1, #2, etc.)
