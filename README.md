# spring-gzip-filter
Spring GZIP Filter

This implementation provides a simple but powerful servlet filter to automatic adds GZIP support for your web requests, it's not 100% customizable now but on future I expect that.

Gradle:
```groovy
dependencies {
    compile("com.github.leonardoxh:GZIPFilter:0.1")
}
```

or Maven
```xml
<dependency>
   <groupId>com.github.leonardoxh</groupId>
   <artifactId>GZIPFilter</artifactId>
   <version>0.1</version>
</dependency>
```

It's easy use with spring, with spring boot just create a bean and we do the rest, doesn't matter if spring-security is present or not.
```java
@Bean
public FilterRegistrationBean gzipFilter() {
    FilterRegistrationBean filterBean = new FilterRegistrationBean();
    filterBean.setFilter(new GZIPFilter());
    return filterBean;
}
```
