import { server } from "../server/config";

export const isAuthenticated = async () => {
  const response = await server.get("/is-authenticated");

  return response.status == 200;
};
