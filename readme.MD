# Spring Netty Application Sample

Sample Netty server(gateway) application on top of spring framework.

## Scheme

Gateway establishes N:M connection between downstream clients and upstream servers to simulate routing of fixed-length frame message.

Connection establishment follows below steps:

1) Gateway connects to upstream servers, receives message containing id from each server. 
2) Gateway puts each server id and channel to map in `ChannelRepository`.
3) Clients connect to the gateway then send message containing id from each client.
4) Gateway puts each client id and channel to map in `ChannelRepository`.

After connections are set, messages sent from client follow below steps:

1) Client sends message to gateway containing destination client id, service server id and content.
2) Gateway routes message to respective upstream service server.
3) Upstream server processes message content and sends it to gateway.
4) Gateway routes processed message to destination client.

Servers and clients simulated with putty.

## Notes

### Dependency injection
* Always prefer constructor based injection over method injection or field injection(`@Autowired`).
  - Prevent NPE at compile time
  - `final` fields can be injected
  - Prevent cyclic dependency at compile time
  
* `@RequiredArgsConstructor`
  - A Bean's dependency can be injected without `@Autowired` when it has a single constructor with its parameters being able to be registered as Bean/
  
### Two ways to implement `@ConfigurationProperties`
1) Common use-case:
```java
@Getter
@Setter
@ConfigurationProperties(prefix="spring.datasource")
public class HikariConfigProperties{
    private int user;
    //...
}
```

while `application.properties`:

```
spring.datasource.url = [url]
spring.datasource.username = [username]
spring.datasource.password = [password]
spring.datasource.driverClassName = oracle.jdbc.OracleDriver
```
2) Can also be applied to `@Bean`. For example,

```java
@Bean
@ConfigurationProperties(prefix="spring.datasource")
public DataSource dataSource() {
        return new DataSource();
}
```

while `DataSource.java`:
```java
public class DataSource {
    private String url;
    private String driverClassName;
    private String username;
    private String password;
    //getters & setters, etc.
}
```

### ApplicationListener
* Triggered automatically when the listener is registered as Bean.

### Issues
* `@ConfigurationProperties` not recognizing `application.properties` - erase `<packaging>pom</packaging>` in pom.xml