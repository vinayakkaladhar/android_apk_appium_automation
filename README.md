# Android Calculator App — Appium Automation

Automated test suite for the Android Calculator APK using Appium, TestNG, and Java.

## Tech Stack

| Tool | Version |
|---|---|
| Language | Java 8 |
| Test framework | TestNG 7.3.0 |
| Appium client | java-client 7.3.0 |
| Build tool | Maven |
| IDE | IntelliJ IDEA |
| Platform | Android 11.0 |

---

## Prerequisites

Before running tests, make sure you have the following installed and configured:

1. **Java 8+** — `java -version` should report 1.8 or higher
2. **Maven** — `mvn -version`
3. **Android Studio** — needed to run and manage the Android emulator
4. **ADB** (Android Debug Bridge) — comes with Android Studio; ensure it is on your `PATH`
5. **Appium server** — install via npm or use the Appium Desktop client
   ```bash
   npm install -g appium
   ```
6. **Android emulator** — create and start an AVD with Android 11 (API 30) in Android Studio

---

## Project Structure

```
appium/
├── src/
│   ├── main/java/
│   │   ├── POM/
│   │   │   └── CalcPage.java          # Page Object for the Calculator screen
│   │   └── Utilities/
│   │       ├── DriverUtilities.java   # Appium driver setup and capabilities
│   │       └── ListenerTest.java      # TestNG listener — screenshots on failure
│   └── test/java/
│       └── CalculatorApp.java         # Test class
├── src/
│   └── Calculator.apk                 # The APK under test (bundled with the project)
├── testng.xml                         # TestNG suite configuration
└── pom.xml                            # Maven dependencies and build config
```

---

## Setup

### 1. Clone and import the project

```bash
git clone <repo-url>
cd android_apk_appium_automation/appium
```

Open IntelliJ IDEA → **File > Open** → select the `appium` folder → import as a Maven project.

### 2. Install Maven dependencies

```bash
mvn clean install -DskipTests
```

### 3. Configure the emulator

- Open Android Studio → **Device Manager** → create a Pixel device with **Android 11 (API 30)**
- Start the emulator; confirm it is visible:
  ```bash
  adb devices
  # Should show:  emulator-5554   device
  ```

The driver is hard-coded to `deviceName=emulator-5554`. If your emulator has a different name, update it in `DriverUtilities.java`:

```java
cap.setCapability("deviceName", "emulator-5554");   // change if needed
cap.setCapability("platformVersion", "11.0");        // match your emulator's Android version
```

### 4. Install the APK on the emulator

```bash
adb install src/Calculator.apk
```

### 5. Start the Appium server

```bash
appium
# Default address: http://0.0.0.0:4723/wd/hub
```

The server URL is configured in `DriverUtilities.java`. If you run Appium on a different port, update that line accordingly.

---

## Running the Tests

**From the terminal (recommended):**

```bash
cd appium
mvn test
```

**From IntelliJ:**

Right-click `testng.xml` → **Run**.

**From IntelliJ (individual test):**

Right-click `CalculatorApp.java` → **Run**.

---

## Test Cases

| Class | Method | Description |
|---|---|---|
| `CalculatorApp` | `verifyAddFunctionality` | Enters two digits (2 + 4), asserts the result equals 6 |

The suite is defined in `testng.xml` and runs tests in parallel with a thread count of 2.

---

## Reports and Screenshots

- **HTML report:** `appium/target/surefire-reports/emailable-report.html`
- **TestNG report:** `appium/target/surefire-reports/index.html`
- **Failure screenshots:** saved to `appium/` root, named `<timestamp><testMethodName>.png`

Screenshots are captured automatically on test failure by `ListenerTest.java`.

---

## Troubleshooting

| Symptom | Likely cause | Fix |
|---|---|---|
| `Connection refused` on driver init | Appium server not running | Run `appium` and retry |
| `Could not find device` | Emulator not started or wrong device name | Check `adb devices`, update `deviceName` in `DriverUtilities.java` |
| `App not installed` | APK not installed on emulator | Run `adb install src/Calculator.apk` |
| `NoSuchElementException` | Wrong element ID or app not in foreground | Verify the APK package name matches `com.android2.calculator3` |

---

## Author

Vinayak Kaladhar
