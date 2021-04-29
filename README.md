## references

https://www.cnblogs.com/freud/p/8397934.html


if u run start error , like exception information in down :  
so maybe is aop dao zhi de 
```text
java.lang.IllegalStateException: Failed to register @ServerEndpoint class: class com.envision.it.platforminfra.h5push.app.service.TemplateWebSocket$$EnhancerBySpringCGLIB$$cec1644c
	at org.springframework.web.socket.server.standard.ServerEndpointExporter.registerEndpoint(ServerEndpointExporter.java:148) ~[spring-websocket-4.3.18.RELEASE.jar:4.3.18.RELEASE]
	at org.springframework.web.socket.server.standard.ServerEndpointExporter.registerEndpoints(ServerEndpointExporter.java:129) ~[spring-websocket-4.3.18.RELEASE.jar:4.3.18.RELEASE]
	at org.springframework.web.socket.server.standard.ServerEndpointExporter.afterSingletonsInstantiated(ServerEndpointExporter.java:107) ~[spring-websocket-4.3.18.RELEASE.jar:4.3.18.RELEASE]
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:781) ~[spring-beans-4.3.18.RELEASE.jar:4.3.18.RELEASE]
	at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:867) ~[spring-context-4.3.18.RELEASE.jar:4.3.18.RELEASE]
	at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:543) ~[spring-context-4.3.18.RELEASE.jar:4.3.18.RELEASE]
	at org.springframework.boot.context.embedded.EmbeddedWebApplicationContext.refresh(EmbeddedWebApplicationContext.java:122) ~[spring-boot-1.5.14.RELEASE.jar:1.5.14.RELEASE]
	at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:693) [spring-boot-1.5.14.RELEASE.jar:1.5.14.RELEASE]
	at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:360) [spring-boot-1.5.14.RELEASE.jar:1.5.14.RELEASE]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:303) [spring-boot-1.5.14.RELEASE.jar:1.5.14.RELEASE]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1118) [spring-boot-1.5.14.RELEASE.jar:1.5.14.RELEASE]
	at org.springframework.boot.SpringApplication.run(SpringApplication.java:1107) [spring-boot-1.5.14.RELEASE.jar:1.5.14.RELEASE]
	at com.envision.it.platforminfra.h5push.PlatformServiceApp.main(PlatformServiceApp.java:16) [classes/:na]
Caused by: javax.websocket.DeploymentException: Cannot deploy POJO class [com.envision.it.platforminfra.h5push.app.service.TemplateWebSocket$$EnhancerBySpringCGLIB$$cec1644c] as it is not annotated with @ServerEndpoint
	at org.apache.tomcat.websocket.server.WsServerContainer.addEndpoint(WsServerContainer.java:203) ~[tomcat-embed-websocket-8.5.31.jar:8.5.31]
	at org.springframework.web.socket.server.standard.ServerEndpointExporter.registerEndpoint(ServerEndpointExporter.java:145) ~[spring-websocket-4.3.18.RELEASE.jar:4.3.18.RELEASE]
	... 12 common frames omitted
```