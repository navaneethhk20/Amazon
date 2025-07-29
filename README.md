# Selenium Java Automation Framework

A comprehensive test automation framework built with Selenium WebDriver, Java, TestNG, and Page Object Model (POM) design pattern for web application testing.

## 🚀 Framework Overview

This framework is designed to test web applications with a focus on login functionality, user account management, and order processing. It follows industry best practices and provides detailed reporting with Allure Reports.

## 🛠️ Technologies & Tools

- **Java** - Programming language
- **Selenium WebDriver** - Web automation tool
- **TestNG** - Testing framework
- **Page Object Model (POM)** - Design pattern
- **Allure Reports** - Test reporting
- **Jenkins** - CI/CD integration
- **Git** - Version control
- **Maven** - Build automation tool
- **ChromeDriver** - Browser automation

## 📁 Project Structure

```
Amazon/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── org/example/
│   │           ├── base/
│   │           │   └── CommonToAllPages.java
│   │           ├── driver/
│   │           │   └── DriverManager.java
│   │           ├── page_pom/
│   │           │   ├── LoginPage.java
│   │           │   ├── MyAccount.java
│   │           │   └── MyOrders.java
│   │           └── utils/
│   │               └── PropertiesReader.java
│   ├── test/
│   │   └── java/
│   │       └── org/example/
│   │           ├── base/
│   │           │   └── CommonToAllTests.java
│   │           ├── sample/
│   │           │   └── SampleTest.java
│   │           └── tests/
│   │               └── VerifyLogin.java
│   └── resources/
│       └── data.properties
├── allure-results/
├── target/
├── pom.xml
└── README.md
```

## 🎯 Features

- **Page Object Model (POM)** - Maintainable and reusable page objects
- **TestNG Integration** - Powerful testing framework with annotations
- **Cross-browser Testing** - Support for Chrome and other browsers
- **Data-driven Testing** - External data source support
- **Allure Reporting** - Beautiful and detailed test reports
- **Jenkins Integration** - CI/CD pipeline support
- **Git Version Control** - Source code management
- **Parallel Execution** - Faster test execution
- **Screenshot Capture** - Automatic screenshot on test failures

## 🔧 Setup Instructions

### Prerequisites

1. **Java 8+** installed
2. **Maven** installed
3. **Git** installed
4. **Chrome Browser** installed
5. **ChromeDriver** (managed automatically)

### Installation

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd Amazon
   ```

2. **Install dependencies**
   ```bash
   mvn clean install
   ```

3. **Update configuration**
    - Configure `data.properties` file with test data
    - Update browser preferences in `DriverManager.java`

## 🏃‍♂️ Running Tests

### Command Line Execution

```bash
# Run all tests
mvn clean test

# Run specific test class
mvn clean test -Dtest=VerifyLogin

# Run tests with specific browser
mvn clean test -Dbrowser=chrome

# Run tests in parallel
mvn clean test -DthreadCount=3
```

### TestNG XML Execution

```bash
mvn clean test -DsuiteXmlFile=testng.xml
```

## 📊 Test Reporting

### Allure Reports

1. **Generate Allure Results**
   ```bash
   mvn clean test
   ```

2. **Generate and Serve Allure Report**
   ```bash
   allure generate allure-results --clean
   allure serve allure-results
   ```

### TestNG Reports

TestNG generates HTML reports automatically in the `target/surefire-reports` directory.

## 🧪 Test Cases

### Login Functionality
- ✅ Valid login credentials
- ✅ Invalid login credentials
- ✅ Empty username/password validation
- ✅ Login page elements verification

### Account Management
- ✅ Account information display
- ✅ Profile update functionality
- ✅ Account navigation

### Order Management
- ✅ Order history verification
- ✅ Order details validation
- ✅ Order status checking

## 📄 Configuration Files

### data.properties
```properties
# Browser Configuration
browser=chrome
headless=false
timeout=10

# Application URLs
baseUrl=https://example.com
loginUrl=https://example.com/login

# Test Data
username=testuser
password=testpass123
expecteduser=testuser
```

### Key Classes

#### VerifyLogin.java
```java
@Test
public void loginTest() {
    WebDriver driver = new ChromeDriver();
    LoginPage loginPage = new LoginPage(driver);
    String userLogged = loginPage.validLogin(
        PropertiesReader.readKey("username"),
        PropertiesReader.readKey("password")
    );
    Assert.assertEquals(userLogged, PropertiesReader.readKey("expecteduser"));
}
```

## 🔄 CI/CD Integration

### Jenkins Pipeline

```groovy
pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                git 'repository-url'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn clean test'
            }
        }
        stage('Report') {
            steps {
                allure([
                    includeProperties: false,
                    jdk: '',
                    properties: [],
                    reportBuildPolicy: 'ALWAYS',
                    results: [[path: 'allure-results']]
                ])
            }
        }
    }
}
```

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/new-feature`)
3. Commit your changes (`git commit -m 'Add new feature'`)
4. Push to the branch (`git push origin feature/new-feature`)
5. Create a Pull Request

## 📝 Best Practices

- Follow Page Object Model design pattern
- Use meaningful test method names
- Implement proper wait strategies
- Add assertions for test validations
- Capture screenshots on failures
- Use data-driven approach for test data
- Maintain clean and readable code
- Regular code reviews and refactoring

## 🐛 Troubleshooting

### Common Issues

1. **ChromeDriver not found**
    - Ensure ChromeDriver is in PATH or use WebDriverManager

2. **Test failures due to timeouts**
    - Increase wait times in configuration
    - Use explicit waits instead of implicit waits

3. **Element not found**
    - Verify element locators
    - Check page load status

## 📞 Support

For issues and questions:
- Create an issue in the repository
- Contact the development team
- Check documentation and wiki

## 📜 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

**Happy Testing! 🚀**