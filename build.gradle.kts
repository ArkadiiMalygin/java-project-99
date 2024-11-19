plugins {
	application
	id("io.freefair.lombok") version "8.6"
	id("org.springframework.boot") version "3.4.0-SNAPSHOT"
	id("io.spring.dependency-management") version "1.1.6"
	id("com.github.ben-manes.versions") version "0.50.0"
}

group = "hexlet.code"
version = "0.0.1-SNAPSHOT"

//java {
//	toolchain {
//		languageVersion = JavaLanguageVersion.of(21)
//	}
//}

application { mainClass.set("hexlet.code.app.AppApplication") }

repositories {
	mavenCentral()
	maven { url = uri("https://repo.spring.io/milestone") }
	maven { url = uri("https://repo.spring.io/snapshot") }
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-validation")

	implementation("org.openapitools:jackson-databind-nullable:0.2.6")
	implementation("org.mapstruct:mapstruct:1.5.5.Final")
	annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")

	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
	testImplementation("org.springframework.security:spring-security-test")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	runtimeOnly("com.h2database:h2")
	implementation("org.springframework.boot:spring-boot-starter")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	// for tests
	testImplementation(platform("org.junit:junit-bom:5.10.0"))
	testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
// Понадобится когда мы начнем работать с аутентификацией
	testImplementation("org.springframework.security:spring-security-test")

	implementation("org.instancio:instancio-junit:3.6.0")
	implementation("net.javacrumbs.json-unit:json-unit-assertj:3.2.2")
	implementation("net.datafaker:datafaker:2.0.2")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
