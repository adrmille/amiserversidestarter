# AMI Server Side Starter
This is some kind of "work in progress Frankenstein project" mixing parts of some of my public and private projects.  
The idea is to consolidate usage and example of :
- Springboot
- Camunda
- JPA
- Others to come...

In order to ease the creation of new projects based on this stack.

## Build
> mvn clean install

## Launch
### Using Maven
> cd amisss-application  
> mvn spring-boot:run 

### Using Java
> java -jar amisss-application-1.0.0.jar


## Functionalities
### Spring
#### Profile
see:
- application.properties
- MarketMarginPositionDaoTest.java

### Logger
- directly in the application properties: application.properties
- via logback lib: logback-spring.xml

### Datasource 
#### auto generation
- application.properties

#### In-memory / remote postgres / file
- amisss-core/src/main/resources/META-INF/datasource.properties

#### Using 2 datasources
- CoreConfiguration.java

### Camunda
#### BPMN flows
*bpmn files can be open using the Camunda Modeler: https://camunda.com/download/modeler/*
- amisss-core/src/test/resources/META-INF/flow/system/

#### Camunda process testing
- ThreadSynchronizationTest.java

#### Camunda multi-thread processes
- META-INF/flow/system/Multi_thread_test.bpmn
- see also: CamundaProcessHelper.java