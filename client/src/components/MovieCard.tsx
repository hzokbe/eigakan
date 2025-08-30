import { Card, CardContent } from "@mui/material";
import type { MovieResponse } from "../types/MovieResponse";
import { MovieOutlined } from "@mui/icons-material";
import { Link } from "react-router";

interface MovieCardProps {
  movie: MovieResponse;
}

function MovieCard({ movie }: MovieCardProps) {
  return (
    <Link to={"/movies/" + movie.id} title={movie.title}>
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
    </Link>
  );
}

export default MovieCard;
