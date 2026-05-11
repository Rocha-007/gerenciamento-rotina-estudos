#!/usr/bin/env python3
"""
Script para criar Issue no GitHub automaticamente via API
Uso: python criar_issue.py
"""

import json
import subprocess
import sys

def criar_issue():
    """Cria uma Issue no GitHub via GitHub CLI (gh)"""
    
    titulo = "feat: Integração com API Pública de Feriados Nacionais Brasileiros"
    
    descricao = """## 📋 Descrição
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

# Executar aplicação
java -jar target/study-routine-manager.jar
```

## 🔗 Referências
- API Docs: https://date.nager.at/
- Repo da API: https://github.com/nager/Nager.Date"""
    
    labels = ["enhancement", "api-integration", "testing"]
    
    print("🔄 Tentando criar Issue no GitHub...")
    print(f"Título: {titulo}")
    print(f"Labels: {', '.join(labels)}")
    
    try:
        # Tenta usar GitHub CLI
        cmd = [
            "gh", "issue", "create",
            "--title", titulo,
            "--body", descricao,
            "--label", ",".join(labels),
            "--repo", "Rocha-007/gerenciamento-rotina-estudos"
        ]
        
        result = subprocess.run(cmd, capture_output=True, text=True)
        
        if result.returncode == 0:
            print("✅ Issue criada com sucesso!")
            print(result.stdout)
            return True
        else:
            print("❌ Erro ao criar Issue com GitHub CLI")
            print("Instalando GitHub CLI via winget...")
            subprocess.run(["winget", "install", "GitHub.cli"], check=False)
            print("\nTente novamente após reinstalar GitHub CLI")
            return False
            
    except FileNotFoundError:
        print("❌ GitHub CLI não está instalado")
        print("\n📝 Alternativa: Criar a Issue manualmente")
        print("\n1. Acesse: https://github.com/Rocha-007/gerenciamento-rotina-estudos/issues/new")
        print(f"2. Título: {titulo}")
        print(f"3. Descrição: (copie do arquivo GUIA_PRATICO_ENTREGA_FINAL.md)")
        print(f"4. Labels: {', '.join(labels)}")
        return False

if __name__ == "__main__":
    criar_issue()
