Write-Host "Stopping Docker containers..." -ForegroundColor Cyan

docker compose down

if ($LASTEXITCODE -eq 0) {
    Write-Host ""
    Write-Host "Services stopped successfully!" -ForegroundColor Green
} else {
    Write-Host ""
    Write-Host "Error stopping services" -ForegroundColor Red
    exit $LASTEXITCODE
}

