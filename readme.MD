# Spring Netty Application Sample

### Dependency injection
* Always prefer constructor based injection over method injection or field injection(`@Autowired`).
  - Prevent NPE at compile time
  - `final` fields can be injected
  - Prevent cyclic dependency at compile time
  
* `@RequiredArgsConstructor`
  - A Bean's dependency can be injected without `@Autowired` when it has a single constructor with its parameters being able to be registered as Bean/
  
### ApplicationListener
* Triggered automatically when the listener is registered as Bean.