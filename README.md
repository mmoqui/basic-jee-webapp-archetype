# A Basic JEE Web Application Archetype

This is a Maven Archetype to generate the basic skeleton of a JEE Web Application.
This skeleton provides:
*   The bootstrap of a REST-based application at the /api URI path
*   PrimeFaces to facilitate the creation of JSF-based rich UI components
*   JUnit and Mockito for the unit tests with the Maven plugin Surefire
*   Arquillian with a Wildfly managed container for the integration tests with the Maven plugin Failsafe

The integration tests aren't executed by default as they take longer time. They are defined within a Maven profile: it.
So, to run them, you just have to activate the profile.

In my mind, the unit tests should test only a specific code with all the dependencies mocked whereas the integration tests
are to test that a whole part works fine.
