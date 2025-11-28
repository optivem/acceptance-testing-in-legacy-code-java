function Build-Frontend {
    Write-Host "Building frontend..." -ForegroundColor Cyan
    Execute-Command -Command "& .\Build-Frontend.ps1" -SubFolder "frontend"
}