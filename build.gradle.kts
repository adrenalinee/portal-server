plugins {
    id("org.jetbrains.kotlin.jvm") version "2.1.0"
    id("org.jetbrains.kotlin.plugin.allopen") version "2.1.0"
    id("org.jetbrains.kotlin.plugin.jpa") version "2.1.0"
//    id("com.google.devtools.ksp") version "2.1.0-1.0.29"
    id("io.micronaut.application") version "4.5.3"
    id("com.gradleup.shadow") version "8.3.6"
    id("io.micronaut.aot") version "4.5.3"
    kotlin("kapt") version "2.1.0"
}

version = "0.1"
group = "malibu.portal"

val kotlinVersion=project.properties.get("kotlinVersion")
val jdslVersion = "3.5.5"

repositories {
    mavenCentral()
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

dependencies {
    kapt("io.micronaut.data:micronaut-data-processor")
    kapt("io.micronaut:micronaut-http-validation")
    kapt("io.micronaut.security:micronaut-security-annotations")
    kapt("io.micronaut.serde:micronaut-serde-processor")
    kapt("com.querydsl:querydsl-apt:5.0.0:jakarta")

    implementation("io.micronaut.beanvalidation:micronaut-hibernate-validator")
    implementation("io.micronaut.validation:micronaut-validation")
    implementation("io.micronaut.data:micronaut-data-hibernate-jpa")
    implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")
    implementation("io.micronaut.security:micronaut-security-jwt")
    implementation("io.micronaut.serde:micronaut-serde-jackson")
    implementation("io.micronaut.sql:micronaut-jdbc-hikari")

    implementation("org.jetbrains.kotlin:kotlin-reflect:${kotlinVersion}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${kotlinVersion}")

    implementation("com.github.f4b6a3:ulid-creator:5.2.3")

//    implementation("org.hibernate.orm:hibernate-jpamodelgen")

//    implementation("com.github.ryarnyah:micronaut-querydsl:3.0.0")
//    implementation("com.snourian.micronaut:querydsl-dynamic-query:0.2.0")


//    implementation("com.querydsl:querydsl-jpa:5.1.0:jakarta")
//    implementation("com.querydsl:querydsl-sql:5.1.0")
//    annotationProcessor("com.querydsl:querydsl-apt:5.1.0:jakarta")


    kapt("com.querydsl:querydsl-apt:5.1.0:jakarta")



    implementation("com.querydsl:querydsl-jpa:5.1.0:jakarta")
//    kapt("com.querydsl:querydsl-apt:5.1.0:jakarta")
//    kapt("jakarta.annotation:jakarta.annotation-api")
//    kapt("jakarta.persistence:jakarta.persistence-api")

    implementation("com.linecorp.kotlin-jdsl:jpql-dsl:${jdslVersion}")
    implementation("com.linecorp.kotlin-jdsl:jpql-render:${jdslVersion}")
    implementation("com.linecorp.kotlin-jdsl:hibernate-support:${jdslVersion}")


    compileOnly("io.micronaut:micronaut-http-client")
    runtimeOnly("ch.qos.logback:logback-classic")
    runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")
//    runtimeOnly("com.h2database:h2")
    runtimeOnly("com.mysql:mysql-connector-j")
    testImplementation("io.micronaut:micronaut-http-client")
    aotPlugins(platform("io.micronaut.platform:micronaut-platform:4.8.2"))
    aotPlugins("io.micronaut.security:micronaut-security-aot")
}


application {
    mainClass = "malibu.portal.ApplicationKt"
}
java {
    sourceCompatibility = JavaVersion.toVersion("21")
}


graalvmNative.toolchainDetection = false

micronaut {
//    ignoredAutomaticDependencies.add("io.micronaut.data:micronaut-data-processor")
    runtime("netty")
    testRuntime("kotest5")
    processing {
        incremental(true)
        annotations("malibu.portal.*")
    }
    aot {
        // Please review carefully the optimizations enabled below
        // Check https://micronaut-projects.github.io/micronaut-aot/latest/guide/ for more details
        optimizeServiceLoading = false
        convertYamlToJava = false
        precomputeOperations = true
        cacheEnvironment = true
        optimizeClassLoading = true
        deduceEnvironment = true
        optimizeNetty = true
        replaceLogbackXml = true
        configurationProperties.put("micronaut.security.jwks.enabled","false")
    }
}


tasks.named<io.micronaut.gradle.docker.NativeImageDockerfile>("dockerfileNative") {
    jdkVersion = "21"
}

//val generated = file("src/main/generated")
//
//// querydsl QClass 파일 생성 위치를 지정
//tasks.withType<JavaCompile> {
//    options.generatedSourceOutputDirectory.set(generated)
//}
//
//// kotlin source set 에 querydsl QClass 위치 추가
//sourceSets {
//    main {
//        kotlin.srcDirs += generated
//    }
//}
//
//// gradle clean 시에 QClass 디렉토리 삭제
//tasks.named("clean") {
//    doLast {
//        generated.deleteRecursively()
//    }
//}