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
import type { OverridableStringUnion } from "@mui/types";
import { AxiosError } from "axios";
import { useState, type FormEvent } from "react";
import { sendResetPasswordLink } from "../../services/UserService";

function ResetPasswordPage() {
  const [email, setEmail] = useState("");

  const [showAlert, setShowAlert] = useState(false);

  const [alertMessage, setAlertMessage] = useState("");

  const [severity, setSeverity] =
    useState<OverridableStringUnion<AlertColor, AlertPropsColorOverrides>>(
      "info"
    );

  const handleSubmit = async (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    if (email.trim() == "") {
      setSeverity("error");

      setAlertMessage("email cannot be blank");

      setShowAlert(true);

      setEmail("");

      return;
    }

    try {
      sendResetPasswordLink(email.trim());

      setSeverity("success");

      setAlertMessage("password reset link sent");

      setShowAlert(true);
    } catch (error: unknown) {
      setSeverity("error");

      if (error instanceof AxiosError) {
        if (!error.response) {
          setEmail("cannot connect to the server");
        } else {
          setEmail(error.response.data.message ?? "unknown error");
        }
      }

      setShowAlert(true);
    }

    setEmail("");
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
        Reset Password
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
          label="Email"
          name="email"
          type="email"
          fullWidth
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          required
        />

        <Button type="submit" variant="contained" color="primary">
          Send reset password link
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

export default ResetPasswordPage;
