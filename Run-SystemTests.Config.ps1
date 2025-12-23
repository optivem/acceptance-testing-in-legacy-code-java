# System Test Configuration
# This file contains configuration values for Run-SystemTests.ps1

$Config = @{
    Tests = @(

        # Smoke Tests
        @{  Id = "smoke-local-stub";
            Name = "Smoke Tests (Local - Stub)";
            Command = "& .\gradlew.bat :smoke-test:clean :smoke-test:test -DenvironmentMode=local -DexternalSystemMode=stub";
            Path = "system-test";
            TestReportPath = "system-test\smoke-test\build\reports\tests\test\index.html"
            TestInstallCommands = $null; },
        @{  Id = "smoke-local-real";
            Name = "Smoke Tests (Local - Real)";
            Command = "& .\gradlew.bat :smoke-test:clean :smoke-test:test -DenvironmentMode=local -DexternalSystemMode=real";
            Path = "system-test";
            TestReportPath = "system-test\smoke-test\build\reports\tests\test\index.html"
            TestInstallCommands = $null; },

        # External System Contract Tests
        @{  Id = "contract-local-stub";
            Name = "Contract Tests (Local - Stub)";
            Command = "& .\gradlew.bat :external-system-contract-test:clean :external-system-contract-test:test -DenvironmentMode=local -DexternalSystemMode=stub";
            Path = "system-test";
            TestReportPath = "system-test\external-system-contract-test\build\reports\tests\test\index.html"
            TestInstallCommands = $null; },
        @{  Id = "contract-local-real";
            Name = "Contract Tests (Local - Real)";
            Command = "& .\gradlew.bat :external-system-contract-test:clean :external-system-contract-test:test -DenvironmentMode=local -DexternalSystemMode=real";
            Path = "system-test";
            TestReportPath = "system-test\external-system-contract-test\build\reports\tests\test\index.html"
            TestInstallCommands = $null; },

        # E2E Tests
        @{ 
            Id = "e2e-local-real";
            Name = "E2E Tests (Local - Real)";
            Command = "& .\gradlew.bat :e2e-test:clean :e2e-test:test -DenvironmentMode=local -DexternalSystemMode=real";
            Path = "system-test";
            TestReportPath = "system-test\e2e-test\build\reports\tests\test\index.html";
            TestInstallCommands = $null; }

    )
}

# Export the configuration
return $Config

