# testutils
Java project that contains testutil classes.

## HibernateConfigurationHelper

If you want to test you DAO / Gateway layer then you will get issues testing mappings that have constraints. You can use this class to crob not required dependencies.

### Most important methods
```java
/**
 * Add your classpath files to the helper class.
 */
addResource(String url)

/*
 * Use this method to add all given files to your hibernate configuration object.
 */
addAllResourcesTo(Configuration configuration)*
```
