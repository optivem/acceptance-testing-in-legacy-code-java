# System Test (Java)

## Architecture

The system test environment uses Docker Compose to run:
- **monolith**: The main e-shop application
- **json-server**: An external ERP API server that provides product pricing data

## Instructions

Open up the 'system-test' folder

```shell
cd system-test
```

Check that you have Powershell 7

```shell
$PSVersionTable.PSVersion
```

Start System

```shell
.\start.ps1
```

Run All Tests

```shell
./gradlew test
```

Run Smoke Tests Only

```shell
./gradlew test --tests com.optivem.eshop.systemtest.smoketests.*
```

Stop System

```shell
.\stop.ps1
```

View Logs

```shell
# View all logs
.\logs.ps1

# View specific service logs
.\logs.ps1 monolith
.\logs.ps1 json-server
```