import "./App.css";
import { useEffect, useState } from "react";
import {
  Alert,
  AppBar,
  Box,
  Button,
  CircularProgress,
  Snackbar,
  Toolbar,
  Typography,
  type AlertColor,
  type AlertPropsColorOverrides,
} from "@mui/material";
import { isAuthenticated } from "./services/AuthenticationService";
import { AxiosError } from "axios";
import type { OverridableStringUnion } from "@mui/types";
import { useNavigate } from "react-router";
import { ExitToAppOutlined, PersonOutlined } from "@mui/icons-material";
import { signOut } from "./services/SignOutService";

function App() {
  const [canAccess, setCanAccess] = useState<boolean | null>(null);

  const [showAlert, setShowAlert] = useState(false);

  const [alertMessage, setAlertMessage] = useState("");

  const closeAlert = () => {
    setShowAlert(false);
  };

  const [severity, setSeverity] =
    useState<OverridableStringUnion<AlertColor, AlertPropsColorOverrides>>(
      "info"
    );

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

  const onSignOutButtonClick = () => {
    const tryToSignOut = async () => {
      try {
        await signOut();

        navigate("/sign-in");
      } catch (error: unknown) {
        setSeverity("error");

        if (error instanceof AxiosError) {
          if (!error.response) {
            setAlertMessage("cannot connect to the server");
          } else {
            setAlertMessage(error.response.data.message ?? "unknown error");
          }
        }

        setShowAlert(true);
      }
    };

    tryToSignOut();
  };

  if (canAccess == null) {
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
      <AppBar position="static">
        <Toolbar>
          <Typography variant="h6" sx={{ flexGrow: 1, userSelect: "none" }}>
            Eigakan
          </Typography>

          <Button color="inherit" onClick={() => navigate("/profile")}>
            <PersonOutlined />
          </Button>

          <Button color="inherit" onClick={onSignOutButtonClick}>
            <ExitToAppOutlined />
          </Button>
        </Toolbar>
      </AppBar>

      <Typography
        component="h1"
        sx={{
          fontSize: "2.5rem",
          margin: "16px",
          userSelect: "none",
        }}
      >
        Hello, world!
      </Typography>

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

export default App;
