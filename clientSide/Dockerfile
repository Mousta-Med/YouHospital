FROM node:18.12
WORKDIR /clientside
COPY package*.json ./
RUN npm install -g @angular/cli@16.2.10
RUN npm install
COPY . .
EXPOSE 4201
CMD ["ng", "serve","--host", "0.0.0.0", "--port", "4201"]
