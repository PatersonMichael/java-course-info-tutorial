# Course Info Project

---
This is a project based on Sander Mak's "Building a java application using Java SE 17" Course.

- Built with Maven
- JSON binding using Jackson
- Logging using slf4j
- Unit testing with JUnit 5
- REST API with JAX-RS and Jersey

### Main features
Course Info CLI
- takes an author name as an argument ex: sander-mak
- sends requests to Pluralsight API to retreive courses from an author
- returns a list of Pluralsight Courses serialized from JSON to a Java record

Course REST API
- responds to a GET request to localhost:8080/courses
  - returns a JSON of courses
- responds to a POST request to localhost:8080/courses/{id}/notes
  - POST body can include plain text to add notes to a course