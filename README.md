# Excercise

Implement the following test scenarios.

## Problem One - Form Authentication

http://the-internet.herokuapp.com/login 

given this small sample form-login
- create positive and negative testcase(s) that verify the result of the login-attempt is correct

## Problem Two - HTTP StatusCodes

http://the-internet.herokuapp.com/status_codes

given this page that can return multiple http statusCodes
- verify that upon navigation to a certain page the correct http statusCode is returned

### **Optional** Problem Three - REST API 

https://jsonplaceholder.typicode.com/users

given this rest api that returns a proper json response
- verify that user Nicholas Runolfsdottir V exists
- verify that if this user exists, his address contains the following data
```
{
	"address": {
		"street": "Ellsworth Summit",
		"suite": "Suite 729",
		"city": "Aliyaview",
		"zipcode": "45169",
		"geo": {
			"lat": "-14.3990",
			"lng": "-120.7677"
		}
	}
}
```

# Frameworks

You should use the following frameworks to solve the problems
- [JDK8](https://openjdk.java.net/projects/jdk8/)
- [Cuccumber](https://cucumber.io/docs/installation/)
- [Gherkin](https://cucumber.io/docs/gherkin/reference/)
- [Selenium Chromedriver](https://chromedriver.chromium.org/getting-started)
- **Optional** [Maven](https://maven.apache.org/) 

You are free to add any other libraries you may need to add to solve the problems, the only restriction is that to run the example it is not necessary to configure/install third party software
