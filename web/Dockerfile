FROM node:latest

WORKDIR /app
COPY package.json /app/package.json
RUN npm i

COPY src /app/src
COPY public /app/public
COPY index.html /app/index.html
COPY tsconfig.json /app/tsconfig.json
COPY tsconfig.node.json /app/tsconfig.node.json
COPY vite.config.ts /app/vite.config.ts
CMD npm run dev
