# redis_template_test

This is a maven project.

Run the following to compile the project:

`mvn compile assembly:single`

Run the resulting jar with:

`java -jar target/redtemp-1.0-SNAPSHOT-jar-with-dependencies.jar`

This will connect locally to the port 6379. No authentication or TLS. The function that is run (which executes all of the saves) is `demo` in the `com.example.App` class.