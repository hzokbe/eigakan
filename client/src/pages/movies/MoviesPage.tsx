import {
  Alert,
  Box,
  CircularProgress,
  Pagination,
  Snackbar,
  Stack,
  type AlertColor,
  type AlertPropsColorOverrides,
} from "@mui/material";
import type { OverridableStringUnion } from "@mui/types";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router";
import { isAuthenticated } from "../../services/UserService";
import { AxiosError } from "axios";
import NavigationBar from "../../components/NavigationBar";
import type { MovieResponse } from "../../types/MovieResponse";
import { getMovies } from "../../services/MovieService";
import MovieCard from "../../components/MovieCard";

function MoviesPage() {
  const [canAccess, setCanAccess] = useState<boolean | null>(null);

  const [showAlert, setShowAlert] = useState(false);

  const [alertMessage, setAlertMessage] = useState("");

  const [severity, setSeverity] =
    useState<OverridableStringUnion<AlertColor, AlertPropsColorOverrides>>(
      "info"
    );

  const [movies, setMovies] = useState<MovieResponse[] | null>();

  const closeAlert = () => {
    setShowAlert(false);
  };

  const navigate = useNavigate();

  const [currentPage, setCurrentPage] = useState<number>(1);

  const [totalPages, setTotalPages] = useState<number | null>(null);

  useEffect(() => {
    const checkCanAccess = async () => {
      try {
        if (await isAuthenticated()) {
          setCanAccess(true);
        } else {
          setCanAccess(false);

          navigate("/sign-in");
        }
      } catch (error: unknown) {
        setSeverity("error");

        if (error instanceof AxiosError) {
          if (!error.response) {
            setAlertMessage("cannot connect to the server");
          } else if (error.status == 401) {
            navigate("/sign-in");
          } else {
            setAlertMessage(error.response.data.message ?? "unknown error");
          }
        }

        setShowAlert(true);
      }
    };

    checkCanAccess();
  }, []);

  useEffect(() => {
    const updateMovies = async () => {
      try {
        const response = await getMovies(currentPage - 1, 10);

        setMovies(response.content);

        setCurrentPage(response.number + 1);

        setTotalPages(response.totalPages);
      } catch (error: unknown) {
        setSeverity("error");

        if (error instanceof AxiosError) {
          if (!error.response) {
            setAlertMessage("cannot connect to the server");
          } else if (error.status == 401) {
            navigate("/sign-in");
          } else {
            setAlertMessage(error.response.data.message ?? "unknown error");
          }
        }

        setShowAlert(true);
      }
    };

    updateMovies();
  }, [currentPage]);

  if (canAccess == null || movies == null || totalPages == null) {
    return (
      <Box
        sx={{
          alignItems: "center",
          display: "flex",
          justifyContent: "center",
          minHeight: "100dvh",
          width: "100vw",
        }}
      >
        <CircularProgress />

        <Snackbar
          anchorOrigin={{ vertical: "bottom", horizontal: "center" }}
          open={showAlert}
          autoHideDuration={3000}
          onClose={closeAlert}
        >
          <Alert
            onClose={closeAlert}
            severity={severity}
            variant="filled"
            sx={{ width: "100%" }}
          >
            {alertMessage}
          </Alert>
        </Snackbar>
      </Box>
    );
  }

  return (
    <Box>
      <NavigationBar
        setSeverity={setSeverity}
        setAlertMessage={setAlertMessage}
        setShowAlert={setShowAlert}
      />

      <Box
        sx={{
          boxSizing: "border-box",
          display: "flex",
          flexDirection: "column",
          gap: "8px",
          padding: "16px",
          minHeight: "100dvh",
        }}
      >
        <Stack sx={{ display: "flex", gap: "8px" }}>
          {movies.map((m: MovieResponse) => (
            <MovieCard movie={m} />
          ))}
        </Stack>

        {totalPages != 0 ? (
          <Pagination
            sx={{ width: "100%" }}
            page={currentPage}
            count={totalPages}
            onChange={(_, page) => setCurrentPage(page)}
            shape="rounded"
          />
        ) : (
          <></>
        )}
      </Box>

      <Snackbar
        anchorOrigin={{ vertical: "bottom", horizontal: "center" }}
        open={showAlert}
        autoHideDuration={3000}
        onClose={closeAlert}
      >
        <Alert
          onClose={closeAlert}
          severity={severity}
          variant="filled"
          sx={{ width: "100%" }}
        >
          {alertMessage}
        </Alert>
      </Snackbar>
    </Box>
  );
}

export default MoviesPage;
