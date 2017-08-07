# JSON Validation Test

This repo is intended to demonstrate how to get hibernate validations working
on value types like UUID and other custom values.

The problem is that string-parsed value types tend to throw exceptions when the
input string is improperly formatted, which throws a 400. However, a 400 isn't
quite as nice as the 422 you get when validations fail, as they'll give you a
a list of all the validations on the object that failed to clear.

Currently, validations run after the the input JSON has been deserialized to an
object, but many value types like UUID will fail in the deserialization process
if improperly formatted. This means that the an object with such a value type
field will fail before the validator ever has a chance to run.

This repo contains an example of my current approach to avoiding this problem.
The incoming JSON is mapped to a builder object with much looser requirements,
in this case raw strings instead of the custom value types themselves. The
builder contains all the hibernate validations which can check for correct
formatting. The builder's `build()` method is then responsible for converting
the strings into the domain-specific value types.

This requires the resource object to call `build()` before it can get a
well-formed object. It would be nice if the framework could call build whenever
it sees this pattern :) .

How to start the Test application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/validator-test-1.0-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`
