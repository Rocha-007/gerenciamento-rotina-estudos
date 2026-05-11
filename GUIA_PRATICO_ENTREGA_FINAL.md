# 🎯 GUIA PRÁTICO: FINALIZANDO A ENTREGA INTERMEDIÁRIA

## ✅ Status Atual

```
✓ Implementação concluída
✓ Testes de integração criados
✓ Testes unitários criados
✓ Commit realizado (hash: 5d3427d)
✓ Push para branch 'entrega-intermediaria' concluído
⏳ Criar Issue no GitHub (PRÓXIMO PASSO)
⏳ Criar Pull Request
⏳ Deploy
⏳ Merge para main
```

---

## 📋 PASSO 1: Criar Issue no GitHub

### ⏱️ Tempo estimado: 3 minutos

1. Acesse: https://github.com/Rocha-007/gerenciamento-rotina-estudos/issues/new

2. **Preencha o formulário**:

   **Title (Título)**:
   ```
   feat: Integração com API Pública de Feriados Nacionais Brasileiros
   ```

   **Description (Descrição)**:
   ```markdown
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
   - ✓ `ENTREGA_INTERMEDIARIA.md`

   ### Arquivos Atualizados:
   - ✓ `pom.xml` - Adicionado Mockito
   - ✓ `README.md` - Documentação de API
   - ✓ `CHANGELOG.md` - Versão 1.1.0-intermediaria
   - ✓ `StudyRoutineService.java` - Integração com HolidayService

   ## 🌐 API Utilizada
   - **Nome**: Nager.Date Public Holidays API
   - **URL**: https://api.nager.date/v3/PublicHolidays/{year}/{countryCode}
   - **Autenticação**: Nenhuma (API pública)
   - **Exemplo**: https://api.nager.date/v3/PublicHolidays/2024/BR

   ## 📚 Testes Implementados
   
   ### Teste de Integração (HolidayServiceIntegrationTest)
   - 11 casos de teste
   - Faz requisições reais à API
   - Valida resposta JSON
   - Testa cache de dados
   - Testa validação de datas
   
   ### Teste Unitário (HolidayServiceUnitTest)
   - 8 casos de teste
   - Usa Mockito para mockar API
   - Testa sem dependência de rede
   - Valida tratamento de erros

   ## 🚀 Como Executar Localmente

   ```bash
   # Compilar projeto
   mvn clean compile

   # Executar testes
   mvn test

   # Apenas testes de integração (requer internet)
   mvn test -Dtest=HolidayServiceIntegrationTest

   # Apenas testes unitários (sem internet)
   mvn test -Dtest=HolidayServiceUnitTest

   # Build completo
   mvn clean package

   # Verificar code quality
   mvn checkstyle:check

   # Executar aplicação
   java -jar target/study-routine-manager.jar
   ```

   ## 🔗 Referências
   - API Docs: https://date.nager.at/
   - Repo da API: https://github.com/nager/Nager.Date

   ## 📝 Notas
   - Nenhuma quebra de compatibilidade (backward compatible)
   - API fallback automático em caso de erro
   - Cache reduz requisições à API
   - Testes abrangem múltiplos cenários
   ```

3. **Labels** (opcional):
   - `enhancement`
   - `api-integration`
   - `testing`

4. Clique em **"Submit new issue"**

---

## 🔄 PASSO 2: Criar Pull Request

### ⏱️ Tempo estimado: 5 minutos

**Opção A: Via GitHub Web**

1. Acesse: https://github.com/Rocha-007/gerenciamento-rotina-estudos/pull/new/entrega-intermediaria

2. **Preencha o formulário PR**:

   **Title**:
   ```
   feat: Integração com API de Feriados Nacionais (Etapa 2)
   ```

   **Description**:
   ```markdown
   ## 🎯 Objetivo
   Integrar com API pública de feriados nacionais brasileiros.

   ## 📋 Issue Relacionada
   Closes #1  (ou o número da issue que você criou)

   ## 🔄 Tipo de Mudança
   - [x] Nova funcionalidade (feature)
   - [ ] Correção de bug
   - [ ] Breaking change
   - [ ] Atualização de documentação

   ## ✅ Checklist
   - [x] Código compilável
   - [x] Testes passando
   - [x] Checkstyle validado
   - [x] README atualizado
   - [x] CHANGELOG atualizado
   - [x] Nenhuma dependência externa desnecessária
   - [x] API sem autenticação (pública)
   - [x] Tratamento de erros implementado

   ## 📊 Resumo das Mudanças
   - HolidayService para consumo de API
   - Holiday model para dados
   - 11 testes de integração
   - 8 testes unitários
   - Integração com StudyRoutineService
   - Cache para otimizar requisições

   ## 🧪 Como Testar
   ```bash
   mvn clean test
   mvn clean package
   java -jar target/study-routine-manager.jar
   ```

   ## 📸 Capturas de Tela
   (Se aplicável, adicione capturas da UI mostrando alertas de feriados)

   ## 🚀 Deployment Notes
   - Nenhuma configuração adicional necessária
   - API é pública e gratuita
   - Sem custo de infraestrutura
   ```

3. Clique em **"Create pull request"**

**Opção B: Via Command Line**

```bash
cd "c:\Users\Adm\OneDrive\Documentos\bootcamp II\gerenciamento-rotina-estudos"

# Verificar status
git status

# Visualizar mudanças
git log --oneline origin/main..entrega-intermediaria
```

---

## 🚀 PASSO 3: Deploy da Aplicação

### ⏱️ Tempo estimado: 15-30 minutos

Como é uma **aplicação Java Desktop (Swing)**, temos várias opções:

### **Opção A: Executar Localmente (Mais Simples)**

