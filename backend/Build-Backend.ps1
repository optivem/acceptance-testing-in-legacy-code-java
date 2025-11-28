function Build-Backend {
    Write-Host "Building backend..." -ForegroundColor Cyan
    Execute-Command -Command "& .\Build-Backend.ps1" -SubFolder "backend"
}