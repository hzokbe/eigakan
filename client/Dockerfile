FROM node:21-alpine AS builder

WORKDIR /app

RUN npm install -g pnpm

COPY package.json pnpm-lock.yaml ./

RUN pnpm install

COPY . .

RUN pnpm build

FROM node:21-alpine AS runner

WORKDIR /app

RUN npm install -g pnpm

COPY package.json pnpm-lock.yaml ./

RUN pnpm install --prod

COPY --from=builder /app/.next ./.next

COPY --from=builder /app/public ./public

COPY --from=builder /app/node_modules ./node_modules

EXPOSE 3000

CMD ["pnpm", "start"]
