FROM node:alpine
WORKDIR bookstore-ui
COPY package.json .
RUN npm i
COPY . .
CMD ["npm","start"]
#RUN npm run build

#FROM nginx:latest
#COPY --from=build /bookstore-ui/build /usr/share/nginx/html