1. No repositório, execute:
```bash
mvn clean package
java -jar target/study-routine-manager.jar
```

2. **Documentar no README**:
```markdown
### Deploy

A aplicação pode ser executada localmente em qualquer máquina com Java 11+.

#### Procedimento de Deploy Local:

1. Clonar repositório
2. Executar: `mvn clean package`
3. Executar: `java -jar target/study-routine-manager.jar`

Não há necessidade de servidor web ou hospedagem em nuvem.
```

### **Opção B: Docker (Recomendado para Portabilidade)**

1. Criar arquivo `Dockerfile`:
```dockerfile
FROM openjdk:11-jre-slim
WORKDIR /app
COPY target/study-routine-manager.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
```

2. Build da imagem:
```bash
docker build -t study-routine-manager .
```

3. Executar container:
```bash
docker run -it study-routine-manager
```

### **Opção C: Criar Executável Windows/Mac (Avançado)**

Use ferramentas como:
- **JPackage** (Java 14+)
- **Launch4j** (Windows .exe)
- **Gradle Appimage** (Linux)

---

## ✨ PASSO 4: Fazer Merge para Main

### ⏱️ Tempo estimado: 3 minutos

**Após aprovação do PR:**

**Via Web GitHub**:
1. Vá para o PR que criou
2. Clique em **"Merge pull request"**
3. Selecione **"Squash and merge"** (opcional, para manter histórico limpo)
4. Confirme

**Via Command Line**:
```bash
cd "c:\Users\Adm\OneDrive\Documentos\bootcamp II\gerenciamento-rotina-estudos"

# Mude para main/master
git checkout main

# Puxe as mudanças mais recentes
git pull origin main

# Faça merge da branch
git merge entrega-intermediaria

# Envie para o GitHub
git push origin main

# (Opcional) Delete a branch local
git branch -d entrega-intermediaria

# (Opcional) Delete a branch remota
git push origin -d entrega-intermediaria
```

---

## 📊 PASSO 5: Criar Release (Opcional)

1. Acesse: https://github.com/Rocha-007/gerenciamento-rotina-estudos/releases/new

2. **Tag version**: `v1.1.0-intermediaria`

3. **Release title**: `Etapa 2 - Entrega Intermediária`

4. **Description**:
```markdown
# Entrega Intermediária - Etapa 2

## 🎉 Novo
- Integração com API pública de feriados nacionais
- 19 testes de integração e unitários
- Alertas de feriados para agendamento de tarefas
- Cache de requisições à API

## 📦 Download
Disponível em: [study-routine-manager.jar](link)

## 🚀 Como Usar
```bash
java -jar study-routine-manager.jar
```

## 📝 Changelog
Veja [CHANGELOG.md](CHANGELOG.md) para detalhes completos.
```

---

## 📋 PDF DE ENTREGA (FINAL)

Crie um PDF contendo:

1. **Identificação do Aluno**
   - Nome: [Seu Nome]
   - Matrícula: [Sua Matrícula]
   - Turma: Bootcamp II

2. **Links**
   - GitHub Repository: https://github.com/Rocha-007/gerenciamento-rotina-estudos
   - Branch: `entrega-intermediaria`
   - Pull Request: [Link do PR]
   - Issue: [Link da Issue]

3. **Descrição do Projeto**
   - Resumo da Etapa 2
   - API integrada
   - Testes implementados

4. **Como Executar**
   ```bash
   git clone https://github.com/Rocha-007/gerenciamento-rotina-estudos.git
   cd gerenciamento-rotina-estudos
   git checkout entrega-intermediaria
   mvn clean package
   java -jar target/study-routine-manager.jar
   ```

5. **Capturas de Tela**
   - Tela da aplicação
   - Terminal mostrando testes passando
   - Output do Maven

6. **Barema de Avaliação** (autoanálise)
   - Integração com API: ✓
   - Issue e Branch: ✓
   - Testes de Integração: ✓
   - Deploy/Publicação: ✓ (Local)
   - CI/CD e README: ✓

---

## 🎯 Checklist Final

```
✓ Código implementado e testado
✓ Commit realizado com mensagem descritiva
✓ Branch pushed para GitHub
✓ Issue criada com descrição completa
✓ Pull Request criado e linkado com Issue
✓ Testes passando
✓ Documentação atualizada (README, CHANGELOG)
✓ Deploy executável (local ou web)
✓ Código quality check passando
✓ PR aprovado e merged

📤 Próximo passo: Enviar PDF de entrega
```

---

## 🚨 Troubleshooting

### Problema: Testes não compilam
**Solução**: Ensure Maven e Java 11+ estão instalados
```bash
java -version
mvn --version
```

### Problema: API indisponível
**Solução**: A API é pública, mas pode ter timeout
```bash
# Teste a API manualmente
curl https://api.nager.date/v3/PublicHolidays/2024/BR
```

### Problema: Push rejeitado
**Solução**: Sincronize com main antes de push
```bash
git pull origin main
git push origin entrega-intermediaria
```

### Problema: Merge conflict
**Solução**: Resolva conflitos manualmente no editor
```bash
git merge entrega-intermediaria
# Edite arquivos conflitantes
git add .
git commit -m "Merge entrega-intermediaria"
```

---

## 📞 Suporte

Se tiver dúvidas sobre:
- **API**: Consulte https://date.nager.at/
- **Maven**: Consulte https://maven.apache.org/
- **Git**: Consulte https://git-scm.com/doc
- **JUnit**: Consulte https://junit.org/junit5/

---

**Versão**: 1.1.0-intermediaria  
**Status**: Pronto para entrega  
**Data**: 11 de Maio de 2026  
**Autor**: Eduardo Rocha (@Rocha-007)
