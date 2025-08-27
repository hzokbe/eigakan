import axios from "axios";

export const server = axios.create({
  baseURL: import.meta.env.API_URL || "http://localhost:8080/",
  timeout: 10000,
  headers: {
    "Content-Type": "application/json",
    Accept: "application/json",
  },
  withCredentials: true,
});
