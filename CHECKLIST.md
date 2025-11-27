# Post-Migration Checklist

## ✅ Completed Tasks

### 1. Project Structure
- [x] Renamed `monolith/` to `backend/`
- [x] Created `frontend/` module at root level
- [x] Moved frontend source from `backend/src/frontend/` to `frontend/src/`
- [x] Moved frontend config files to `frontend/`
- [x] Created root `settings.gradle` and `build.gradle`

### 2. Frontend Module
- [x] Created `frontend/Dockerfile` (multi-stage: Node + Nginx)
- [x] Created `frontend/nginx.conf` for port 3001
- [x] Created `frontend/build.gradle` with npm tasks
- [x] Updated `frontend/vite.config.ts` paths and proxy port
- [x] Updated `frontend/tsconfig.json` paths
- [x] Updated `frontend/package.json` metadata
- [x] Created `frontend/README.md`
- [x] Created `frontend/.gitignore`

### 3. Backend Module  
- [x] Created `backend/src/main/java/.../config/CorsConfig.java`
- [x] Updated `backend/src/main/resources/application.yml` with new ports
- [x] Updated `backend/build.gradle` (removed frontend tasks)
- [x] Updated `backend/settings.gradle` name
- [x] Kept `backend/Dockerfile` (already good)

### 4. Docker Compose
- [x] Updated `docker-compose.local.yml` with new ports and services
- [x] Updated `docker-compose.pipeline.yml` with new ports and services
- [x] Updated all port mappings (3001, 8081, 5401, 9011, 9012)

### 5. Build Scripts
- [x] Updated `run.ps1` Build-System function
- [x] Updated `run.ps1` Wait-ForServices function
- [x] Updated `run.ps1` Start-System function
- [x] Updated `run.ps1` service URLs output

### 6. System Tests
- [x] Updated `system-test/src/test/resources/application.yml` with new ports

### 7. Documentation
- [x] Updated root `README.md`
- [x] Created `frontend/README.md`
- [x] Created `MIGRATION.md`

## 🔍 Verification Steps

### Test Build
```powershell
.\run.ps1 build
```
**Expected**: Both frontend and backend build successfully

### Test Docker Compose
```powershell
.\run.ps1 start
```
**Expected**: 5 containers start (frontend, backend, postgres, erp-api, tax-api)

### Test Service Health
- [ ] Frontend responds on http://localhost:3001
- [ ] Backend API responds on http://localhost:8081/api
- [ ] ERP API responds on http://localhost:9011
- [ ] Tax API responds on http://localhost:9012
- [ ] PostgreSQL accessible on localhost:5401

### Test CORS
- [ ] Frontend can call backend API without CORS errors
- [ ] Browser console shows no CORS warnings

### Run System Tests
```powershell
.\run.ps1 test
```
**Expected**: All E2E and smoke tests pass

## 📝 Manual Testing

### Frontend UI Testing
1. Open http://localhost:3001
2. Navigate to "Place Order" page
3. Fill in order form and submit
4. Verify order appears in "Order History"
5. Cancel the order
6. Verify order status updates

### API Testing
```powershell
# Health check
curl http://localhost:8081/api/orders

# Place order
curl -X POST http://localhost:8081/api/orders `
  -H "Content-Type: application/json" `
  -d '{"sku":"TEST-SKU","quantity":5,"country":"US"}'
```

## 🐛 Known Issues / TODOs

- [ ] Update GitHub Actions workflows (commit-stage-monolith → commit-stage-backend)
- [ ] Update Docker image names in CI/CD (monolith → backend/frontend)
- [ ] Consider adding frontend tests (unit tests for TypeScript)
- [ ] Consider adding API integration tests (backend)
- [ ] Update any hardcoded URLs in documentation

## 🎯 Next Steps

1. **Phase 3 (Future)**: Consider separate deployments
   - Deploy frontend to CDN
   - Deploy backend to app server cluster
   - Use environment variables for API URLs

2. **Monitoring**: Add health check endpoints
   - Frontend: nginx health
   - Backend: Spring Boot Actuator

3. **Security**: Review CORS configuration for production
   - Restrict allowed origins
   - Add rate limiting

## 📊 Port Reference

Quick reference for the new port scheme:

| Service | Port | URL |
|---------|------|-----|
| Frontend | 3001 | http://localhost:3001 |
| Backend | 8081 | http://localhost:8081 |
| ERP API | 9011 | http://localhost:9011 |
| Tax API | 9012 | http://localhost:9012 |
| PostgreSQL | 5401 | localhost:5401 |

