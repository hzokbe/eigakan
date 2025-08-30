import {
  Alert,
  Box,
  CircularProgress,
  Grid,
  Snackbar,
  type AlertColor,
  type AlertPropsColorOverrides,
} from "@mui/material";
import type { OverridableStringUnion } from "@mui/types";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router";
import { isAuthenticated } from "../../services/AuthenticationService";
import { AxiosError } from "axios";
import NavigationBar from "../../components/NavigationBar";
import type { MovieResponse } from "../../types/MovieResponse";
import { getAllMovies } from "../../services/MovieService";
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
        setMovies(await getAllMovies());
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
  });

  if (canAccess == null || movies == null) {
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

      <Box sx={{ boxSizing: "border-box", padding: "16px" }}>
        <Grid
          container
          spacing={{ xs: 2, md: 3 }}
          columns={{ xs: 4, sm: 8, md: 12 }}
        >
          {movies.map((m: MovieResponse) => (
            <Grid key={m.id} size={{ xs: 2, sm: 4, md: 4 }}>
              <MovieCard movie={m} />
            </Grid>
          ))}
        </Grid>
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
