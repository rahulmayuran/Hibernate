# Hibernate
To learn about the hibernate functions and reduce the core java JDBC boiler plate coding , Significance of using an ORM Tool and HQL.
Complete Documentation --> https://docs.jboss.org/hibernate/orm/5.3/userguide/html_single/Hibernate_User_Guide.html#entity-inheritance-joined-table

1. Embeddable(An Entity class must be marked as @Embeddable and in another class we can use it as @Embedded)
2. C3PO(Connection pooling example)
3. Added Enumerated classes and linked to Entity class

# Branches

Each branch describes about various aspects of Hibernate that covers

1. Basic CRUD operations (with CFG/ without CFG, by Properties interface/ with MetaDataSources/ with Configuration)
2. HQL with Query interface using CreateQuery, CreateNativeQuery, CreateSQLQuery methods
3. get() and load() methods to fetch data
4. Default First level cache and enabling second level caching to reduce SQL query duplicates
5. LAZY and EAGER fetching that depends upon object usages. 
6. Mappings that has 1 to 1, 1 to Many, Many to 1 and Many to Many which are annotations based.
7. Transaction Managements
