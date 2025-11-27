# Frontend

Modern TypeScript frontend application built with Vite and served with Nginx.

## Tech Stack

- **TypeScript 5.6**: Type-safe JavaScript
- **Vite 5.4**: Fast build tool and dev server
- **Nginx**: Production web server
- **Vanilla TypeScript**: No framework - keeping it simple for legacy modernization

## Development

### Install dependencies
```bash
npm install
```

### Run development server with hot reload
```bash
npm run dev
```

This starts Vite dev server on **http://localhost:5173** with API proxy to backend on port 8081.

### Build for production
```bash
npm run build
```

Builds TypeScript files and bundles them using Vite, outputting to `dist/`.

### Type checking
```bash
npm run type-check
```

## Project Structure

```
src/
├── pages/           # Page-specific scripts
│   ├── place-order.ts
│   └── order-history.ts
├── services/        # API service layer
│   └── order-service.ts
├── types/           # TypeScript type definitions
│   ├── api.types.ts
│   ├── error.types.ts
│   ├── form.types.ts
│   ├── result.types.ts
│   └── index.ts
└── common.ts        # Shared utilities
```

## Integration with Backend

The frontend is completely independent from the backend:

- **Development**: Use `npm run dev` for hot reload. API calls proxied to localhost:8081
- **Production**: Frontend runs in its own Docker container with Nginx on port 3001
- **API Communication**: Frontend calls backend REST API via HTTP (CORS enabled)

## Docker

The frontend is containerized using a multi-stage Dockerfile:

1. **Build stage**: Uses Node.js to compile TypeScript and bundle assets
2. **Production stage**: Uses Nginx Alpine to serve static files

Build and run:
```bash
docker build -t frontend .
docker run -p 3001:3001 frontend
```

## Environment Variables

- `API_BASE_URL`: Backend API URL (default: http://localhost:8081)

