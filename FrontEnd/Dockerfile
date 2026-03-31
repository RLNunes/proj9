FROM nginx:alpine

# Remove default server config to avoid conflicts with our custom reverse proxy setup
RUN rm /etc/nginx/conf.d/default.conf

# Static frontend assets
COPY FrontEnd/public /usr/share/nginx/html

# Custom server config that also reverse-proxies /api to Payara
COPY nginx/default.conf /etc/nginx/conf.d/default.conf
