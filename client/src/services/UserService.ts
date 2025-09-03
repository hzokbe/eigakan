import type { SignUpRequest } from "../types/SignUpRequest";
import { server } from "../server/config";

export const isAuthenticated = async () => {
  const response = await server.get("/is-authenticated");

  return response.status == 200;
};

export const isAdmin = async () => {
  const response = await server.get("/is-admin");

  return response.status == 200;
};

export const signUp = async (request: SignUpRequest) => {
  await server.post("/sign-up", {
    username: request.username,
    rawPassword: request.rawPassword,
    email: request.email,
  });
};

export const signIn = async (username: string, rawPassword: string) => {
  const encodedUsernameAndRawPassword = btoa(`${username}:${rawPassword}`);

  await server.post(
    "/sign-in",
    {},
    {
      headers: {
        Authorization: `Basic ${encodedUsernameAndRawPassword}`,
      },
    }
  );
};

export const sendResetPasswordLink = async (email: string) => {
  server.post(
    "/send-reset-password-link",
    { email: email },
    { withCredentials: false }
  );
};

export const resetPassword = async (password: string, recoverToken: string) => {
  server.post(
    `/reset-password/${recoverToken}`,
    { rawPassword: password },
    { withCredentials: false }
  );
};

export const signOut = async () => {
  server.post("/sign-out");
};
