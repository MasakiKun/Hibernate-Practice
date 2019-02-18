plugins {
	id("java")
}

group = "kr.ayukawa"

configure<JavaPluginConvention> {
	sourceCompatibility = JavaVersion.VERSION_11
	targetCompatibility = JavaVersion.VERSION_11
}

repositories {
	mavenCentral()
}

dependencies {
	compile("org.hibernate:hibernate-core:5.4.1.Final")
	compile("com.h2database:h2:1.4.197")
	compileOnly("org.projectlombok:lombok:1.18.6")
}
