import {
  Alert,
  Box,
  Button,
  Snackbar,
  TextField,
  Typography,
  type AlertColor,
  type AlertPropsColorOverrides,
} from "@mui/material";
import { useState, type FormEvent } from "react";
import { AxiosError } from "axios";
import type { OverridableStringUnion } from "@mui/types";
import { signIn } from "../services/SignInService";

function SignInPage() {
  const [username, setUsername] = useState("");

  const [password, setPassword] = useState("");

  const [showAlert, setShowAlert] = useState(false);

  const [alertMessage, setAlertMessage] = useState("");

  const [severity, setSeverity] =
    useState<OverridableStringUnion<AlertColor, AlertPropsColorOverrides>>(
      "info"
    );

  const handleSubmit = async (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    if (password == "") {
      setSeverity("error");

      setAlertMessage("password cannot be blank");

      setShowAlert(true);

      setPassword("");

      return;
    }

    try {
      await signIn(username, password);

      setSeverity("success");

      setAlertMessage("user logged in successfully");

      setShowAlert(true);
    } catch (error: unknown) {
      setSeverity("error");

      if (error instanceof AxiosError) {
        if (!error.response) {
          setAlertMessage("cannot connect to the server");
        } else if (error.status == 401) {
          setAlertMessage("invalid credentials");
        } else {
          setAlertMessage(error.response.data.message ?? "unknown error");
        }
      }

      setShowAlert(true);
    }

    setUsername("");

    setPassword("");
  };

  const closeAlert = () => {
    setShowAlert(false);
  };

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
        Sign In
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
        <Button type="submit" variant="contained" color="primary">
          Sign In
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

export default SignInPage;
