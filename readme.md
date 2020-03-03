```
mvn clean compile exec:java -Dexec.mainClass=bzh.guillaume.alb.keepalive.Main -Dexec.args="https://identity.hotpads.com/identity/datarouter/healthcheck 359"
```
or
```
mvn clean package
java -jar target/alb-keepalive.jar https://identity.hotpads.com/identity/datarouter/healthcheck 359
```
