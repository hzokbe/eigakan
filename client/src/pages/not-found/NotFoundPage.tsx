import { SearchOffOutlined } from "@mui/icons-material";
import { Box, Typography } from "@mui/material";

function NotFoundPage() {
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
      <Typography
        component="h1"
        sx={{
          alignItems: "center",
          display: "flex",
          fontSize: "1.5rem",
          gap: "8px",
          margin: "16px",
          userSelect: "none",
        }}
      >
        <SearchOffOutlined /> Page not found
      </Typography>
    </Box>
  );
}

export default NotFoundPage;
