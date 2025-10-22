Nota Fiscal Eletrônica
===
Comunicador de nota fiscal e nota fiscal do consumidor da [fazenda](http://www.nfe.fazenda.gov.br/portal/principal.aspx).<br/>
[![Java CI](https://github.com/wmixvideo/nfe/workflows/Continuous%20Integration/badge.svg)](https://github.com/wmixvideo/nfe/workflows/Java%20CI/badge.svg)
[![Coverage Status](https://coveralls.io/repos/github/wmixvideo/nfe/badge.svg?branch=master)](https://coveralls.io/github/wmixvideo/nfe?branch=master)
[![Maven Central](https://img.shields.io/maven-central/v/com.github.wmixvideo/nfe)](https://search.maven.org/artifact/com.github.wmixvideo/nfe)
[![Apache 2.0 License](https://img.shields.io/badge/license-apache%202.0-green.svg) ](https://github.com/wmixvideo/nfe/blob/master/LICENSE)

## Atenção
Este é um projeto colaborativo, sinta-se à vontade em usar e colaborar com o mesmo.<br/>

Antes de submeter um pull request, verifique a estrutura seguida pelo projeto e procure incluir no mesmo testes unitários que garantam que a funcionalidade funciona como o esperado.

Possuímos um grupo de WhatsApp para discussões sobre o desenvolvimento da lib: https://chat.whatsapp.com/LFmqpkoiIYc6Zy3d4TnZGU

## Antes de usar
Antes de começar a implementar, é altamente recomendável a leitura da documentação oficial que o governo disponibiliza em http://www.nfe.fazenda.gov.br/portal

Caso não possua conhecimento técnico para criar notas fiscais, um profissional da área (como um contador) pode lhe auxiliar.

## Instalação

### Diretamente pelo Maven

```xml
<dependency>
  <groupId>com.github.wmixvideo</groupId>
  <artifactId>nfe</artifactId>
  <version>${latest.release}</version>
</dependency>
```
Para acessar a lista de versões disponíveis, acesse a página de release [aqui](https://github.com/wmixvideo/nfe/releases) no github. 

### Diretamente pelo código fonte (última versão em desenvolvimento)
 ```console
    git clone https://github.com/wmixvideo/nfe
    mvn clean install
  ```

## Como usar
Basicamente você precisará de uma implementação de **NFeConfig** (exemplificado abaixo), com informações de tipo de emissão, certificados
digitais, e uma instância da **WsFacade**, essa classe tem a responsabilidade de fazer a ponte entre o seu sistema e a
comunicação com os webservices da Sefaz.

```java
// Exemplo de configuracao para acesso aos serviços da Sefaz.
public class NFeConfigTeste extends NFeConfig {

    private KeyStore keyStoreCertificado = null;
    private KeyStore keyStoreCadeia = null;

    @Override
    public DFUnidadeFederativa getCUF() {
        return DFUnidadeFederativa.SC;
    }

    @Override
    public String getCertificadoSenha() {
        return "senha_certificado";
    }

    @Override
    public String getCadeiaCertificadosSenha() {
        return "senha_cadeia";
    }

    @Override
    public KeyStore getCertificadoKeyStore() throws KeyStoreException {
        if (this.keyStoreCertificado == null) {
            this.keyStoreCertificado = KeyStore.getInstance("PKCS12");
            try (InputStream certificadoStream = new FileInputStream("/tmp/certificado.pfx")) {
                this.keyStoreCertificado.load(certificadoStream, this.getCertificadoSenha().toCharArray());
            } catch (CertificateException | NoSuchAlgorithmException | IOException e) {
                this.keyStoreCadeia = null;
                throw new KeyStoreException("Nao foi possibel montar o KeyStore com a cadeia de certificados", e);
            }
        }
        return this.keyStoreCertificado;
    }

    @Override
    public KeyStore getCadeiaCertificadosKeyStore() throws KeyStoreException {
        if (this.keyStoreCadeia == null) {
            this.keyStoreCadeia = KeyStore.getInstance("JKS");
            try (InputStream cadeia = new FileInputStream("/tmp/cadeia.jks")) {
                this.keyStoreCadeia.load(cadeia, this.getCadeiaCertificadosSenha().toCharArray());
            } catch (CertificateException | NoSuchAlgorithmException | IOException e) {
                this.keyStoreCadeia = null;
                throw new KeyStoreException("Nao foi possibel montar o KeyStore com o certificado", e);
            }
        }
        return this.keyStoreCadeia;
    }
}
```
### Configurar pelo repositório de certificados do Windows:

#### Carregando os alias disponíveis(certificados instalados) no Windows:
```java
KeyStore keyStoreCert = KeyStore.getInstance("Windows-MY", "SunMSCAPI"); 
keyStoreCert.load(null, null);
Enumeration<String> aliasEnum = keyStoreCert.aliases();
```

Após isso é necessário algum método para o usuário escolher entre um destes alias, 
talvez por meio de um JOptionPane(<java7)
 ou de um ChoiceDialog<String>(>=Java8), e então fazer o load com a respectivo alias escolhido e sua senha.

### Alguns exemplos
Considere para os exemplos abaixo que **config** seja uma instância da implementação da interface **NFeConfig**.

#### Status dos webservices
```java
NFStatusServicoConsultaRetorno retorno = new WSFacade(config).consultaStatus(DFUnidadeFederativa.SC, DFModelo.NFE);
System.out.println(retorno.getStatus());
System.out.println(retorno.getMotivo());
```

O Resultado será (caso o webservice responsável por SC esteja OK):
```
107
Servico em operacao
```

#### Envio do lote para o sefaz
Popule os dados do lote a ser enviado para o Sefaz:

```java
NFLoteEnvio lote = new NFLoteEnvio();
// setando os dados do lote
```

Faça o envio do lote através do facade:
```java
final NFLoteEnvioRetorno retorno = new WSFacade(config).enviaLote(lote);
```

#### Corrige nota
Faça a correção da nota através do facade:
```java
final NFEnviaEventoRetorno retorno = new WSFacade(config).corrigeNota(chaveDeAcessoDaNota, textoCorrecao, sequencialEventoDaNota);
```

#### Cancela nota
Faça o cancelamento da nota através do facade:
```java
final NFCancelamentoRetornoDados retorno = new WSFacade(config).cancelaNota(chaveDeAcessoDaNota, protocoloDaNota, motivoCancelaamento);
```

#### Consulta nota por chave de acesso ou NSU
Faça a consulta da nota através do facade:
```java
final NFDistribuicaoIntRetorno retorno = new WSFacade(config).consultarDistribuicaoDFe(cnpj, uf, chaveAcesso, nsu, ultNsu);
```

### Convertendo objetos Java em XML
Qualquer objeto que seja uma representação XML do documento NFe, pode ser obtido seu XML de forma fácil bastando chamar o método **toString**, por exemplo, para conseguir o XML do lote, invoque o toString

```java
NFLoteEnvio lote = new NFLoteEnvio();
// setando os dados do lote
...

// Obtendo o xml do objeto
String xmlGerado = lote.toString();
```

### Convertendo nota XML em Java
Existe uma classe que pode receber um File/String e converter para um objeto NFNota, faça da seguinte forma:
```java
final NFNota nota = new DFPersister().read(NFNota.class, xmlNota);
final NFNotaProcessada notaProcessada = new DFPersister().read(NFNotaProcessada.class, xmlNotaProcessada);
```

Ou desabilitando o modo estrito (habilitado por padrão):
```java
final NFNota nota = new DFPersister(false).read(NFNota.class, xmlNota);
```

### Armazenando notas autorizadas
Você precisará armazenar as notas autorizadas por questões legais e também para a geração do DANFE, uma forma de fazer é armazenar o xml das notas ao enviar o lote:
```java
final List<NFNota> notas = lote.getNotas();
// Armazena os xmls das notas
...
```
Ao fazer a consulta do lote, crie um objeto do tipo **NFNotaProcessada** e adicione o protocolo da nota correspondente, além da nota assinada:
```java
// Carregue o xml da nota do local que foi armazenado
final String xmlNotaRecuperada;
// Assine a nota
final String xmlNotaRecuperadaAssinada = new AssinaturaDigital(config).assinarDocumento(xmlNotaRecuperada);
// Converta para objeto java
final NFNota notaRecuperadaAssinada = new DFPersister().read(NFNota.class, xmlNotaRecuperadaAssinada);
// Crie o objeto NFNotaProcessada
final NFNotaProcessada notaProcessada = new NFNotaProcessada();
notaProcessada.setVersao(new BigDecimal(NFeConfig.VERSAO_NFE));
notaProcessada.setProtocolo(protocolo);
notaProcessada.setNota(notaRecuperadaAssinada);
// Obtenha o xml da nota com protocolo
String xmlNotaProcessadaPeloSefaz = notaProcessada.toString();
```

### Funcionalidades
* Possui validação de campos a nível de código;
* Valida o XML de envio de lote através dos xsd's disponibilizados pela Sefaz;
* Gera o XML dos objetos de maneira simples, invocando o metodo toString() dá conta do recado.
* Suporta diferentes TimeZones, com a implementacao do devido metodo na classe de configuração (DFConfig e suas subclasses).

## Serviços disponíveis
| Serviço                       | Status              |
| ----------------------------- | :-----------------: |
| Envio lote                    | Estável             |
| Consulta lote                 | Estável             |
| Consulta status               | Estável             |
| Consulta nota                 | Estável             |
| Download nota                 | Estável             |
| Corrige nota                  | Estável             |
| Cancela nota                  | Estável             |
| Inutiliza nota                | Estável             |
| Consulta cadastro             | Estável             |
| Manifestação de destinatário  | Estável             |

## Requisitos

JDK >= 1.8<br>
Maven >= 3.x

## Criação do Java KeyStore (JKS)
Para usar os serviços da nota fiscal são necessários dois certificados:
1) O certificado do cliente que será utilizado para assinar as notas e comunicar com o fisco (fornecido por uma entidade certificadora);
2) A cadeia de certificados da SEFAZ que queremos acesso;

Os certificados são um ponto crítico já que estes tem validade de apenas um ano (certificado cliente).
Além disso as SEFAZ vem trocando suas cadeias de certificado a cada atualização. Dessa forma se surgirem erros de SSL vale a pena verificar se existem novas atualizações de certificados.
Para gerar a cadeia de certificados, disponibilizamos um pequeno helper que baixa os certificados das SEFAZ e gera o arquivo automaticamente:
```java
public static void main(String args[]){
    try {
        FileUtils.writeByteArrayToFile(new File("/tmp/producao.cacerts"), DFCadeiaCertificados.geraCadeiaCertificados(DFAmbiente.PRODUCAO, "senha"));
        FileUtils.writeByteArrayToFile(new File("/tmp/homologacao.cacerts"), DFCadeiaCertificados.geraCadeiaCertificados(DFAmbiente.HOMOLOGACAO, "senha"));
    } catch (Exception e) {
        e.printStackTrace();
    }
}
```

# Integração Maven com AWS CodeArtifact

Este documento descreve o processo de autenticação e configuração do **Maven** para utilização do **AWS CodeArtifact**, bem como o **deploy** de uma aplicação **Java 11**.

---

## 📋 Pré-requisitos

Antes de iniciar, verifique se você possui:

* Conta AWS com permissões de acesso ao CodeArtifact e IAM.
* AWS CLI configurado localmente (`aws configure`).
* Java 11 instalado.
* Maven 3.6+ instalado.
* Repositório CodeArtifact previamente criado.

---

## 🔑 1. Obter o token de autenticação do CodeArtifact

O Maven utiliza um **token temporário** para autenticar com o CodeArtifact. Esse token expira após 12 horas.

Execute o comando abaixo para gerar o token:

```bash
aws codeartifact get-authorization-token --domain mister --domain-owner 434209040133 --region us-east-1 --query authorizationToken --output text
```

> **Dica:** Você pode automatizar esse processo em scripts de build para renovação automática do token.

---

## ⚙️ 2. Atualizar o arquivo `settings.xml`

O arquivo `settings.xml` (geralmente localizado em `~/.m2/settings.xml`) deve conter as credenciais e repositórios do CodeArtifact.

Exemplo de configuração:

```xml
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
          https://maven.apache.org/xsd/settings-1.0.0.xsd">

  <servers>
    <server>
      <id>mister-maven</id>
      <username>aws</username>
      <password>${env:CODEARTIFACT_AUTH_TOKEN}</password>
    </server>
  </servers>

  <profiles>
    <profile>
      <id>mister-maven</id>
      <repositories>
        <repository>
          <id>mister-maven</id>
          <url>https://mister-434209040133.d.codeartifact.us-east-1.amazonaws.com/maven/maven/</url>
        </repository>
      </repositories>
      <pluginRepositories>
        <pluginRepository>
          <id>mister-maven</id>
          <url>https://mister-434209040133.d.codeartifact.us-east-1.amazonaws.com/maven/maven/</url>
        </pluginRepository>
      </pluginRepositories>
    </profile>
  </profiles>

  

</settings>
```

---

## 🌍 3. Exportar o token como variável de ambiente

Para evitar armazenar o token diretamente no arquivo `settings.xml`, exporte-o como variável de ambiente:

```bash
FOR /F "tokens=*" %g IN ('aws codeartifact get-authorization-token --domain mister --domain-owner 434209040133 --region us-east-1 --query authorizationToken --output text') do (SET CODEARTIFACT_AUTH_TOKEN=%g)
```

Verifique se o token foi definido corretamente:

```bash
echo $CODEARTIFACT_AUTH_TOKEN
```

---

## 🚀 4. Deploy da aplicação no CodeArtifact

Antes do deploy, confirme se o `pom.xml` contém a configuração de distribuição:

```xml
<distributionManagement>
  <repository>
    <id>codeartifact</id>
    <url>https://<nome-do-repo>-<id-da-conta-aws>.d.codeartifact.<região>.amazonaws.com/maven/<nome-do-repo>/</url>
  </repository>
</distributionManagement>
```

Então, execute o deploy com:

```bash
mvn clean deploy
```

> O Maven usará o `settings.xml` configurado e o token exportado para autenticar no CodeArtifact.

---

## 🧰 5. Automatização (opcional)

Para CI/CD, adicione a geração e exportação do token no pipeline antes da etapa de build/deploy:

Exemplo (GitHub Actions):

```yaml
- name: Configurar CodeArtifact
  run: |
    export CODEARTIFACT_AUTH_TOKEN=$(aws codeartifact get-authorization-token \
      --domain my-domain \
      --domain-owner 123456789012 \
      --query authorizationToken \
      --output text)
    echo "CODEARTIFACT_AUTH_TOKEN=$CODEARTIFACT_AUTH_TOKEN" >> $GITHUB_ENV
```

---

## 🧾 Referências

* [AWS CodeArtifact Documentation](https://docs.aws.amazon.com/codeartifact/latest/ug/welcome.html)
* [Maven Settings Reference](https://maven.apache.org/settings.html)
* [AWS CLI Reference for CodeArtifact](https://docs.aws.amazon.com/cli/latest/reference/codeartifact/)

---

**Autor:** Bruno Pereira
**Versão:** 1.0
**Data:** 22/10/2025
