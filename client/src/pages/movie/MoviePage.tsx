import {
  Alert,
  Box,
  Card,
  CardContent,
  CircularProgress,
  Snackbar,
  Typography,
  type AlertColor,
  type AlertPropsColorOverrides,
} from "@mui/material";
import type { OverridableStringUnion } from "@mui/types";
import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router";
import { isAuthenticated } from "../../services/AuthenticationService";
import { AxiosError } from "axios";
import NavigationBar from "../../components/NavigationBar";
import type { MovieResponse } from "../../types/MovieResponse";
import { getMovieById } from "../../services/MovieService";
import { MovieOutlined } from "@mui/icons-material";

function MoviePage() {
  const [canAccess, setCanAccess] = useState<boolean | null>(null);

  const [showAlert, setShowAlert] = useState(false);

  const [alertMessage, setAlertMessage] = useState("");

  const [severity, setSeverity] =
    useState<OverridableStringUnion<AlertColor, AlertPropsColorOverrides>>(
      "info"
    );

  const [movie, setMovie] = useState<MovieResponse | null>();

  const { id } = useParams();

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
        if (id) {
          setMovie(await getMovieById(id));
        } else {
          setSeverity("error");

          setAlertMessage("invalid movie id");

          setShowAlert(true);
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

    updateMovies();
  }, []);

  if (canAccess == null || movie == null) {
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

  if (!id) {
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
        <Typography
          component="h2"
          sx={{
            fontSize: "2.5rem",
            margin: "16px",
            userSelect: "none",
          }}
        >
          {movie.title} ({movie.genre})
        </Typography>

        <Card>
          <CardContent
            sx={{
              alignItems: "center",
              backgroundColor: "#a3a3a3",
              display: "flex",
              justifyContent: "center",
              minHeight: "120px",
              minWidth: "80px",
            }}
          >
            <MovieOutlined sx={{ color: "#fafafa" }} />
          </CardContent>
        </Card>
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

export default MoviePage;
