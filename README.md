# 🏥 Laboratory Test Simulation System

This project simulates a laboratory system that handles various medical tests, including blood type tests, AIDS tests, and thyroid tests. The system processes patient details, manages test requests, generates random results for different medical conditions, and displays the results accordingly.

## 🚀 Features

- **👩‍⚕️ Patient Management**: Handles patient details, including personal information and medical test results.
- **🩺 Test Types**: Supports various medical tests like Blood Type, AIDS, and Thyroid.
- **🎲 Result Generation**: Random test results are generated for each test.
- **📊 Display Results**: Provides a display of test results for each patient.
- **⚙️ Test Processing**: Includes operations for running tests and checking their results.

## 🗂️ Project Structure

- **Patient**: Manages patient information such as name, age, and list of tests.
- **Laboratory**: A singleton class managing test requests, test results, and patients.
- **Test**: Base class for different medical tests that are extended for specific test cases (e.g., BloodType, AIDS, Thyroid).
- **iCheckTest**: Interface for checking test results.
- **iPrivate**: Interface for processing test results.
- **ShowResults**: Class for managing and displaying test results.

## 💻 Usage

- Add patients to the system.
- Assign different tests to patients (e.g., Blood Type, AIDS, Thyroid).
- The system will automatically generate random results for each test.
- Results can be viewed and processed by the user.

## 📄 Example Output

```text
Patient: John Doe
Age: 30
Blood Type: A
AIDS: Negative
Thyroid: Normal
```
