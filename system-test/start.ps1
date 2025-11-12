Write-Host "Building monolith application..." -ForegroundColor Cyan
Set-Location ..\monolith

& .\gradlew.bat clean bootJar

if ($LASTEXITCODE -ne 0) {
    Write-Host "Build failed!" -ForegroundColor Red
    exit $LASTEXITCODE
}

Write-Host ""
Write-Host "Starting Docker containers..." -ForegroundColor Cyan
Set-Location ..\system-test

docker compose up -d

Write-Host ""
Write-Host "Done! Services are starting..." -ForegroundColor Green
Write-Host "- JSON Server: " -NoNewline
Write-Host "http://localhost:3000" -ForegroundColor Yellow
Write-Host "- Monolith API: " -NoNewline
Write-Host "http://localhost:8080" -ForegroundColor Yellow
Write-Host ""
Write-Host "To view logs: " -NoNewline
Write-Host "docker compose logs -f" -ForegroundColor Cyan
Write-Host "To stop: " -NoNewline
Write-Host "docker compose down" -ForegroundColor Cyan

