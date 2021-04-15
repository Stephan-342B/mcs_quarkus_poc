# mcs_quarkus_poc
## Launch
You need to have *maven 3.6.3* and *java 11* to be able to run the project  

```
mvn clean install
mvn clean compile quarkus:dev -Ddebug
```

## Create a clean architecture
The basic concept is to use the same way we do to build a multi-module maven project. So, to be more specific, we need to create a maven based-module project then change the runner as *Quarkus* instead of *Maven*.  

## Structure (Goal)
- Parent POM
	- [Module 1] controller (*starter*)
		- [Dependency] Common
		- [Dependency] services
		- [Dependency] data
	- [Module 2] services
		- [Dependency] data
		- [Dependency] common
		- [Dependency] repository
	- [Module 3] repository
		- [Dependency] data
	- [Module 4] common
		- [Dependency] data

![](microservice_structure.png)
### Create Parent
First, we need to create a parent to hold our modules. This is what we call **Parent POM**.  
```
mvn archetype:generate -DgroupId=group.id -DartifactId=mcs_quarkus_poc
```

### Set Parent as Parent
```xml
<packaging>pom</packaging>
```

### Create modules
'til now our parent pom has been created. Next, let's add some modules
```
mvn archetype:generate -DgroupId=group.id  -DartifactId=controller  
mvn archetype:generate -DgroupId=group.id  -DartifactId=services  
mvn archetype:generate -DgroupId=group.id  -DartifactId=repository  
mvn archetype:generate -DgroupId=group.id  -DartifactId=data  
mvn archetype:generate -DgroupId=group.id  -DartifactId=common  
```

### Add dependency between modules
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
### Modification Parent POM
Add the following xml code to the parent pom
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

### Modification main module (starter)
Add to the Controller module's pom
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
So, in our case, right click on the main module (*controller module*) and open a terminal to execute the command below. 
```
mvn io.quarkus:quarkus-maven-plugin:1.8.1.Final:generate-config -Dfile=application.properties
```

## Set host and port in application.properties
```
quarkus.http.host=localhost
quarkus.http.port=8080
```

## Add extension
```
mvn quarkus:add-extension -Dextensions="resteasy, resteasy-jackson"
```
