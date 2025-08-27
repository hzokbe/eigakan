import type { SignUpRequest } from "../types/SignUpRequest";
import { server } from "../server/config";

export const signUp = async (request: SignUpRequest) => {
  await server.post("/sign-up", {
    username: request.username,
    rawPassword: request.rawPassword,
  });
};
