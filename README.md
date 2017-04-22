# Gradle for Android and Java Final Project

In this project, you will create an app with multiple flavors that uses
multiple libraries and Google Cloud Endpoints. The finished app will consist
of four modules. A Java library that provides jokes, a Google Cloud Endpoints
(GCE) project that serves those jokes, an Android Library containing an
activity for displaying jokes, and an Android app that fetches jokes from the
GCE module and passes them to the Android Library for display.

## Dependencies

A google-services.json file is required and should be placed in the app/src/free module directory. 


##What Did I Learn?

Learnt the role of Gradle in building Android Apps and how to use
Gradle to manage apps of increasing complexity. Learnt to:

* Add free and paid flavors to an app, and set up your build to share code between them
* Factor reusable functionality into a Java library
* Factor reusable Android functionality into an Android library
* Configure a multi project build to compile your libraries and app
* Use the Gradle App Engine plugin to deploy a backend
* Configure an integration test suite that runs against the local App Engine development server
* (Optional) Added loading indicator to be displayed before the joke is loaded from GCE
