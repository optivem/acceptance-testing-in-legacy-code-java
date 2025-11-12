param(
    [string]$Service = ""
)

Write-Host "Viewing Docker container logs..." -ForegroundColor Cyan
Write-Host "Press Ctrl+C to exit" -ForegroundColor Yellow
Write-Host ""

if ($Service -eq "") {
    docker compose logs -f
} else {
    docker compose logs -f $Service
}

