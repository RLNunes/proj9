# Use official Node.js LTS image
FROM node:24-alpine

RUN apk update

RUN apk add --no-cache vim nano

RUN apk add --no-cache openssh

# Prepare SSH
RUN mkdir -p /run/sshd && \
    ssh-keygen -A

# Permit root login
RUN echo "PermitRootLogin yes" >> /etc/ssh/sshd_config && \
    echo "PasswordAuthentication yes" >> /etc/ssh/sshd_config && \
    echo "root:root" | chpasswd

# Configure root access using SSH
RUN mkdir -p /root/.ssh

# Copy authorized_keys file
COPY authorized_keys /root/.ssh
RUN chmod go-w /root/.ssh/authorized_keys

# Set working directory inside the container
WORKDIR /app

# Copy package.json and package-lock.json
COPY package.json package-lock.json ./

# Install dependencies
RUN npm install

# Copy the rest of the application files
COPY . .

# Copy entrypoint
COPY docker-entrypoint.sh /usr/local/bin/
RUN chmod +x /usr/local/bin/docker-entrypoint.sh

# Expose the application port
EXPOSE 80

# Expose the SSH server port
EXPOSE 22

# Start the application
ENTRYPOINT ["/usr/local/bin/docker-entrypoint.sh"]
CMD ["node", "index.js"]