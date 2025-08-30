import { server } from "../server/config";

export const signOut = async () => {
  server.post("/sign-out");
};
