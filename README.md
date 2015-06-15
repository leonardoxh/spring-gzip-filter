# spring-gzip-filter
Spring GZIP Filter

Deprecated
-----
Spring 1.2.2+ has the compression enabled by default via <code>application.properties</code>

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

Licence:
=================
```
Copyright 2015 Leonardo Rossetto

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
