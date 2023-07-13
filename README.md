## Short description
This is an example project which describes the incompatibility of micronaut and lombok.copyableAnnotations

## Project structure 

1. That project contains [lombok.config](./lombok.config) with `lombok.copyableAnnotations += jakarta.inject.Named`
2. That project contains 2 services ([FirstService](./src/main/java/com/example/services/FirstService.java) and [SecondService](./src/main/java/com/example/services/SecondService.java)) They inherit the interface [Service](./src/main/java/com/example/services/Service.java)
3. That project contains [CustomApplicationReadyEvent](./src/main/java/com/example/CustomApplicationReadyEvent.java) whose try to inject the first service and annotated lombok and jakarta annotations


## Project behavior

If you run on linux:
```shell
./gradlew clean asseble
```

You can see the compiled [CustomApplicationReadyEvent](./src/main/java/com/example/CustomApplicationReadyEvent.java):
```java
@Singleton
public class CustomApplicationReadyEvent implements ApplicationEventListener<StartupEvent> {
    private static final Logger log = LoggerFactory.getLogger(CustomApplicationReadyEvent.class);
    @Named("first")
    private final Service service;

    // ...

    public CustomApplicationReadyEvent(@Named("first") final Service service) {
        this.service = service;
    }
}
```

Micronaut tries to inject in the constructor bean, then try to inject in the field. Hence, we can see an exception when we run `./gradlew clean test`:
```
io.micronaut.context.exceptions.BeanInstantiationException: Error instantiating bean of type  [com.example.CustomApplicationReadyEvent]

Message: class com.example.$CustomApplicationReadyEvent$Definition tried to access private field com.example.CustomApplicationReadyEvent.service (com.example.$CustomApplicationReadyEvent$Definition and com.example.CustomApplicationReadyEvent are in unnamed module of loader 'app')
Path Taken: new CustomApplicationReadyEvent(Service service)
```