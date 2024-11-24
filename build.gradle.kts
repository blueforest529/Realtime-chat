plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.3.3"
    id("io.spring.dependency-management") version "1.1.6"
    id("org.graalvm.buildtools.native") version "0.10.2"
    id("com.diffplug.spotless") version "6.11.0"
}

group = "forloooop"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

spotless {
    kotlin {
        target("**/*.kt")
        indentWithSpaces()
        endWithNewline()
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // H2
    runtimeOnly("com.h2database:h2")

    // Spring Data Jpa
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // JSON
    implementation("org.json:json:20210307")

    // JDSL
    // https://mvnrepository.com/artifact/com.linecorp.kotlin-jdsl/kotlin-jdsl
    val JDSL_VERSION = "3.5.2"
    implementation("com.linecorp.kotlin-jdsl:jpql-dsl:$JDSL_VERSION")
    implementation("com.linecorp.kotlin-jdsl:jpql-render:$JDSL_VERSION")
    implementation("com.linecorp.kotlin-jdsl:hibernate-support:$JDSL_VERSION")
    implementation("com.linecorp.kotlin-jdsl:spring-data-jpa-support:$JDSL_VERSION")

    // AutoSource
    testImplementation("io.github.autoparams:autoparams:8.3.0")

    // Tsid
    implementation("io.hypersistence:hypersistence-utils-hibernate-63:3.8.2")

    // JWT 라이브러리 추가
    implementation("io.jsonwebtoken:jjwt-api:0.11.5")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.5")

    // MySql
    // https://mvnrepository.com/artifact/mysql/mysql-connector-java
    implementation("mysql:mysql-connector-java:8.0.33")

    // Elasticsearch
    // https://mvnrepository.com/artifact/org.springframework.data/spring-data-elasticsearch
    implementation("org.springframework.boot:spring-boot-starter-data-elasticsearch")
    implementation("org.springframework.data:spring-data-elasticsearch:5.3.2")
    implementation("org.elasticsearch.client:elasticsearch-rest-high-level-client:7.17.0")

    //swagger
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")

    //web socket
    implementation("org.springframework.boot:spring-boot-starter-websocket")

    //redis
    implementation("org.springframework.boot:spring-boot-starter-data-redis")

    //jackson
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")

    //http
    implementation("org.apache.httpcomponents.client5:httpclient5:5.4")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
