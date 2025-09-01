import "./index.css";
import { createRoot } from "react-dom/client";
import { StrictMode } from "react";
import HomePage from "./pages/home/HomePage.tsx";
import { BrowserRouter, Route, Routes } from "react-router";
import SignUpPage from "./pages/sign-up/SignUpPage.tsx";
import SignInPage from "./pages/sign-in/SignInPage.tsx";
import ProfilePage from "./pages/profile/ProfilePage.tsx";
import MoviesPage from "./pages/movies/MoviesPage.tsx";
import NotFoundPage from "./pages/not-found/NotFoundPage.tsx";
import MoviePage from "./pages/movie/MoviePage.tsx";
import ResetPasswordPage from "./pages/reset-password/ResetPasswordPage.tsx";

createRoot(document.getElementById("root")!).render(
  <StrictMode>
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<HomePage />} />

        <Route path="/sign-up" element={<SignUpPage />} />

        <Route path="/sign-in" element={<SignInPage />} />

        <Route path="/reset-password" element={<ResetPasswordPage />} />

        <Route path="/profile" element={<ProfilePage />} />

        <Route path="/movies" element={<MoviesPage />} />

        <Route path="/movies/:id" element={<MoviePage />} />

        <Route path="*" element={<NotFoundPage />} />
      </Routes>
    </BrowserRouter>
  </StrictMode>
);
