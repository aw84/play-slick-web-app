# Create database
## Using H2 database
Run: ```java -cp h2*.jar org.h2.tools.Shell```

JDBC URL to DB to create: `jdbc:h2:c:/some-dir/db-name`.
Optionally User and Password can pe provided.
Key point here is to provide local path JDBC URL.

After database successfully created Slick configuration needs to be updated
to reflect changes in database location.

Next thing is to create database structure. This is handled by Play Evolutions.

# Run

Define environment variable `PLAY_APPS_ROOT` pointing to root folder
that contains this sources

Download latest `sbt-launch*.jar` and put it in `sbt-dist/bin` directory.

Run `sbt.bat ";compile;run"`

