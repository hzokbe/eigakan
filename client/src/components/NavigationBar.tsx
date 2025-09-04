import {
  FavoriteBorder,
  Logout,
  MovieOutlined,
  Settings,
} from "@mui/icons-material";
import {
  AppBar,
  Avatar,
  Box,
  Button,
  ListItemIcon,
  Menu,
  MenuItem,
  Toolbar,
  type AlertColor,
  type AlertPropsColorOverrides,
} from "@mui/material";
import { signOut } from "../services/UserService";
import { Link, useNavigate } from "react-router";
import { useState, type Dispatch, type SetStateAction } from "react";
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

  const [anchorEl, setAnchorEl] = useState<HTMLElement | null>(null);

  const open = Boolean(anchorEl);

  const handleClose = () => {
    setAnchorEl(null);
  };

  return (
    <AppBar position="static">
      <Toolbar
        sx={{
          boxSizing: "border-box",
          display: "flex",
          justifyContent: "space-between",
          width: "100%",
        }}
      >
        <Box>
          <Link to="/">Eigakan</Link>
        </Box>

        <Box>
          <Button color="inherit" onClick={() => navigate("/my-list")}>
            <FavoriteBorder />
          </Button>

          <Button color="inherit" onClick={() => navigate("/movies")}>
            <MovieOutlined />
          </Button>

          <Button
            color="inherit"
            onClick={(event) => setAnchorEl(event.currentTarget)}
          >
            <Avatar sx={{ width: 24, height: 24 }} />
          </Button>

          <Menu
            id="account-menu"
            anchorEl={anchorEl}
            open={open}
            onClose={handleClose}
            onClick={handleClose}
          >
            <MenuItem
              onClick={() => {
                handleClose();

                navigate("/profile");
              }}
            >
              <ListItemIcon>
                <Settings fontSize="small" />
              </ListItemIcon>
              Settings
            </MenuItem>

            <MenuItem
              onClick={() => {
                handleClose();

                onSignOutButtonClick();
              }}
            >
              <ListItemIcon>
                <Logout fontSize="small" />
              </ListItemIcon>
              Logout
            </MenuItem>
          </Menu>
        </Box>
      </Toolbar>
    </AppBar>
  );
}

export default NavigationBar;
