# ── Stage 1: Build the Angular app ──────────────────────────────────────────
FROM node:20-alpine AS builder

WORKDIR /app

# Copy package files first for layer caching
COPY package*.json ./
RUN npm ci --prefer-offline

# Copy source and build
COPY . .
RUN npm run build -- --configuration production

# ── Stage 2: Serve with Nginx ─────────────────────────────────────────────────
FROM nginx:alpine

# Remove default Nginx config
RUN rm /etc/nginx/conf.d/default.conf

# Copy built Angular app into Nginx html root
COPY --from=builder /app/dist/frontend-angular/browser /usr/share/nginx/html

# Copy Nginx config (supplied at build time from repo root context)
COPY nginx/default.conf /etc/nginx/conf.d/default.conf

EXPOSE 80
