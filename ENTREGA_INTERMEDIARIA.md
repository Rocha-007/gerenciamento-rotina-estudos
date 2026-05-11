# 📋 GUIA PARA COMPLETAR A ENTREGA INTERMEDIÁRIA

## ✅ O que foi implementado

Esta branch contém a implementação completa da **Entrega Intermediária (Etapa 2)** do Bootcamp:

### 1. **Integração com API Pública ✨**
- **API**: Nager.Date Public Holidays API
- **Funcionalidade**: Integração com feriados nacionais brasileiros
- **Benefício**: Alertar o usuário quando agenda estudo em feriados
- **Arquivos**: 
  - `src/main/java/com/studyroutine/model/Holiday.java`
  - `src/main/java/com/studyroutine/service/HolidayService.java`

### 2. **Testes de Integração ✅**
- **Teste de Integração Real**: `HolidayServiceIntegrationTest.java`
  - Faz chamadas reais à API
  - Valida resposta da API
  - Testa cache de dados
- **Teste Unitário com Mock**: `HolidayServiceUnitTest.java`
  - Testa sem dependência de rede
  - Valida tratamento de erros
- **Integração com Serviço**: `StudyRoutineService.java` atualizado

### 3. **Estrutura de Branching**
- Branch criada: `entrega-intermediaria`
- Pronta para Pull Request e Merge

---

## 🚀 Como Executar Localmente

### Pré-requisitos
- Java 11+
- Maven 3.6+

### Passos

1. **Build do projeto**:
```bash
mvn clean package
```

2. **Rodar testes**:
```bash
# Todos os testes
mvn test

# Apenas testes de integração (requer internet)
mvn test -Dtest=HolidayServiceIntegrationTest

# Apenas testes unitários
mvn test -Dtest=HolidayServiceUnitTest
```

3. **Verificar code quality**:
```bash
mvn checkstyle:check
```

4. **Executar a aplicação**:
```bash
# Windows
run.bat

# Linux/Mac
./run.sh
```

---

## 📝 Como Criar a Issue no GitHub

Acesse: https://github.com/Rocha-007/gerenciamento-rotina-estudos/issues/new

**Título**:
```
feat: Integração com API Pública de Feriados Nacionais Brasileiros
```

**Descrição**:
```markdown
## 📋 Descrição
Implementar integração com API pública para consultar feriados nacionais brasileiros. 
Isso permitirá alertar o usuário quando tentar agendar uma tarefa de estudo em um feriado.

## 🎯 Objetivo
- Consumir dados de API pública (Nager.Date)
- Validar datas de feriados
- Alertar usuário na UI
- Implementar cache para otimizar requisições

## ✅ Critérios de Aceitação
- [ ] Implementar HolidayService para consumo de API
- [ ] Adicionar testes de integração com API real
- [ ] Adicionar testes unitários com mocking
- [ ] Integrar com StudyRoutineService
- [ ] Alertar quando agendar tarefa em feriado
- [ ] CI/CD pipeline passando (verde)
- [ ] README atualizado com novo link de deploy

## 📚 Referências
- API: https://api.nager.date/v3/PublicHolidays
- Documentação: https://date.nager.at/

## 🔗 Branch Relacionada
- `entrega-intermediaria`
```

---

## 🌐 Como Fazer Deploy

### Opção 1: GitHub Pages (Recomendado para Aplicação Web)
Se converter para aplicação web:
1. Fazer commit do código
2. Ir em Settings > Pages
3. Selecionar branch e pasta

### Opção 2: Vercel / Netlify (Para Java)
Como é aplicação Java Desktop, considere:
1. Criar um wrapper web em Node.js/Express
2. Ou documentar como executar localmente

### Opção 3: Docker + Heroku/Railway
```dockerfile
FROM openjdk:11-jre-slim
COPY target/study-routine-manager.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

---

## 📮 Próximos Passos

1. ✅ Fazer commit deste código
2. ⏳ Fazer push da branch
3. ⏳ Criar Pull Request
4. ⏳ Merge para main
5. ⏳ Criar release

---

## 📊 Estrutura de Testes

```
HolidayServiceIntegrationTest
├── testGetHolidaysForYear_Success
├── testIsHoliday_WithKnownHoliday
├── testIsHoliday_WithRegularDay
├── testGetHolidayName_WithHoliday
├── testCache_ShouldAvoidMultipleRequests
├── testGetUpcomingHolidays_ShouldReturnInOrder
└── testAPIResponse_ValidateHolidayStructure

HolidayServiceUnitTest
├── testIsHoliday_Successful
├── testGetHolidayName_Successful
├── testHandleIOException
└── testYearValidation_*
```

---

## 🔍 Checklist de Qualidade

- ✅ Código compilável
- ✅ Testes passando
- ✅ Checkstyle validado
- ✅ Sem dependências externas desnecessárias
- ✅ Tratamento de exceções robusto
- ✅ Cache implementado (evita requisições repetidas)
- ✅ API pública gratuita e sem autenticação
- ✅ Documentação de código completa

---

## 🐛 Troubleshooting

### Erro: API indisponível
- A API é pública e geralmente confiável
- Teste em: `https://api.nager.date/v3/PublicHolidays/2024/BR`

### Erro de timeout
- Aumentar timeout em `HolidayService` se necessário
- Padrão: 10 segundos

### Testes falhando
- Verificar conexão com internet (testes de integração)
- Executar apenas testes unitários sem rede

---

**Autor**: Eduardo Rocha (@Rocha-007)  
**Data**: Maio 2026  
**Versão**: 1.1.0-intermediaria
