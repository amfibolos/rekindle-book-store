FROM node as store-build
WORKDIR /app
COPY package.json .
RUN npm install
COPY . .
#CMD ["npm","start"]
RUN npm run build

FROM nginx
COPY --from=store-build /app/build /usr/share/nginx/html