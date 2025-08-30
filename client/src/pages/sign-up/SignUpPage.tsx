import {
  Alert,
  Box,
  Button,
  CircularProgress,
  Snackbar,
  TextField,
  Typography,
  type AlertColor,
  type AlertPropsColorOverrides,
} from "@mui/material";
import { useEffect, useState, type FormEvent } from "react";
import { AxiosError } from "axios";
import type { OverridableStringUnion } from "@mui/types";
import { useNavigate } from "react-router";
import { isAuthenticated, signUp } from "../../services/UserService";

function SignUpPage() {
  const [username, setUsername] = useState("");

  const [password, setPassword] = useState("");

  const [passwordConfirmation, setPasswordConfirmation] = useState("");

  const [showAlert, setShowAlert] = useState(false);

  const [alertMessage, setAlertMessage] = useState("");

  const [severity, setSeverity] =
    useState<OverridableStringUnion<AlertColor, AlertPropsColorOverrides>>(
      "info"
    );

  const [canAccess, setCanAccess] = useState<boolean | null>(null);

  const navigate = useNavigate();

  const handleSubmit = async (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    if (password != passwordConfirmation) {
      setSeverity("error");

      setAlertMessage("invalid password confirmation");

      setShowAlert(true);

      setPassword("");

      setPasswordConfirmation("");

      return;
    }

    try {
      await signUp({
        username: username,
        rawPassword: password,
      });

      navigate("/");
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

    setUsername("");

    setPassword("");

    setPasswordConfirmation("");
  };

  const closeAlert = () => {
    setShowAlert(false);
  };

  useEffect(() => {
    const checkCanAccess = async () => {
      try {
        if (await isAuthenticated()) {
          setCanAccess(false);

          navigate("/");
        } else {
          setCanAccess(true);
        }
      } catch (error: unknown) {
        setSeverity("error");

        if (error instanceof AxiosError) {
          if (!error.response) {
            setAlertMessage("cannot connect to the server");
          } else if (error.status == 401) {
            setCanAccess(false);

            return;
          } else {
            setAlertMessage(error.response.data.message ?? "unknown error");
          }
        }

        setShowAlert(true);
      }
    };

    checkCanAccess();
  }, []);

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
    <Box
      sx={{
        alignItems: "center",
        display: "flex",
        flexDirection: "column",
        justifyContent: "center",
        minHeight: "100dvh",
      }}
    >
      <Typography
        component="h1"
        sx={{
          fontSize: "2.5rem",
          margin: "16px",
          userSelect: "none",
        }}
      >
        Sign Up
      </Typography>
      <Box
        component="form"
        onSubmit={handleSubmit}
        noValidate
        sx={{
          display: "flex",
          flexDirection: "column",
          gap: "16px",
          margin: "16px",
          width: "80%",
        }}
      >
        <TextField
          label="Username"
          name="name"
          type="text"
          fullWidth
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          required
        />
        <TextField
          label="Password"
          name="password"
          type="password"
          fullWidth
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          required
        />
        <TextField
          label="Password Confirmation"
          name="password-confirmation"
          type="password"
          fullWidth
          value={passwordConfirmation}
          onChange={(e) => setPasswordConfirmation(e.target.value)}
          required
        />
        <Button type="submit" variant="contained" color="primary">
          Sign Up
        </Button>
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

export default SignUpPage;
