import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
	application
	checkstyle
	jacoco
	id("io.freefair.lombok") version "8.6"
	id("org.springframework.boot") version "3.4.0-SNAPSHOT"
	id("io.spring.dependency-management") version "1.1.6"
	id("com.github.ben-manes.versions") version "0.50.0"
//	id("io.sentry.jvm.gradle") version "4.13.0"
}

group = "hexlet.code"
version = "0.0.1-SNAPSHOT"


application {
	mainClass.set("hexlet.code.app.AppApplication")
}

repositories {
	mavenCentral()
	maven { url = uri("https://repo.spring.io/milestone") }
	maven { url = uri("https://repo.spring.io/snapshot") }
}

dependencies {

	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-devtools")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-configuration-processor")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")

	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")

	implementation("org.openapitools:jackson-databind-nullable:0.2.6")
	implementation("org.mapstruct:mapstruct:1.5.5.Final")
	annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")

	implementation("org.springframework.boot:spring-boot-starter")

	implementation("org.instancio:instancio-junit:3.6.0")
	implementation("net.javacrumbs.json-unit:json-unit-assertj:3.2.2")
	implementation("net.datafaker:datafaker:2.0.2")

	runtimeOnly("org.postgresql:postgresql")
	runtimeOnly("com.h2database:h2:2.2.224")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
	testImplementation(platform("org.junit:junit-bom:5.10.1"))
	testImplementation("org.junit.jupiter:junit-jupiter:5.10.1")
	//testRuntimeOnly("org.junit.platform:junit-platform-launcher")

}

tasks.jacocoTestReport {
	reports {
		xml.required.set(true)
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
	testLogging {
		exceptionFormat = TestExceptionFormat.FULL
		events = mutableSetOf(TestLogEvent.FAILED, TestLogEvent.PASSED, TestLogEvent.SKIPPED)
		// showStackTraces = true
		// showCauses = true
		showStandardStreams = true
	}
}
//TODO Sentry setup
//buildscript {
//	repositories {
//		mavenCentral()
//	}
//}
//SENTRY_AUTH_TOKEN=sntrys_eyJpYXQiOjE3MzIwMzIwMDMuNzUwNDcsInVybCI6Imh0dHBzOi8vc2VudHJ5LmlvIiwicmVnaW9uX3VybCI6Imh
// 0dHBzOi8vZGUuc2VudHJ5LmlvIiwib3JnIjoiYXJrYWRpaW1hbHlnaW4ifQ==_53XZ5FUVRiMKZbmoTMkT1maywUqF84NZwRmeEByzIU8
//sentry {
//	// Generates a JVM (Java, Kotlin, etc.) source bundle and uploads your source code to Sentry.
//	// This enables source context, allowing you to see your source
//	// code as part of your stack traces in Sentry.
//	includeSourceContext = true
//
//	org = "arkadiimalygin"
//	projectName = "java-spring-boot"
//	authToken = System.getenv("SENTRY_AUTH_TOKEN")
//}
