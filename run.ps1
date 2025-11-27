param(
    [Parameter(Mandatory=$true, Position=0)]
    [ValidateSet("build", "start", "test", "stop", "logs", "all")]
    [string]$Command,

    [Parameter(Position=1)]
    [ValidateSet("local", "pipeline")]
    [string]$Mode = "local"
)

# Constants - Service URLs
$FrontendUrl = "http://localhost:3001"
$BackendUrl = "http://localhost:8081/health"
$ErpApiUrl = "http://localhost:9001/erp/health"
$TaxApiUrl = "http://localhost:9001/tax/health"
$PostgresHost = "localhost:5401"

# Constants - Configuration
$ContainerName = "modern-acceptance-testing-in-legacy-code-java"
$TestReportPath = "system-test\build\reports\tests\test\index.html"

# Script Configuration
$ErrorActionPreference = "Continue"
$ComposeFile = if ($Mode -eq "pipeline") { "docker-compose.pipeline.yml" } else { "docker-compose.local.yml" }

function Assert-Success {
    param(
        [string]$ErrorMessage
    )

    if ($LASTEXITCODE -ne 0) {
        throw $ErrorMessage
    }
}


function Wait-ForService {
    param(
        [string]$Url,
        [string]$ServiceName,
        [string]$ContainerName,
        [int]$MaxAttempts = 10,
        [int]$LogLines = 50
    )

    $attempt = 0
    $isReady = $false

    while (-not $isReady -and $attempt -lt $MaxAttempts) {
        try {
            $response = Invoke-WebRequest -Uri $Url -UseBasicParsing -TimeoutSec 2 -ErrorAction SilentlyContinue
            if ($response.StatusCode -eq 200) {
                $isReady = $true
            }
        } catch {
            $attempt++
            Start-Sleep -Seconds 1
        }
    }

    if (-not $isReady) {
        docker compose -f $ComposeFile logs $ContainerName --tail=$LogLines
        throw "$ServiceName failed to become ready after $MaxAttempts attempts"
    }
}

function Wait-ForServices {
    Wait-ForService -Url $ErpApiUrl -ServiceName "ERP API" -ContainerName "external" -LogLines 20
    Wait-ForService -Url $TaxApiUrl -ServiceName "Tax API" -ContainerName "external" -LogLines 20
    Wait-ForService -Url $BackendUrl -ServiceName "Backend API" -ContainerName "backend" -LogLines 50
    Wait-ForService -Url $FrontendUrl -ServiceName "Frontend" -ContainerName "frontend" -LogLines 50
}

function Build-Backend {
    Set-Location backend

    & .\gradlew.bat clean build
    Assert-Success "Backend build failed!"

    Set-Location ..
}

function Build-Frontend {
    Set-Location frontend

    if (-not (Test-Path "node_modules")) {
        npm install
        Set-Location ..
        Assert-Success "Frontend dependency installation failed!"
    }

    npm run build
    Set-Location ..
    Assert-Success "Frontend build failed!"
}

function Build-System {
    if ($Mode -eq "local") {
        Build-Backend
        Build-Frontend
    } else {
        Write-Host "Pipeline mode: Skipping build (using pre-built Docker images)" -ForegroundColor Cyan
    }
}

function Stop-System {
    docker compose -f docker-compose.local.yml down 2>$null
    Assert-Success "Error for docker compose down for local"
    docker compose -f docker-compose.pipeline.yml down 2>$null
    Assert-Success "Error for docker compose down for pipeline"

    $projectContainers = docker ps -aq --filter "name=$ContainerName" 2>$null
    if ($projectContainers) {
        docker stop $projectContainers 2>$null
        docker rm -f $projectContainers 2>$null
    }

    # Wait to ensure containers are fully stopped and ports are released
    Start-Sleep -Seconds 2
}

function Start-System {
    docker compose -f $ComposeFile up -d --build
    Assert-Success "Failed to start Docker containers!"

    Write-Host "- Frontend UI: " -NoNewline
    Write-Host $FrontendUrl -ForegroundColor Yellow
    Write-Host "- Backend API Health: " -NoNewline
    Write-Host $BackendUrl -ForegroundColor Yellow
    Write-Host "- ERP API Health: " -NoNewline
    Write-Host $ErpApiUrl -ForegroundColor Yellow
    Write-Host "- Tax API Health: " -NoNewline
    Write-Host $TaxApiUrl -ForegroundColor Yellow
    Write-Host "- PostgreSQL Host: " -NoNewline
    Write-Host $PostgresHost -ForegroundColor Yellow
    Write-Host ""
    Write-Host "To view logs: " -NoNewline
    Write-Host ".\run.ps1 logs $Mode" -ForegroundColor Cyan
    Write-Host "To stop: " -NoNewline
    Write-Host ".\run.ps1 stop $Mode" -ForegroundColor Cyan
}

function Test-System {
    Set-Location system-test

    & .\gradlew.bat clean test
    Set-Location ..
    Assert-Success "System tests execution failed!"

    Write-Host ""
    Write-Host "All tests passed!" -ForegroundColor Green
    Write-Host "Test report: " -NoNewline
    Write-Host $TestReportPath -ForegroundColor Yellow
}



function Show-Logs {
    docker compose -f $ComposeFile logs --tail=100 -f
}

function Run-All {
    Write-Host "Build System"
    Build-System

    Write-Host "Stop System"
    Stop-System

    Write-Host "Start System"
    Start-System

    Write-Host "Wait for System"
    Wait-ForServices

    Write-Host "Test System"
    Test-System

    Write-Host ""
    Write-Host "================================================" -ForegroundColor Green
    Write-Host "All tasks completed successfully!" -ForegroundColor Green
    Write-Host "================================================" -ForegroundColor Green
    Write-Host ""
}

# Main execution
try {
    switch ($Command) {
        "build" { Build-System }
        "start" { Start-System }
        "test"  { Test-System }
        "stop"  { Stop-System }
        "logs"  { Show-Logs }
        "all"   { Run-All }
    }
} catch {
    Write-Host ""
    Write-Host "Error: $_" -ForegroundColor Red
    exit 1
}

# Explicit exit with code 0 on success
exit 0


