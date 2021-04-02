# mcs_quarkus_poc
## Modules relationship
Parent POM
	controller -> Common
		   -> services
		   -> data
	services   -> data
		   -> common
		   -> repository
	repository -> data
	common     -> data

## Create Parent
```
mvn archetype:generate -DgroupId=group.id -DartifactId=mcs_quarkus_poc
```

## Set Parent as Parent
```xml
<packaging>pom</packaging>
```

## Add modules
```
mvn archetype:generate -DgroupId=group.id  -DartifactId=controller  
mvn archetype:generate -DgroupId=group.id  -DartifactId=services  
mvn archetype:generate -DgroupId=group.id  -DartifactId=repository  
mvn archetype:generate -DgroupId=group.id  -DartifactId=data  
mvn archetype:generate -DgroupId=group.id  -DartifactId=common  
```

## Add dependency
Add the following xml code to the module to add the dependency
```xml
<dependency>
	<groupId>${group_id}</groupId>
	<artifactId>${artifact_id}</artifactId>
	<version>${pom.version}</version>
	<type>jar</type>
	<scope>compile</scope>
</dependency>
```

## Run the maven project as Quarkus project
### Modification under Parent POM
Add the following xml code to parent pom
```xml
<properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <quarkus-plugin.version>1.8.1.Final</quarkus-plugin.version>
    <quarkus.platform.artifact-id>quarkus-universe-bom</quarkus.platform.artifact-id>
    <quarkus.platform.group-id>io.quarkus</quarkus.platform.group-id>
    <quarkus.platform.version>1.8.1.Final</quarkus.platform.version>
</properties>
....
<dependencyManagement>
    <dependencies>
      <dependency>
        <groupId>${quarkus.platform.group-id}</groupId>
        <artifactId>${quarkus.platform.artifact-id}</artifactId>
        <version>${quarkus.platform.version}</version>
        <type>pom</type>
        <scope>import</scope>
      </dependency>
    </dependencies>
</dependencyManagement>
....
<build>
  <plugins>
    <plugin>
      <groupId>io.quarkus</groupId>
      <artifactId>quarkus-maven-plugin</artifactId>
      <version>${quarkus-plugin.version}</version>
    </plugin>
  </plugins>
</build>
```

### Modification under the main module
Add to Controller pom
```xml
<properties>
    <quarkus-plugin.version>1.8.1.Final</quarkus-plugin.version>
  </properties>
...
<build>
  <plugins>
    <plugin>
      <groupId>io.quarkus</groupId>
      <artifactId>quarkus-maven-plugin</artifactId>
      <version>${quarkus-plugin.version}</version>
      <executions>
        <execution>
          <goals>
            <goal>generate-code</goal>
            <goal>build</goal>
          </goals>
        </execution>
      </executions>
    </plugin>
  </plugins>
</build>
```

## Generate config file
*application.properties file should be placed under the main module*  
So, in our case, right click on the main module and open a terminal to execute the command below. 
```
mvn io.quarkus:quarkus-maven-plugin:1.8.1.Final:generate-config -Dfile=application.properties
```

## Set host and port in application.properties
```
quarkus.http.host=localhost
quarkus.http.port=8081
```

## Add extension
Execute the command below directly on the terminal
```
mvn quarkus:add-extension -Dextensions="resteasy, resteasy-jackson"
```

## Run
```
mvn clean compile quarkus:dev -Ddebug
```
