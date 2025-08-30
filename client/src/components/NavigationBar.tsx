import {
  ExitToAppOutlined,
  MovieOutlined,
  PersonOutlined,
} from "@mui/icons-material";
import {
  AppBar,
  Button,
  Toolbar,
  Typography,
  type AlertColor,
  type AlertPropsColorOverrides,
} from "@mui/material";
import { signOut } from "../services/UserService";
import { useLocation, useNavigate } from "react-router";
import type { Dispatch, SetStateAction } from "react";
import { AxiosError } from "axios";
import type { OverridableStringUnion } from "@mui/types";

interface NavigationBarProps {
  setShowAlert: Dispatch<SetStateAction<boolean>>;

  setSeverity: Dispatch<
    SetStateAction<OverridableStringUnion<AlertColor, AlertPropsColorOverrides>>
  >;

  setAlertMessage: Dispatch<SetStateAction<string>>;
}

function NavigationBar({
  setSeverity,
  setAlertMessage,
  setShowAlert,
}: NavigationBarProps) {
  const navigate = useNavigate();

  const location = useLocation();

  const isProfileRoute = location.pathname.startsWith("/profile");

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

  return (
    <AppBar position="static">
      <Toolbar>
        <Typography variant="h6" sx={{ flexGrow: 1, userSelect: "none" }}>
          Eigakan
        </Typography>

        <Button color="inherit" onClick={() => navigate("/movies")}>
          <MovieOutlined />
        </Button>

        {!isProfileRoute && (
          <Button color="inherit" onClick={() => navigate("/profile")}>
            <PersonOutlined />
          </Button>
        )}

        <Button color="inherit" onClick={onSignOutButtonClick}>
          <ExitToAppOutlined />
        </Button>
      </Toolbar>
    </AppBar>
  );
}

export default NavigationBar;
