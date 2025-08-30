import "./index.css";
import { createRoot } from "react-dom/client";
import { StrictMode } from "react";
import App from "./App.tsx";
import { BrowserRouter, Route, Routes } from "react-router";
import SignUpPage from "./pages/sign-up/SignUpPage.tsx";
import SignInPage from "./pages/sign-in/SignInPage.tsx";
import ProfilePage from "./pages/profile/ProfilePage.tsx";

createRoot(document.getElementById("root")!).render(
  <StrictMode>
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<App />} />

        <Route path="/sign-up" element={<SignUpPage />} />

        <Route path="/sign-in" element={<SignInPage />} />

        <Route path="/profile" element={<ProfilePage />} />
      </Routes>
    </BrowserRouter>
  </StrictMode>
);
