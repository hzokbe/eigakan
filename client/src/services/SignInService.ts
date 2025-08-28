import { server } from "../server/config";

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
