# Garage API

API responsible for managing the vehicle mechanic workflow.
Tech Challenge for the 15SOAT course.

## 🗒️ Information

- [Documentation]()

## 📋 Prerequisites

- [JDK 25](https://jdk.java.net/archive/)
- [IDE 2026.1](https://www.jetbrains.com/idea/download/)
- [Apache Maven 3.9.11](https://maven.apache.org/download.cgi)

## ⚙️ Setup

```sh
export M2_HOME=~/app/apache-maven-3.9.11
export M2=$M2_HOME/bin
export PATH=$PATH:$M2
```

```sh
export JAVA_HOME=~/app/jdk-25.0.2
export PATH=$PATH:$JAVA_HOME/bin
```

### 📂 Cloning the repository

```sh
git clone https://github.com/rodsordi/15SOAT-TechChallenge.git
```

### 👌 Running unit tests

```sh
mvn test
```

### 📦 Package building

```sh
mvn clean install -DskipTests
```

### 🐳 Running the application with Docker

```sh
docker build -t garage:0.0.1-SNAPSHOT .
```

### 🚀 Running the application with Docker Compose

```sh
docker compose up
```

## 📄 Swagger

| Ambiente | Url                                                     | 
|----------|---------------------------------------------------------|
| local    | [link](http://localhost:8080/api/swagger-ui/index.html) |

## 🧪 Integration tests:

### 🍿 Running integration tests

```sh
mvn test -DintegrationTests
```

### 📂 Postman collection

```sh
curl 
```

## Quality

## 🛡️ Vulnerabilities

- Request an api-key on https://nvd.nist.gov/developers/request-an-api-key
- Obs: This sptep is optional, but it will provide more accurate results and a higher rate limit for vulnerability checks.

```sh

NVD_API_KEY=${confirmed_api_key_on_email}
mvn clean verify -DskipTests -Dowasp
```

## 🧹 Quality coverage

```sh
curl -u admin:admin -X POST "http://localhost:9000/api/users/change_password?login=admin&previousPassword=admin&password=Sonarqube@2026"
SONAR_TOKEN=$(curl -u admin:Sonarqube@2026 -X POST "http://localhost:9000/api/user_tokens/generate?name=setup-token" | grep -oP '"token":"\K[^"]+')
echo $SONAR_TOKEN
mvn clean verify sonar:sonar -Dsonar.host.url=http://localhost:9000 -Dsonar.qualitygate.wait=true -Dsonar.token=$SONAR_TOKEN
```

## 📌 Versão

- Using [SemVer](https://semver.org/) for version control.

## ✒ Autores

- [Rodrigo de Sordi - RM372537](https://github.com/rodsordi)


