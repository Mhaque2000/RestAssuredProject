# 🧪 REST Assured End-to-End API Automation Framework

This project demonstrates a complete **End-to-End (CRUD)** API automation framework using **REST Assured**, **TestNG**, and **Maven**.  
It is based on the Google Maps API example (from Rahul Shetty Academy), covering full API flow — **Create**, **Read**, **Update**, and **Delete** operations.

---

## 🚀 Project Overview

### 🧩 Framework Highlights
- ✅ Built with **REST Assured** for API testing  
- ✅ **TestNG** used as the test runner & report generator  
- ✅ Uses **Maven** for build automation  
- ✅ **POJO classes** for clean request/response serialization  
- ✅ Parameterized & reusable payload builder  
- ✅ Integrated **`testng.xml`** for easy suite execution  

---

## 🧱 Tech Stack

| Tool / Library | Purpose |
|----------------|----------|
| **Java 8+** | Programming language |
| **REST Assured** | API testing library |
| **TestNG** | Testing framework |
| **Maven** | Build and dependency management |
| **Jackson Databind** | JSON serialization/deserialization |
| **SLF4J (Optional)** | Internal logging support |

---

## 🗂️ Project Structure

```bash
E2EProject/
│
├── pom.xml                     # Maven project configuration and dependencies
├── testng.xml                  # TestNG suite file
├── README.md                   # Project documentation
│
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── pojo/           # POJOs for request payloads
│   │       │   ├── AddPlace.java
│   │       │   ├── Location.java
│   │       │   └── UpdatePlace.java
│   │       └── utils/          # Utility classes
│   │           └── TestDataBuilder.java
│   │
│   └── test/
│       └── java/
│           └── com/
│               └── mahamood/
│                   ├── BaseTest.java               # Common setup (base URI, config)
│                   └── EndToEndCRUDOperation.java  # Main CRUD tests
│
└── target/
    └── surefire-reports/       # Test reports generated after execution
