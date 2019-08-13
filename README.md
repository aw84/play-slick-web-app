# Create database
## Using H2 database
Run: ```java -cp h2*.jar org.h2.tools.Shell```

JDBC URL to DB to create: `jdbc:h2:c:/some-dir/db-name`.
Optionally User and Password can pe provided.
Key point here is to provide local path JDBC URL.

After database successfully created Slick configuration needs to be updated
to reflect changes in database location.

Next thing is to create database structure 

### Run creation script

To start from scratch `ddl-drop.sql` is provided. 
```shell script
java -cp c:\dev\h2\bin\h2-1.4.199.jar org.h2.tools.RunScript ^
-url "jdbc:h2:c:\dev\scala\play-slick-web-app\db\the-database" ^
-user sa -password "sa" -script db\ddl-drop.sql
```

Create database structure:
```shell script
java -cp c:\dev\h2\bin\h2-1.4.199.jar org.h2.tools.RunScript ^
-url "jdbc:h2:c:\dev\scala\play-slick-web-app\db\the-database" ^
-user sa -password "sa" -script db\ddl-create.sql
```

Insert data:
```shell script
java -cp c:\dev\h2\bin\h2-1.4.199.jar org.h2.tools.RunScript ^
-url "jdbc:h2:c:\dev\scala\play-slick-web-app\db\the-database" ^
-user sa -password "sa" -script db\dml-insert.sql
```
